package com.ksballetba.rayplus.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

enum class NetworkType{
    AUTH,PROJECT
}

class RetrofitClient(public val networkType: NetworkType){
    private val DEFAULT_TIMEOUT:Long = 30
    public val AUTH_BASE_URL = "http://www.rayplus.top:81/"
    public val PROJECT_BASE_URL = "http://www.rayplus.top:82/"
    var mOkHttpClient:OkHttpClient? = null
    var mRetrofit:Retrofit? = null

    init {
        val gson = GsonBuilder().serializeNulls().create()
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()
        mRetrofit = Retrofit.Builder()
            .client(mOkHttpClient!!)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(if(networkType == NetworkType.AUTH) AUTH_BASE_URL else PROJECT_BASE_URL)
            .build()
    }

    companion object {
        @Volatile
        var sRetrofitClient:RetrofitClient? = null
        private fun initRetrofitClient(networkType: NetworkType):RetrofitClient{
            if(sRetrofitClient==null || sRetrofitClient?.networkType != networkType){
                synchronized(RetrofitClient::class.java){
                    if(sRetrofitClient==null || sRetrofitClient?.networkType != networkType){
                        sRetrofitClient = RetrofitClient(networkType)
                    }
                }
            }
            return sRetrofitClient!!
        }

        fun getInstance(networkType: NetworkType):Retrofit{
          return initRetrofitClient(networkType).mRetrofit!!
        }
    }
}