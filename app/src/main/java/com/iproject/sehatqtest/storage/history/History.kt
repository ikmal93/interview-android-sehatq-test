package com.iproject.sehatqtest.storage.history

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = "",
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "image_url")
    var imageUrl: String = "",
    @ColumnInfo(name = "loved")
    var loved: Int = 0,
    @ColumnInfo(name = "price")
    var price: String = "",
    @ColumnInfo(name = "title")
    var title: String = ""

)