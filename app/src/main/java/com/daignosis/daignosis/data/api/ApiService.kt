package com.daignosis.daignosis.data.api

import com.daignosis.daignosis.data.response.*
import retrofit2.Call
import retrofit2.http.*

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

    @FormUrlEncoded
    @POST("/user/forgot-password")
    fun postForgotpw(
        @Field("username") username: String
    ): Call<ForgotResponse>

    @GET("article/data")
    fun getAllArticle(): Call<ArticleResponse>

    @GET("article/data?limit=5")
    fun getMainArticle(): Call<ArticleResponse>

    @POST("user/get-profile")
    fun getProfileUser(
        @Header("Authorization") token: String
    ): Call<ProfileResponse>
}