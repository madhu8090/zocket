package com.mad.zocket.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mad.zocket.reopsitory.ApiRepository
import com.mad.zocket.utils.Resource
import kotlinx.coroutines.Dispatchers

class LoginViewModel(private val repository: ApiRepository) : ViewModel() {

    fun getPageInfo(userId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPageInfo(userId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getPageImage(pageId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getPageImage(pageId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}