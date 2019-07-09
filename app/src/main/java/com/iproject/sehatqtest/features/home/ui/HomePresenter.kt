package com.iproject.sehatqtest.features.home.ui

import android.util.Log
import com.google.gson.Gson
import com.iproject.sehatqtest.features.home.repository.HomeRepositoryImpl
import com.iproject.sehatqtest.features.home.repository.local.HomeLocalRepoImpl
import com.iproject.sehatqtest.features.home.repository.remote.HomeRemoteRepoImpl
import com.iproject.sehatqtest.rest.RestImpl
import com.iproject.sehatqtest.rest.response.DataResponse
import com.iproject.sehatqtest.storage.DbHelper
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class HomePresenter(private val homeView: HomeContract.View, dbHelper: DbHelper) :
    HomeContract.Presenter {

    private val restImpl = RestImpl.getInstance()
    private val homeRemoteDataSource = HomeRemoteRepoImpl(restImpl)
    private val homeLocalDataSource = HomeLocalRepoImpl(dbHelper)
    private val homeRepository = HomeRepositoryImpl(homeRemoteDataSource, homeLocalDataSource)
    private val compositeDisposable = CompositeDisposable()

    override fun getData() {
        homeRepository.getData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<DataResponse>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    homeView.showLoading()
                    compositeDisposable.add(d)
                }

                override fun onNext(t: List<DataResponse>) {
                    homeView.showData(t)
                    Log.d("IKMAL", "HAHA : " + Gson().toJson(t[0].data))
                }

                override fun onError(e: Throwable) {
                    homeView.showError()
                    Log.d("IKMAL", "HAHA : " + e.localizedMessage)
                }

            })
    }

    override fun onViewDetached() {
        compositeDisposable.dispose()
    }
}