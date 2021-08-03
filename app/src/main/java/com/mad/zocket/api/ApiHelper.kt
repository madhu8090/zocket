package com.mad.zocket.api

import com.mad.zocket.helper.PrefKeys
import com.mad.zocket.helper.Prefs

class ApiHelper(private val apiService: ApiService) {

    suspend fun getPageInfo(userId: String) =
        apiService.getPageInfo(userId, getToken())

    suspend fun getPageImage(pageId: String) =
        apiService.getPagePicture(pageId, getToken())

    private fun getToken(): String {
        return Prefs.getString(PrefKeys.USER_ACCESS_TOKEN)
    }

}