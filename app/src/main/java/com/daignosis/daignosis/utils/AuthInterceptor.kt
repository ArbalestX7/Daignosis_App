package com.daignosis.daignosis.utils

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val userPref: UserPref) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = runBlocking {
            userPref.getToken().first()
        }

        requestBuilder.addHeader("Authorization", "Bearer $token")

        return chain.proceed(requestBuilder.build())
    }
}