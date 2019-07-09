package com.iproject.sehatqtest.features.home.repository.remote

import com.iproject.sehatqtest.rest.response.DataResponse
import io.reactivex.Observable

interface HomeRemoteRepo {
    fun getData(): Observable<List<DataResponse>>
}