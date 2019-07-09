package com.iproject.sehatqtest.rest

import com.iproject.sehatqtest.rest.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface Rest {
    @GET("home")
    fun getData(): Call<List<DataResponse>>
}