package com.daignosis.daignosis.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.daignosis.daignosis.data.entity.UserEntity
import com.daignosis.daignosis.data.response.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPref private constructor(private val dataStore: DataStore<Preferences>) {

    fun getToken(): Flow<Data> {
        return dataStore.data.map { pref ->
            Data(
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

    suspend fun saveTokenUser(user: Data){
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

    suspend fun logout(){
        dataStore.edit { pref ->
            pref[TOKEN_KEY] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPref? = null

        private val BIRTHDAY_KEY = stringPreferencesKey("birthday")
        private val FULLNAME_KEY = stringPreferencesKey("fullname")
        private val USER_ID = stringPreferencesKey("userId")
        private val PHOTO_KEY = stringPreferencesKey("photo")
        private val PHONE_KEY = stringPreferencesKey("phone")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val TOKEN_KEY = stringPreferencesKey("token")

        fun getInstance(dataStore: DataStore<Preferences>): UserPref {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPref(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}