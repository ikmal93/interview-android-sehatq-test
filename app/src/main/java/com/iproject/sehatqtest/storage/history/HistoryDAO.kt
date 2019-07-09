package com.iproject.sehatqtest.storage.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDAO {
    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    @Insert
    fun insert(history: History)
}