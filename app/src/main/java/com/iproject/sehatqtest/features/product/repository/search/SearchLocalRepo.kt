package com.iproject.sehatqtest.features.product.repository.search

import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable

interface SearchLocalRepo {
    fun getAll(): Observable<List<ProductPromo>>
}