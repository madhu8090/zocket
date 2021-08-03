package com.mad.zocket.model

import com.google.gson.Gson
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class PageDetails {

    @SerializedName("result")
    @Expose
    private var result: List<Result>? = null

    @SerializedName("status")
    @Expose
    private var status: String? = null

    fun getResult(): List<Result>? {
        return result
    }

    fun setResult(result: List<Result>) {
        this.result = result
    }

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}