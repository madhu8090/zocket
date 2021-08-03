package com.mad.zocket.reopsitory

import com.mad.zocket.api.ApiHelper

class ApiRepository(private val apiHelper: ApiHelper) {

    suspend fun getPageInfo(userId: String) = apiHelper.getPageInfo(userId)
    suspend fun getPageImage(pageId: String) = apiHelper.getPageImage(pageId)

}