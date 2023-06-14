package com.daignosis.daignosis.data.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.daignosis.daignosis.data.api.ApiService
import com.daignosis.daignosis.data.response.*
import com.daignosis.daignosis.utils.UserPref
import com.daignosis.daignosis.utils.Result
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DaignosisRepository (
    private val apiService: ApiService,
    private val pref: UserPref
){

    fun getSession(): LiveData<DataSession>{
        return pref.getSessionId().asLiveData()
    }
    fun getToken(): LiveData<DataLogin>{
        return pref.getToken().asLiveData()
    }

    fun logout(){
        MainScope().launch {
            pref.logout()
        }
    }

    fun rmvSession(){
        MainScope().launch {
            pref.rmvSessionId()
        }
    }

    fun login (email: String, password: String): LiveData<Result<Boolean>> {
        val login = MutableLiveData<Result<Boolean>>()
        login.value = Result.Loading

        val client = apiService.postLogin(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error){
                        login.value = Result.Success(true)
                        MainScope().launch { pref.saveTokenUser(responseBody.dataLogin) }
                    } else {
                        login.value = Result.Error("Error")
                        Log.e(ContentValues.TAG, "onResponse(E): ${response.message()}" )
                    }
                } else {
                    login.value = Result.Error("Error")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                login.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure(T): ${t.message}")
            }
        })
        return login
    }

    fun register(
        username: String, email: String, password: String
    ):LiveData<Result<Boolean>> {
        val register = MutableLiveData<Result<Boolean>>()
        register.value = Result.Loading

        val client = apiService.postRegister(username, email, password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error){
                        register.value = Result.Success(true)
                    } else {
                        register.value = Result.Error("Error")
                        Log.e(ContentValues.TAG, "onResponse(E): ${response.message()}" )
                    }
                } else {
                    register.value = Result.Error("Error")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                register.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure(T): ${t.message}")
            }
        })
        return register
    }

    fun forgotPw (username: String): LiveData<Result<Boolean>> {
        val forgot = MutableLiveData<Result<Boolean>>()

        forgot.value = Result.Loading
        val client = apiService.postForgotpw(username)
        client.enqueue(object: Callback<ForgotResponse>{
            override fun onResponse(
                call: Call<ForgotResponse>,
                response: Response<ForgotResponse>,
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        forgot.value = Result.Success(true)
                    } else {
                        forgot.value = Result.Error("Error")
                        Log.e(ContentValues.TAG, "onResponse: Fail ${response.message()}" )
                    }
                } else {
                    forgot.value = Result.Error("Error")
                    Log.e(ContentValues.TAG, "onResponse: isError ${response.message()}" )
                }
            }

            override fun onFailure(call: Call<ForgotResponse>, t: Throwable) {
                forgot.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure: ${t.message.toString()}" )
            }
        })
        return forgot
    }

    fun getListArticle(): Pair<LiveData<Result<Boolean>>, LiveData<List<DataItem>>>{
        val allArticle = MutableLiveData<List<DataItem>>()
        val progress = MutableLiveData<Result<Boolean>>()

        progress.value = Result.Loading
        val client = apiService.getAllArticle()
        client.enqueue(object: Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        allArticle.value = responseBody.data
                        progress.value = Result.Success(true)
                    }else{
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                        progress.value = Result.Error("Error")
                    }
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                progress.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
        return Pair(progress,allArticle)
    }

    fun getArticleLimit(): Pair<LiveData<Result<Boolean>>, LiveData<List<DataItem>>>{
        val allArticle = MutableLiveData<List<DataItem>>()
        val progress = MutableLiveData<Result<Boolean>>()

        progress.value = Result.Loading
        val client = apiService.getMainArticle()
        client.enqueue(object: Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        allArticle.value = responseBody.data
                        progress.value = Result.Success(true)
                    }else{
                        Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                        progress.value = Result.Error("Error")
                    }
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                progress.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }

        })
        return Pair(progress,allArticle)
    }

    fun getUserProfile(token: String): Pair<LiveData<Result<Boolean>>, LiveData<ProfileResponse>>{
        val user = MutableLiveData<ProfileResponse>()
        val progress = MutableLiveData<Result<Boolean>>()

        progress.value = Result.Loading
        val client = apiService.getProfileUser("Bearer $token")
        client.enqueue(object: Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null && !responseBody.error){
                        user.postValue(responseBody!!)
                        progress.value = Result.Success(true)
                    }else{
                        progress.value = Result.Error("Error: Fail")
                        Log.e(ContentValues.TAG, "onFailure1: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                progress.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure2: ${t.message}")
            }
        })
        return Pair(progress, user)
    }

    fun editProfileUser(
        token: String, userId: String?, username: String?,
        full_name: String?, phone_number: String?,email: String?,
        birthday: String?, address: String?, city: String?,
        province: String?, postal_code: Int, country: String?
    ): LiveData<Result<Boolean>> {
        val edit = MutableLiveData<Result<Boolean>>()
        edit.value = Result.Loading

        val client = apiService.editProfileUser(
                "Bearer $token",userId,username,
                full_name,phone_number, email, birthday,
                address, city, province, postal_code, country
            )
        client.enqueue(object : Callback<EditProfileResponse>{
            override fun onResponse(
                call: Call<EditProfileResponse>,
                response: Response<EditProfileResponse>,
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error) {
                        edit.value = Result.Success(true)
                    } else {
                        edit.value = Result.Error("Error Response")
                        Log.d(ContentValues.TAG, "onResponse: Error ${response.message()}")
                    }
                } else {
                    edit.value = Result.Error("Error")
                }
            }

            override fun onFailure(call: Call<EditProfileResponse>, t: Throwable) {
                edit.value = Result.Error("onFailure")
                Log.d(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
        return edit
    }

    fun newSession(token: String): LiveData<Result<Boolean>> {
        val session = MutableLiveData<Result<Boolean>>()
        session.value = Result.Loading

        val client = apiService.newSessions("Bearer $token")
        client.enqueue(object : Callback<NewSessionResponse>{
            override fun onResponse(
                call: Call<NewSessionResponse>,
                response: Response<NewSessionResponse>,
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error){
                        session.value = Result.Success(true)
                        MainScope().launch { pref.saveSessionId(responseBody.dataSession)}
                    } else {
                        session.value = Result.Error("Error")
                        Log.e(ContentValues.TAG, "onResponse: Error ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<NewSessionResponse>, t: Throwable) {
                session.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
        return session
    }

    fun message(
        token: String, message: String, sessionId: String
    ): Pair<LiveData<Result<Boolean>>, LiveData<List<Data>>>{
        val msg = MutableLiveData<Result<Boolean>>()
        val data = MutableLiveData<List<Data>>()

        msg.value = Result.Loading
        val client = apiService.message("Bearer $token",message, sessionId)
        client.enqueue(object : Callback<MessageResponse>{
            override fun onResponse(
                call: Call<MessageResponse>,
                response: Response<MessageResponse>,
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null && !responseBody.error){
                        data.value = listOf(responseBody.data)
                        msg.value = Result.Success(true)
                    } else {
                        msg.value = Result.Error("Error")
                        Log.e(ContentValues.TAG, "onResponse: Error ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<MessageResponse>, t: Throwable) {
                msg.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
        return Pair(msg,data)
    }

    fun historySession(
        token: String
    ): Pair<LiveData<Result<Boolean>>, LiveData<List<DataHistory>>>{
        val history = MutableLiveData<Result<Boolean>>()
        val data = MutableLiveData<List<DataHistory>>()

        history.value = Result.Loading
        val client = apiService.sessionHistory("Bearer $token")
        client.enqueue(object : Callback<HistoryResponse>{
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>,
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        data.value = responseBody.dataHistory
                        history.value = Result.Success(true)
                    } else {
                        history.value = Result.Error("Error")
                        Log.e(ContentValues.TAG, "onResponse: Error ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {
                history.value = Result.Error("Error")
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
        return Pair(history,data)
    }
}