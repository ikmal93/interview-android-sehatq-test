package com.iproject.sehatqtest.features.product.repository.detail

import com.iproject.sehatqtest.storage.history.History
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductRepositoryImpl(private val productLocalRepo: ProductLocalRepoImpl) : ProductRepository {
    override fun getProduct(id: String?): Observable<ProductPromo> {
        return productLocalRepo.getProduct(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insert(history: History): Single<String> {
        return productLocalRepo.insert(history)
    }
}