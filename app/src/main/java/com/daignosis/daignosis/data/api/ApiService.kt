package com.daignosis.daignosis.data.api

import com.daignosis.daignosis.data.model.LoginModel
import com.daignosis.daignosis.data.model.RegisterModel
import com.daignosis.daignosis.data.response.LoginResponse
import com.daignosis.daignosis.data.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("/user/login")
    fun postLogin(
        @Body loginModel: LoginModel
    ): LoginResponse

    @POST("/user/register")
    fun postRegister(
        @Body registerModel: RegisterModel
    ): RegisterResponse
}