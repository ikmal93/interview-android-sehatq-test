package com.iproject.sehatqtest.features.product.repository.search

import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable

class SearchLocalRepoImpl(private val dbHelper: DbHelper) : SearchLocalRepo {
    override fun getAll(): Observable<List<ProductPromo>> {
        return Observable.fromCallable {
            dbHelper.productPromoDAO().getAll()
        }
    }
}