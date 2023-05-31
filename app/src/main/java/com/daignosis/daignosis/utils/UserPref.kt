package com.daignosis.daignosis.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.daignosis.daignosis.data.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPref private constructor(private val dataStore: DataStore<Preferences>) {

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY] ?: ""
        }
    }

    fun getUser(): Flow<UserEntity> {
        return dataStore.data.map { preferences ->
            UserEntity(
                preferences[NAME_KEY] ?: "",
                preferences[EMAIL_KEY] ?: "",
                preferences[PASSWORD_KEY] ?: "",
                preferences[STATE_KEY] ?: false,
                preferences[TOKEN_KEY] ?: ""
            )
        }
    }

    suspend fun saveUser(user: UserEntity) {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = user.name
            preferences[EMAIL_KEY] = user.email
            preferences[PASSWORD_KEY] = user.password
            preferences[STATE_KEY] = user.isLogin
            preferences[TOKEN_KEY] = user.token
        }
    }


    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[NAME_KEY] = ""
            preferences[EMAIL_KEY] = ""
            preferences[PASSWORD_KEY] = ""
            preferences[STATE_KEY] = false
            preferences[TOKEN_KEY] = ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPref? = null

        private val NAME_KEY = stringPreferencesKey("name")
        private val EMAIL_KEY = stringPreferencesKey("email")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val STATE_KEY = booleanPreferencesKey("state")
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