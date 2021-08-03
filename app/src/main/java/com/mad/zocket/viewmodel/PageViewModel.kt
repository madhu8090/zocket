package com.mad.zocket.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.mad.zocket.api.ApiHelper
import com.mad.zocket.database.PageInfoDataBase
import com.mad.zocket.model.PageInfo
import com.mad.zocket.reopsitory.PageInfoRepository
import kotlinx.coroutines.launch

class PageViewModel(private val repository: PageInfoRepository) : ViewModel() {


    // Using LiveData and caching what allPages returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allPages: LiveData<List<PageInfo>> = repository.allPages.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(pageInfo: PageInfo) = viewModelScope.launch {
        repository.insert(pageInfo)
        Log.i("TAG", "Page info inserted")
    }

}

class PageInfoViewModelFactory(private val repository: PageInfoRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}