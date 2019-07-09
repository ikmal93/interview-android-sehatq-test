package com.iproject.sehatqtest.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iproject.sehatqtest.storage.category.Category
import com.iproject.sehatqtest.storage.category.CategoryDAO
import com.iproject.sehatqtest.storage.history.History
import com.iproject.sehatqtest.storage.history.HistoryDAO
import com.iproject.sehatqtest.storage.product.ProductPromo
import com.iproject.sehatqtest.storage.product.ProductPromoDAO

@Database(entities = [Category::class, ProductPromo::class, History::class], version = 2)
abstract class DbHelper : RoomDatabase() {
    abstract fun categoryDAO(): CategoryDAO
    abstract fun productPromoDAO(): ProductPromoDAO
    abstract fun historyDAO(): HistoryDAO

    companion object {
        private var INSTANCE: DbHelper? = null

        fun getInstance(context: Context): DbHelper? {
            if (INSTANCE == null) {
                synchronized(DbHelper::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DbHelper::class.java, "sehatq.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }
    }
}