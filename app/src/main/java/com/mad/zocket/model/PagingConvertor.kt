package com.mad.zocket.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PagingConvertor {
    @TypeConverter
    fun storedStringToMyObjects(data: String?): Paging? {
        val gson = Gson()
        if (data == null) {
            return Paging()
        }
        val type = object : TypeToken<Paging?>() {}.type
        return gson.fromJson<Paging>(data, type)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: Paging?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}
