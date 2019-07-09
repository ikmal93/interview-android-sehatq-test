package com.iproject.sehatqtest.features.product.repository.search

import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepositoryImpl(private val searchLocalRepo: SearchLocalRepoImpl) : SearchRepository {
    override fun getAll(): Observable<List<ProductPromo>> {
        return searchLocalRepo.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}