package com.mad.zocket.helper

import com.google.gson.Gson

class ServiceErrorHandler(var errorCode: Int, var errorMessage: String?, var errorBody: String?) {




//    fun getErrorCode(): Int {
//        return errorCode
//    }
//
//    fun getErrorMessage(): String? {
//        return if (errorMessage == null) "Exception error message is null" else errorMessage
//    }
//
//    fun getErrorBody(): String? {
//        return if (errorBody == null) "Exception error body is null" else errorBody
//    }

    override fun toString(): String {
        return Gson().toJson(this)
    }
}
