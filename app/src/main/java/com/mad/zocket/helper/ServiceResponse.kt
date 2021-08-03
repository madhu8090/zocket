package com.mad.zocket.helper

class ServiceResponse<T> {
    private var result: T? = null
    private var error: ServiceErrorHandler? = null

    private fun ServiceResponse(result: T) {
        this.result = result
        error = null
    }

    private fun ServiceResponse(error: ServiceErrorHandler) {
        result = null
        this.error = error
    }

    fun success(result: T) {
        return ServiceResponse(result)
    }

    fun error(error: ServiceErrorHandler) {
        return ServiceResponse(error)
    }

    fun isSuccess(): Boolean {
        return error == null
    }

    interface Listener<T> {
        fun onResponse(response: T)
    }

    interface ErrorListener {
        fun onErrorResponse(errorHandler: ServiceErrorHandler)
    }
}