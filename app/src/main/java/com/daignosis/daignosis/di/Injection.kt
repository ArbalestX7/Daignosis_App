package com.daignosis.daignosis.di

import android.content.Context
import com.daignosis.daignosis.data.api.ApiConfig
import com.daignosis.daignosis.data.repository.DaignosisRepository
import com.daignosis.daignosis.ui.login.dataStore
import com.daignosis.daignosis.utils.UserPref

object Injection {
    fun provideRepository(context: Context): DaignosisRepository {
        val apiService = ApiConfig.getAuthApiServices()
        val preference = UserPref.getInstance(context.dataStore)
        return DaignosisRepository(apiService, preference)
    }
}