package com.iproject.sehatqtest.storage.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductPromoDAO {
    @Query("SELECT * FROM product_promo")
    fun getAll(): List<ProductPromo>

    @Query("SELECT * FROM product_promo WHERE id = :id")
    fun getProduct(id: String?): ProductPromo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductPromo>)
}