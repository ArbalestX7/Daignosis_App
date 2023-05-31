package com.daignosis.daignosis.data.api

import androidx.viewbinding.BuildConfig
import com.daignosis.daignosis.utils.AuthInterceptor
import com.daignosis.daignosis.utils.UserPref
import com.daignosis.daignosis.utils.Util.base_Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val loggingInterceptor = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun getAuthApiServices(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

    fun getApiServices(userPreference: UserPref): ApiService {
        val clientWithAuthInterceptor = client.newBuilder()
            .addInterceptor(AuthInterceptor(userPreference))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientWithAuthInterceptor)
            .build()

        return retrofit.create(ApiService::class.java)
    }
}