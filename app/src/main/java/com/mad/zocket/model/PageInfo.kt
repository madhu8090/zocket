package com.mad.zocket.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "page_info_table")
class PageInfo {

    @PrimaryKey(autoGenerate = true)
    private var id = 0

    @SerializedName("data")
    @Expose
    @TypeConverters(DataConvertor::class)
    private var data: List<Datum?>? = null

    @SerializedName("paging")
    @Expose
    @TypeConverters(PagingConvertor::class)
    private var paging: Paging? = null

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    fun getData(): List<Datum> {
        return data as List<Datum>
    }

    fun setData(data: List<Datum?>?) {
        this.data = data
    }

    fun getPaging(): Paging? {
        return paging
    }

    fun setPaging(paging: Paging?) {
        this.paging = paging
    }

    override fun toString(): String {
        return Gson().toJson(this)
    }

}