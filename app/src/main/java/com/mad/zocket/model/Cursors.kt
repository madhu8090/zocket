package com.mad.zocket.model

import com.google.gson.Gson
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Cursors {

    @SerializedName("before")
    @Expose
    private var before: String? = null

    @SerializedName("after")
    @Expose
    private var after: String? = null

    fun getBefore(): String? {
        return before
    }

    fun setBefore(before: String?) {
        this.before = before
    }

    fun getAfter(): String? {
        return after
    }

    fun setAfter(after: String?) {
        this.after = after
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
