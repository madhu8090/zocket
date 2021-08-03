package com.mad.zocket.activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import com.mad.zocket.App
import com.mad.zocket.R
import com.mad.zocket.activity.base.XActivity
import com.mad.zocket.adapter.PageAdapter
import com.mad.zocket.databinding.ActivityMainBinding
import com.mad.zocket.helper.MyWorkManager
import com.mad.zocket.model.Constants
import com.mad.zocket.model.Datum
import com.mad.zocket.model.PageInfo
import com.mad.zocket.viewmodel.PageInfoViewModelFactory
import com.mad.zocket.viewmodel.PageViewModel
import java.util.concurrent.TimeUnit

class HomeActivity : XActivity(), PageAdapter.OnPageManager {

    private lateinit var mBinding: ActivityMainBinding

    private val pageViewModel: PageViewModel by viewModels {
        PageInfoViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
        title = "Home Page"

        mBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        mBinding.tvName.text = resources.getString(R.string.username) + user.email
        user.email.also { mBinding.tvName.text = it }

        // Add an observer on the LiveData returned by getAllPages.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mProgressBar!!.show()
        pageViewModel.allPages.observe(this) { pages ->
            // Update the cached copy of the pages in the adapter.
            mProgressBar!!.hide()
            submitList(pages)
        }

        myWorkManager()
    }

    private fun myWorkManager() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()

        val myRequest = PeriodicWorkRequest.Builder(
            MyWorkManager::class.java,
            30, // triggered every 30 minutes (minimum is 15 min)
            TimeUnit.MINUTES
        ).setConstraints(constraints)
            .build()

        WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork(
                "my_id",
                ExistingPeriodicWorkPolicy.KEEP,
                myRequest
            )
    }

    private fun submitList(it: List<PageInfo>?) {
        val adapter = PageAdapter(it!![0].getData(), this)
        mBinding.recyclerView.adapter = adapter;
        adapter.notifyDataSetChanged();
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            logout()
        }
        return true
    }

    private fun logout() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage("Are you sure you want to logout?")
        builder.setIcon(android.R.drawable.ic_dialog_alert)
        builder.setPositiveButton("Yes") { _, _ ->
            LoginManager.getInstance().logOut()
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        builder.setNegativeButton("No") { _, _ ->
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.show()
    }

    override fun onSelected(datum: Datum) {
        val intent = Intent(this, PageDetailsActivity::class.java)
        intent.putExtra(Constants.PAGE_INFO, datum.toString())
        startActivity(intent)
    }

}