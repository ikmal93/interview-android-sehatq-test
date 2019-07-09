package com.iproject.sehatqtest.features.home.repository.remote

import com.iproject.sehatqtest.rest.RestImpl
import com.iproject.sehatqtest.rest.response.DataResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeRemoteRepoImpl(private val restImpl: RestImpl) :
    HomeRemoteRepo {
    override fun getData(): Observable<List<DataResponse>> {
        return restImpl.getData()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
    }
}