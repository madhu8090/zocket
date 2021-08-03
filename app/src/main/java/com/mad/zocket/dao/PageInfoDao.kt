package com.mad.zocket.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mad.zocket.model.PageInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PageInfoDao {

    @Insert
    suspend fun insert(pageInfo: PageInfo?)

    @Update
    suspend fun update(pageInfo: PageInfo?)

    @Delete
    suspend fun delete(pageInfo: PageInfo?)

    @Query("DELETE FROM page_info_table")
    suspend fun deleteAllPages()

    @Query("SELECT * FROM page_info_table")
    fun getAllPages(): Flow<List<PageInfo>>

//    @Query("SELECT * FROM page_info_table WHERE id = :id")
//    suspend fun getPageById(id: Long): LiveData<PageInfo>
}