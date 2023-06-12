package com.daignosis.daignosis.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.daignosis.daignosis.data.response.Data
import com.daignosis.daignosis.data.response.DataLogin
import com.daignosis.daignosis.data.response.DataSession
import com.daignosis.daignosis.data.response.DataUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPref private constructor(private val dataStore: DataStore<Preferences>) {

    fun getToken(): Flow<DataLogin> {
        return dataStore.data.map { pref ->
            DataLogin(
                pref[BIRTHDAY_KEY] ?:"",
                pref[FULLNAME_KEY] ?:"",
                pref[USER_ID] ?:"",
                pref[PHOTO_KEY] ?:"",
                pref[PHONE_KEY] ?:"",
                pref[EMAIL_KEY] ?:"",
                pref[USERNAME_KEY] ?:"",
                pref[TOKEN_KEY] ?:""
            )
        }
    }

    suspend fun saveTokenUser(user: DataLogin){
        dataStore.edit { preference ->
            preference[BIRTHDAY_KEY] = user.birthday
            preference[FULLNAME_KEY] = user.fullName
            preference[USER_ID] = user.userId
            preference[PHOTO_KEY] = user.photoProfile
            preference[PHONE_KEY] = user.phoneNumber
            preference[EMAIL_KEY] = user.email
            preference[USERNAME_KEY] = user.username
            preference[TOKEN_KEY] = user.token
        }
    }

    suspend fun saveSessionId(session: DataSession){
        dataStore.edit { pref ->
            pref[SESSION_ID] = session.sessionId
        }
    }

    fun getSessionId():Flow<DataSession>{
        return dataStore.data.map { pref ->
            DataSession(
                pref[SESSION_ID] ?:""
            )
        }
    }

    suspend fun logout(){
        dataStore.edit { pref ->
            pref[TOKEN_KEY] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPref? = null
        private val TOKEN_KEY = stringPreferencesKey("token")

        private val USER_ID = stringPreferencesKey("userId")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val FULLNAME_KEY = stringPreferencesKey("fullname")
        private val PHONE_KEY = stringPreferencesKey("phone")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val BIRTHDAY_KEY = stringPreferencesKey("birthday")
        private val PHOTO_KEY = stringPreferencesKey("photo")

        private val SESSION_ID = stringPreferencesKey("sessionId")


        fun getInstance(dataStore: DataStore<Preferences>): UserPref {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPref(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}