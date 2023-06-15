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

    @GET("article/data?limit=50")
    fun getAllArticle(): Call<ArticleResponse>

    @GET("article/data?limit=3")
    fun getMainArticle(): Call<ArticleResponse>

    @POST("user/get-profile")
    fun getProfileUser(
        @Header("Authorization") token: String
    ): Call<ProfileResponse>


    @FormUrlEncoded
    @PATCH("user/profile")
    fun editProfileUser(
        @Header("Authorization") token: String,
        @Field("user_id") userId: String?,
        @Field("username") username: String?,
        @Field("full_name") fullname: String?,
        @Field("phone_number") phone_number: String?,
        @Field("email") email: String?,
        @Field("birthday") birthday: String?,
        @Field("address") address: String?,
        @Field("city") city: String?,
        @Field("province") province: String?,
        @Field("postal_code") postal_code: Int?,
        @Field("country") country: String?
    ): Call<EditProfileResponse>

    @POST("sessions/new")
    fun newSessions(
        @Header("Authorization") token: String
    ): Call<NewSessionResponse>

    @POST("sessions")
    fun sessionHistory(
    @Header("Authorization") token: String
    ): Call<HistoryResponse>

    @FormUrlEncoded
    @POST("messages")
    fun message(
        @Header("Authorization") token: String,
        @Field("message") message: String,
        @Field("session_id") sessionId: String
    ): Call<MessageResponse>

    @POST("messages/{id}")
    fun getMsg(
        @Header("Authorization") token: String,
        @Path("id") sessionId: String
    ): Call<HistoryMsgResponse>
}