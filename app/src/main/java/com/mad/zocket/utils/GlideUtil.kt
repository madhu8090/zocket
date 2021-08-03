package com.mad.zocket.utils

import android.R.attr
import android.app.Activity
import android.content.Context
import android.widget.ImageView
import com.mad.zocket.GlideApp
import com.mad.zocket.GlideRequest
import com.mad.zocket.R


class GlideUtil {

    open fun loadImage(activity: Activity, path: String?, mImage: ImageView) {

        GlideApp.with(activity)
            .load(path)
            .circleCrop()
            .error(R.drawable.ic_image)
            .into(mImage);
    }

    private fun isValidContextForGlide(context: Context?): Boolean {
        if (context == null) {
            return false
        }
        if (context is Activity) {
            return !context.isDestroyed && !context.isFinishing
        }
        return true
    }

}