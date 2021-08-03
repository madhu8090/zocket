package com.mad.zocket.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mad.zocket.App
import com.mad.zocket.api.ApiHelper
import com.mad.zocket.api.FBRetrofitBuilder
import com.mad.zocket.base.ViewModelFactory
import com.mad.zocket.databinding.ActivityLoginBinding
import com.mad.zocket.helper.PrefKeys
import com.mad.zocket.helper.Prefs
import com.mad.zocket.helper.ProgressBarHandler
import com.mad.zocket.model.PageInfo
import com.mad.zocket.utils.Status
import com.mad.zocket.viewmodel.LoginViewModel
import com.mad.zocket.viewmodel.PageInfoViewModelFactory
import com.mad.zocket.viewmodel.PageViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityLoginBinding
    lateinit var callbackManager: CallbackManager
    private lateinit var viewModel: LoginViewModel
    private var mProgressBar: ProgressBarHandler? = null
    private lateinit var auth: FirebaseAuth

    private val pageViewModel: PageViewModel by viewModels {
        PageInfoViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        // initialize progress bar handler
        mProgressBar = ProgressBarHandler(this)

        // Initialize Firebase Auth
        auth = Firebase.auth

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        setupViewModel()

        mBinding.loginButton.setPermissions(
            "email", "public_profile", "pages_manage_metadata",
            "pages_manage_posts",
            "pages_show_list"
        )

        mBinding.loginButton.registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Log.d(TAG, "facebook:onSuccess:$loginResult")
                mProgressBar!!.show()
                Toast.makeText(
                    applicationContext,
                    "Please wait while we download the Page information...",
                    Toast.LENGTH_LONG
                ).show()
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d(TAG, "facebook:onCancel")
            }

            override fun onError(error: FacebookException) {
                Log.d(TAG, "facebook:onError", error)
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(FBRetrofitBuilder.apiService))
        ).get(LoginViewModel::class.java)

    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:${token.token}")

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    Prefs.setString(PrefKeys.USER_ACCESS_TOKEN, token.token)
                    Prefs.setString(PrefKeys.USER_ID, token.userId)
                    setupObservers()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    mProgressBar!!.hide()
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun setupObservers() {
        val userId = Prefs.getString(PrefKeys.USER_ID)
        viewModel.getPageInfo(userId).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        mProgressBar!!.hide()
                        resource.data?.let { it -> retrieveList(it) }
                    }
                    Status.ERROR -> {
                        mProgressBar!!.hide()
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }

    private fun moveToNextActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun retrieveList(pageinfo: PageInfo) {
        Log.d("TAG", pageinfo.toString())
        pageViewModel.insert(pageinfo)
        moveToNextActivity()
    }
}