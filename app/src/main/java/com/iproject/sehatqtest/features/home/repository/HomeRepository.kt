package com.iproject.sehatqtest.features.home.repository

import com.iproject.sehatqtest.rest.response.DataResponse
import com.iproject.sehatqtest.storage.category.Category
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable

interface HomeRepository {
    fun getData(): Observable<List<DataResponse>>
    fun insertAllCategories(categories: List<Category>)
    fun insertAllPromo(promos: List<ProductPromo>)
}