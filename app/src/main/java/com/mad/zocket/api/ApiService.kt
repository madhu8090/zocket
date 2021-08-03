package com.mad.zocket.api

import com.mad.zocket.model.PageImage
import com.mad.zocket.model.PageInfo
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("{userId}/accounts")
    suspend fun getPageInfo(
        @Path("userId") userId: String?,
        @Query("access_token") accessToken: String
    ): PageInfo

    @GET("{pageId}/picture?redirect=0")
    suspend fun getPagePicture(
        @Path("pageId") userId: String?,
        @Query("access_token") accessToken: String
    ): PageImage


    @POST("fb_page_details")
    fun getPageDetails(): Call<Any>

}