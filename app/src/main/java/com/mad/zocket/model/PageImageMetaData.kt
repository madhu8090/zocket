package com.mad.zocket.model

import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PageImageMetaData {

    @SerializedName("height")
    @Expose
    private var height: Int? = null

    @SerializedName("is_silhouette")
    @Expose
    private var isSilhouette: Boolean? = null

    @SerializedName("url")
    @Expose
    private var url: String? = null

    @SerializedName("width")
    @Expose
    private var width: Int? = null

    fun getHeight(): Int? {
        return height
    }

    fun setHeight(height: Int?) {
        this.height = height
    }

    fun getIsSilhouette(): Boolean? {
        return isSilhouette
    }

    fun setIsSilhouette(isSilhouette: Boolean?) {
        this.isSilhouette = isSilhouette
    }

    fun getUrl(): String? {
        return url
    }

    fun setUrl(url: String?) {
        this.url = url
    }

    fun getWidth(): Int? {
        return width
    }

    fun setWidth(width: Int?) {
        this.width = width
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}