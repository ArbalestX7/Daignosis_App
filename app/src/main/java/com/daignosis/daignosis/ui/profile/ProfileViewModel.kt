package com.daignosis.daignosis.ui.profile

import androidx.lifecycle.ViewModel
import com.daignosis.daignosis.data.repository.DaignosisRepository

class ProfileViewModel(private val repository: DaignosisRepository): ViewModel() {
    fun getToken() = repository.getToken()
    fun logout() = repository.logout()
    fun getUserProfile(token: String) = repository.getUserProfile(token)

    fun editProfile(
        token: String, userId: String?, username: String?,
        full_name: String?, phone_number: String?,email: String?,
        birthday: String?, address: String?, city: String?,
        province: String?, postal_code: Int, country: String?
    ) = repository.editProfileUser(
        token, userId, username, full_name,
        phone_number, email, birthday,
        address, city, province, postal_code, country
    )
}