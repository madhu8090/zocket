package com.mad.zocket.activity.base

import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.mad.zocket.helper.MadFragmentManager
import com.mad.zocket.helper.ProgressBarHandler
import io.reactivex.disposables.Disposable

open class XActivity : AppCompatActivity() {
    protected var mFragmentManager: MadFragmentManager? = null
    protected var mProgressBar: ProgressBarHandler? = null
    protected var mDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressBar = ProgressBarHandler(this)
        mFragmentManager = MadFragmentManager(this)
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            (this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mDisposable != null && !mDisposable!!.isDisposed) {
            mDisposable!!.dispose()
        }
        if (mProgressBar != null) {
            mProgressBar!!.hide()
        }
    }
}