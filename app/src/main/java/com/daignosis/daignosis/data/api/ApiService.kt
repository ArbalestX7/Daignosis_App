package com.daignosis.daignosis.data.api

import com.daignosis.daignosis.data.model.LoginModel
import com.daignosis.daignosis.data.model.RegisterModel
import com.daignosis.daignosis.data.response.LoginResponse
import com.daignosis.daignosis.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("/user/login")
    fun postLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("/user/register")
    fun postRegister(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>
}