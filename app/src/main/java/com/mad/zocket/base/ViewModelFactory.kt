package com.mad.zocket.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mad.zocket.api.ApiHelper
import com.mad.zocket.reopsitory.ApiRepository
import com.mad.zocket.viewmodel.LoginViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(ApiRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

