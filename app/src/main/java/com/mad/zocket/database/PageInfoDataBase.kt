package com.mad.zocket.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mad.zocket.dao.PageInfoDao
import com.mad.zocket.model.PageInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [PageInfo::class], version = 1, exportSchema = false)
abstract class PageInfoDataBase : RoomDatabase() {

    abstract fun pageInfoDao(): PageInfoDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PageInfoDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PageInfoDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PageInfoDataBase::class.java,
                    "page_info_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(RoomCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class RoomCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        /**
         * Override the onCreate method to populate the database.
         */
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // If you want to keep the data through app restarts,
            // comment out the following line.
            INSTANCE?.let { _ ->
                scope.launch(Dispatchers.IO) {
                }
            }
        }
    }

}