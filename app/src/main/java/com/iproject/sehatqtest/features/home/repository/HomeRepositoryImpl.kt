package com.iproject.sehatqtest.features.home.repository

import com.iproject.sehatqtest.features.home.repository.local.HomeLocalRepoImpl
import com.iproject.sehatqtest.features.home.repository.remote.HomeRemoteRepoImpl
import com.iproject.sehatqtest.rest.response.DataResponse
import com.iproject.sehatqtest.storage.category.Category
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeRepositoryImpl(private val homeRemoteRepo: HomeRemoteRepoImpl, private val homeLocalRepo: HomeLocalRepoImpl) :
    HomeRepository {

    override fun getData(): Observable<List<DataResponse>> {
        return homeRemoteRepo.getData().doOnNext {
            if (it[0].data.category.isNotEmpty()) {
                insertAllCategories(it[0].data.category)
            }
            if (it[0].data.productPromo.isNotEmpty()){
                insertAllPromo(it[0].data.productPromo)
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

    override fun insertAllCategories(categories: List<Category>) {
        return homeLocalRepo.insertAllCategories(categories)
    }

    override fun insertAllPromo(promos: List<ProductPromo>) {
        return homeLocalRepo.insertAllPromo(promos)
    }
}