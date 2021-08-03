package com.mad.zocket.model

import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Paging {

    @SerializedName("cursors")
    @Expose
    @TypeConverters(CursorConvertor::class)
    private var cursors: Cursors? = null

    fun getCursors(): Cursors? {
        return cursors
    }

    fun setCursors(cursors: Cursors?) {
        this.cursors = cursors
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
