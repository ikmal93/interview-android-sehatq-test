package com.iproject.sehatqtest.features.home.repository.local

import com.iproject.sehatqtest.storage.category.Category
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable

interface HomeLocalRepo {
    fun getAllCategories(): Observable<List<Category>>
    fun getAllProduct(): Observable<List<ProductPromo>>
    fun insertAllCategories(categories: List<Category>)
    fun insertAllPromo(promos: List<ProductPromo>)
}