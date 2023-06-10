package com.daignosis.daignosis.ui.profile

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.daignosis.daignosis.utils.Util.withDateFormat
import com.daignosis.daignosis.R
import com.daignosis.daignosis.databinding.ActivityProfileBinding
import com.daignosis.daignosis.ui.login.LoginActivity
import com.daignosis.daignosis.utils.Result
import com.daignosis.daignosis.utils.Util.withDateFormatProfile
import com.daignosis.daignosis.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var editTextBirthdate: TextInputEditText
    private lateinit var viewModel: ProfileViewModel
    private lateinit var username: String
    private lateinit var email: String
    private lateinit var photoProfile: String
    private lateinit var full_name: String
    private lateinit var phone_number: String
    private lateinit var birthday: String
    private lateinit var address: String
    private lateinit var city: String
    private lateinit var province: String
    private var postal_code: Int = 0
    private lateinit var postCode: String
    private lateinit var country: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        viewModel = ViewModelProvider(
            this, ViewModelFactory(this)
        )[ProfileViewModel::class.java]

        getProfile()

        editTextBirthdate = binding.edtProfileBirthdate
        editTextBirthdate.setOnClickListener{
            showDatePickerDialog()
        }
        binding.btnBackProfile.setOnClickListener { onBackPressed() }
        binding.btnLogout.setOnClickListener { logout() }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = formatDate(year, month, dayOfMonth)
                editTextBirthdate.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000
        datePickerDialog.show()
    }

    private fun formatDate(year: Int, month: Int, dayOfMonth: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
    }

    private fun logout(){
        val logoutDialog = AlertDialog.Builder(this)
        with(logoutDialog) {
            setTitle(resources.getString(R.string.logout))
            setMessage(resources.getString(R.string.log_desc))
            setPositiveButton(resources.getString(R.string.yes)) { _, _ ->
                viewModel.logout()
                intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            setNegativeButton(resources.getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = logoutDialog.create()
        alertDialog.show()
    }

    private fun getProfile(){
        viewModel.getToken().observe(this) { user ->
            viewModel.getUserProfile(user.token).first.observe(this){
                when(it) {
                    is Result.Loading -> {
                        binding.progressBar4.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar4.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                        binding.progressBar4.visibility = View.GONE
                    }
                }
            }
            viewModel.getUserProfile(user.token).second.observe(this){
                username = it.dataUser.username
                email = it.dataUser.email
                photoProfile = it.dataUser.photoProfile
                full_name = it.dataUser.fullName
                phone_number = it.dataUser.phoneNumber
                birthday = it.dataUser.birthday
                address = it.dataUser.address
                city = it.dataUser.city
                province = it.dataUser.province
                postCode = postal_code.toString()
                postCode = it.dataUser.postalCode.toString()
                country = it.dataUser.country


                binding.apply {
                    Glide.with(this@ProfileActivity)
                        .load(photoProfile)
                        .placeholder(R.drawable.holder)
                        .into(imgPhotoProfile)

                    tvProfileUsername.text = username
                    tvEmailProfile.text = email
                    edtProfileFullname.setText(full_name)
                    edtProfilePhonenum.setText(phone_number)
                    edtProfileBirthdate.setText(birthday)
                    edtProfileAddress.setText(address)
                    edtProfileCity.setText(city)
                    edtProfileProvince.setText(province)
                    edtProfilePostcode.setText(postCode)
                    edtProfileCountry.setText(country)

                    binding.btnSaveprofile.setOnClickListener {
                        editProfile(
                            user.token,user.userId,username, edtProfileFullname.text.toString(),
                            edtProfilePhonenum.text.toString(), email,
                            edtProfileBirthdate.text.toString(), edtProfileAddress.text.toString(),
                            edtProfileCity.text.toString(), edtProfileProvince.text.toString(),
                            edtProfilePostcode.text.toString().toInt(),
                            edtProfileCountry.text.toString()
                        )
                    }
                }
            }
        }
    }

    private fun editProfile(
        token: String, userId: String, username: String,
        full_name: String, phone_number: String,email: String,
        birthday: String, address: String, city: String,
        province: String, postal_code: Int, country: String
    ){
        viewModel.editProfile(
            token,userId, username, full_name, phone_number,
            email, birthday, address, city, province, postal_code, country
        ).observe(this){
            if (it != null) {
                when(it){
                    is Result.Loading -> {
                        binding.progressBar5.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar5.visibility = View.GONE
                        Snackbar.make(
                            window.decorView.rootView,
                            getString(R.string.edit_success),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    is Result.Error -> {
                        binding.progressBar5.visibility = View.GONE
                        Snackbar.make(
                            window.decorView.rootView,
                            getString(R.string.edit_fail),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}
