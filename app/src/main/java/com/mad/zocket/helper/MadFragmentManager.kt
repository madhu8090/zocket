package com.mad.zocket.helper

import android.content.Context
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mad.zocket.R
import java.util.*

class MadFragmentManager {
    private var context: Context? = null
    var fragmentManager: FragmentManager?
        private set

    constructor(context: Context) {
        this.context = context
        fragmentManager = (context as AppCompatActivity).supportFragmentManager
    }

    constructor(fragment: Fragment) {
        fragmentManager = fragment.fragmentManager
    }

    val transaction: FragmentTransaction
        get() = fragmentManager!!.beginTransaction()

    private fun handleFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment, popLastFragment: Boolean
    ) {
        val ft = fragmentManager!!.beginTransaction()
        if (popLastFragment) {
            fragmentManager!!.popBackStack()
        }
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        ft.replace(containerViewId, fragment, fragment.tag)
            .addToBackStack(fragment.tag)
            .commit()
    }

    fun replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment
    ) {
        handleFragment(containerViewId, fragment, true)
    }

    fun removeAllFragments() {
        fragmentManager = (context as AppCompatActivity?)!!.supportFragmentManager
        while (fragmentManager!!.backStackEntryCount != 0) {
            fragmentManager!!.popBackStackImmediate()
        }
    }

    fun replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment, sharedView: View
    ) {
        fragmentManager = (context as AppCompatActivity?)!!.supportFragmentManager
        if (fragmentManager!!.backStackEntryCount == 0) {
            addFragment(containerViewId, fragment)
        }
        val ft = fragmentManager!!.beginTransaction()
        ft.replace(containerViewId, fragment, fragment.tag)
            .addToBackStack(fragment.tag)
            .addSharedElement(sharedView, sharedView.transitionName)
            .commit()
    }

    fun replaceFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment, sharedViews: HashMap<String?, View?>
    ) {
        fragmentManager = (context as AppCompatActivity?)!!.supportFragmentManager
        var ft = fragmentManager!!.beginTransaction()
        ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        ft = ft.replace(containerViewId, fragment, fragment.tag)
            .addToBackStack(fragment.tag)
        for (name in sharedViews.keys) {
            val view = sharedViews[name]
            if (view != null) {
                Log.d("*** TRANS %s", name!!)
                ft = ft.addSharedElement(view, name)
            }
        }
        ft.commit()
    }

    fun pushFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment
    ) {
        handleFragment(containerViewId, fragment, false)
    }

    fun addFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment
    ) {
        fragmentManager!!.beginTransaction()
            .add(containerViewId, fragment, fragment.tag)
            .disallowAddToBackStack()
            .commit()
    }
}
