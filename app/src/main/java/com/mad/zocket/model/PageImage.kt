package com.mad.zocket.model

import com.google.gson.Gson
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class PageImage {

    @SerializedName("data")
    @Expose
    private var data: PageImageMetaData? = null

    fun getImageData(): PageImageMetaData? {
        return data
    }

    fun setImageData(data: PageImageMetaData?) {
        this.data = data
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}