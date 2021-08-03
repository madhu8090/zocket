package com.mad.zocket.helper

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.facebook.FacebookSdk.getApplicationContext
import com.mad.zocket.App

object Prefs {

    var sharedPref: SharedPreferences? = null

    @JvmName("getSharedPref1")
    private fun getSharedPref(): SharedPreferences? {
        if (sharedPref == null) {
            sharedPref = PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext()
            )
        }
        return sharedPref
    }

    private val editor: SharedPreferences.Editor
        get() = getSharedPref()!!.edit()

    fun getString(key: String?): String {
        return getSharedPref()!!.getString(key, null).toString()
    }

    fun getBoolean(key: String?): Boolean {
        return getSharedPref()!!.getBoolean(key, false)
    }

    fun getInt(key: String?): Int {
        return getSharedPref()!!.getInt(key, 0)
    }

    fun getLong(key: String?): Long {
        return getSharedPref()!!.getLong(key, 0)
    }

    fun setString(key: String?, value: String?) {
        editor.putString(key, value).apply()
    }

    fun setBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun setInt(key: String?, value: Int) {
        editor.putInt(key, value).apply()
    }

    fun setLong(key: String?, value: Long) {
        editor.putLong(key, value).apply()
    }
}