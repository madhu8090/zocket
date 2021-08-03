package com.mad.zocket.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CategoryConvertor {
    @TypeConverter
    fun storedStringToMyObjects(data: String?): List<Category?>? {
        val gson = Gson()
        if (data == null) {
            return emptyList<Category>()
        }
        val listType = object : TypeToken<List<Category?>?>() {}.type
        return gson.fromJson<List<Category?>>(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<Category?>?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

}
