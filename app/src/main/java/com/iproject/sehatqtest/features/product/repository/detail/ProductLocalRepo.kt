package com.iproject.sehatqtest.features.product.repository.detail

import com.iproject.sehatqtest.storage.history.History
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable
import io.reactivex.Single

interface ProductLocalRepo {
    fun getProduct(id: String?): Observable<ProductPromo>
    fun insert(history: History): Single<String>
}