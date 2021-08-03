package com.mad.zocket.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CursorConvertor {
    @TypeConverter
    fun storedStringToMyObjects(data: String?): Cursors? {
        val gson = Gson()
        if (data == null) {
            return Cursors()
        }
        val type = object : TypeToken<Cursors?>() {}.type
        return gson.fromJson<Cursors?>(data, type)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: Cursors?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}
