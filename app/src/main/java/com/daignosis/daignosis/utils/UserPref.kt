package com.daignosis.daignosis.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.daignosis.daignosis.data.response.Data
import com.daignosis.daignosis.data.response.DataUser
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


    suspend fun saveUserData(data: DataUser){
        dataStore.edit { pref ->
            pref[USER_ID] = data.userId
            pref[USERNAME_KEY] = data.username
            pref[FULLNAME_KEY] = data.fullName
            pref[PHONE_KEY] = data.phoneNumber
            pref[EMAIL_KEY] = data.email
            pref[BIRTHDAY_KEY] = data.birthday
            pref[ADDRESS_KEY] = data.address
            pref[CITY_KEY] = data.city
            pref[PROVINCE] = data.province
            pref[POST_CODE] = data.postalCode
            pref[COUNTRY_KEY] = data.country
            pref[PHOTO_KEY] = data.photoProfile
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

        private val ADDRESS_KEY = stringPreferencesKey("address")
        private val CITY_KEY = stringPreferencesKey("city")
        private val PROVINCE = stringPreferencesKey("province")
        private val POST_CODE = intPreferencesKey("postal_code")
        private val COUNTRY_KEY = stringPreferencesKey("country")

        fun getInstance(dataStore: DataStore<Preferences>): UserPref {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPref(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}