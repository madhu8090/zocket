package com.mad.zocket.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConvertor {

    @TypeConverter
    fun storedStringToMyObjects(data: String?): List<Datum?>? {
        val gson = Gson()
        if (data == null) {
            return emptyList<Datum>()
        }
        val listType = object : TypeToken<List<Datum?>?>() {}.type
        return gson.fromJson<List<Datum?>>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<Datum?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}
