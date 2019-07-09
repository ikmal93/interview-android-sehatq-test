package com.iproject.sehatqtest.rest

import android.util.Log
import com.google.gson.Gson
import com.iproject.sehatqtest.BuildConfig
import com.iproject.sehatqtest.rest.response.DataResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RestImpl {
    companion object {

        private const val BASE_URL = "https://private-4639ce-ecommerce56.apiary-mock.com//"

        fun getInstance(): RestImpl {
            return RestImpl()
        }

        private var restInstance: Rest? = null
        private val restTransport: Rest?
            get() {
                if (restInstance == null)
                    restInstance = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(httpClient())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(Rest::class.java)
                return restInstance
            }

        private fun httpClient(): OkHttpClient {
            val logger = HttpLoggingInterceptor()

            if (BuildConfig.DEBUG)
                logger.level = HttpLoggingInterceptor.Level.BODY
            else
                logger.level = HttpLoggingInterceptor.Level.NONE

            return OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
        }

    }

    fun getData(): Observable<List<DataResponse>> {
        return Observable.create { emitter ->
            val groupCall = restTransport?.getData()
            groupCall?.enqueue(object : Callback<List<DataResponse>> {
                override fun onResponse(call: Call<List<DataResponse>>, response: Response<List<DataResponse>>) {
                    val dataResponse = response.body()
                    Log.d("IKMAL", "LALA : " + Gson().toJson(dataResponse))
                    if (dataResponse != null) emitter.onNext(dataResponse)
                    else emitter.onError(Exception("Unexpecteds Condition"))
                }

                override fun onFailure(call: Call<List<DataResponse>>, t: Throwable) {
                    emitter.onError(Exception(t.localizedMessage))
                }



            })
        }
    }


}