package com.iproject.sehatqtest.features.product.repository.detail

import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.history.History
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable
import io.reactivex.Single

class ProductLocalRepoImpl(private val dbHelper: DbHelper): ProductLocalRepo {
    override fun getProduct(id: String?): Observable<ProductPromo> {
        return Observable.fromCallable {
            dbHelper.productPromoDAO().getProduct(id)
        }
    }

    override fun insert(history: History): Single<String> {
        return Single.create {
            dbHelper.historyDAO().insert(history)
        }
    }
}