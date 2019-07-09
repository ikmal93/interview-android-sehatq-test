package com.iproject.sehatqtest.features.home.repository.local

import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.category.Category
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observable

class HomeLocalRepoImpl(private val dbHelper: DbHelper): HomeLocalRepo {
    override fun getAllCategories(): Observable<List<Category>> {
        return Observable.create{ emitter ->
            dbHelper.categoryDAO().getAll().also {
                if (it.isNotEmpty()) emitter.onNext(it)
            }

        }
    }

    override fun insertAllCategories(categories: List<Category>) {
        dbHelper.categoryDAO().insertAll(categories)
    }

    override fun getAllProduct(): Observable<List<ProductPromo>> {
        return Observable.create{ emitter ->
            dbHelper.productPromoDAO().getAll().also {
                if (it.isNotEmpty()) emitter.onNext(it)
            }

        }
    }

    override fun insertAllPromo(promos: List<ProductPromo>) {
        dbHelper.productPromoDAO().insertAll(promos)
    }
}