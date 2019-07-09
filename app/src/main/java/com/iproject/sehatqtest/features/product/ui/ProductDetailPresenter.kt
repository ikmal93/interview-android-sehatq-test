package com.iproject.sehatqtest.features.product.ui

import android.util.Log
import com.google.gson.Gson
import com.iproject.sehatqtest.features.product.repository.detail.ProductLocalRepoImpl
import com.iproject.sehatqtest.features.product.repository.detail.ProductRepositoryImpl
import com.iproject.sehatqtest.storage.DbHelper
import com.iproject.sehatqtest.storage.history.History
import com.iproject.sehatqtest.storage.product.ProductPromo
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductDetailPresenter(private val detailView: ProductDetailContract.View, dbHelper: DbHelper):
    ProductDetailContract.Presenter {
    private val productLocalDataSource = ProductLocalRepoImpl(dbHelper)
    private val productRepository = ProductRepositoryImpl(productLocalDataSource)
    private val compositeDisposable = CompositeDisposable()

    override fun getProduct(id: String?) {
        productRepository.getProduct(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<ProductPromo> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    detailView.showLoading()
                    compositeDisposable.add(d)
                }

                override fun onNext(t: ProductPromo) {
                    detailView.showData(t)
                    Log.d("IKMAL", "HAHA : " + Gson().toJson(t))
                }

                override fun onError(e: Throwable) {
                    detailView.showError()
                    Log.d("IKMAL", "HAHA : " + e.localizedMessage)
                }

            })
    }

    override fun insert(history: History) {
        productRepository.insert(history)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<String>{
                override fun onSuccess(t: String) {
                    Log.d("IKMAL", t)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

            })
    }

    override fun onViewDetached() {
        compositeDisposable.dispose()
    }
}