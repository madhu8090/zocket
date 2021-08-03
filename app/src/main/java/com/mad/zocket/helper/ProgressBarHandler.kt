package com.mad.zocket.helper

import android.R
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView

class ProgressBarHandler {
    private var mProgressBar: ProgressBar

    constructor(activity: Activity) {
        val mContext = activity.applicationContext
        val layout = activity.findViewById<View>(R.id.content).rootView as ViewGroup
        mProgressBar = ProgressBar(mContext, null, R.attr.progressBarStyle)
        mProgressBar.isIndeterminate = true
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        val rl = RelativeLayout(mContext)
        rl.gravity = Gravity.CENTER
        rl.addView(mProgressBar)
        layout.addView(rl, params)
        hide()
    }

    constructor(context: Context) {
        val rootView =
            (context as Activity).window.decorView.findViewById<View>(R.id.content).rootView as ViewGroup
        mProgressBar = ProgressBar(context, null, R.attr.progressBarStyle)
        mProgressBar.isIndeterminate = true
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        val rl = RelativeLayout(context)
        rl.gravity = Gravity.CENTER
        rl.addView(mProgressBar)
        rootView.addView(rl, params)
        hide()
    }

    fun show(mEmptyListView: TextView) {
        mProgressBar.visibility = View.VISIBLE
        mEmptyListView.visibility = View.VISIBLE
        mEmptyListView.text = "Loading..."
    }

    fun hide(mEmptyListView: TextView, size: Int, message: String) {
        mProgressBar.visibility = View.INVISIBLE
        when (size) {
            0 -> {
                mEmptyListView.visibility = View.VISIBLE
                mEmptyListView.text = "No $message found"
            }
            -1 -> {
                mEmptyListView.visibility = View.VISIBLE
                mEmptyListView.text = "Error loading $message or no $message found"
            }
            else -> {
                mEmptyListView.visibility = View.GONE
                mProgressBar.visibility = View.GONE
            }
        }
    }

    fun show() {
        mProgressBar.visibility = View.VISIBLE
    }

    fun hide() {
        mProgressBar.visibility = View.GONE
    }
}