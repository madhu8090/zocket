package com.mad.zocket.reopsitory

import androidx.annotation.WorkerThread
import com.mad.zocket.dao.PageInfoDao
import com.mad.zocket.model.PageInfo
import kotlinx.coroutines.flow.Flow

class PageInfoRepository(private val pageInfoDao: PageInfoDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allPages: Flow<List<PageInfo>> = pageInfoDao.getAllPages()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(pageInfo: PageInfo) {
        pageInfoDao.insert(pageInfo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(pageInfo: PageInfo) {
        pageInfoDao.delete(pageInfo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(pageInfo: PageInfo) {
        pageInfoDao.update(pageInfo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        pageInfoDao.deleteAllPages()
    }
}