package com.iproject.sehatqtest.features.product.ui

import android.util.Log
import com.google.gson.Gson
import com.iproject.sehatqtest.features.product.repository.search.SearchLocalRepoImpl
import com.iproject.sehatqtest.features.product.repository.search.SearchRepositoryImpl
import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class ProductSearchPresenter(private val searchView: ProductSearchContract.View,
                             dbHelper: DbHelper): ProductSearchContract.Presenter {

    private val searchLocalRepo = SearchLocalRepoImpl(dbHelper)
    private val searchRepository = SearchRepositoryImpl(searchLocalRepo)
    private val compositeDisposable = CompositeDisposable()

    override fun getAll() {
        searchRepository.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<ProductPromo>> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                        searchView.showLoading()
                        compositeDisposable.add(d)
                    }

                    override fun onNext(t: List<ProductPromo>) {
                        searchView.showData(t)
                        Log.d("IKMAL", "HAHA : " + Gson().toJson(t))
                    }

                    override fun onError(e: Throwable) {
                        searchView.showError()
                        Log.d("IKMAL", "HAHA : " + e.localizedMessage)
                    }

                })
    }

    override fun onViewDestroyed() {
        compositeDisposable.dispose()
    }
}