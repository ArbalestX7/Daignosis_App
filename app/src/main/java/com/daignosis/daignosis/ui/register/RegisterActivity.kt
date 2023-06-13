package com.daignosis.daignosis.ui.register

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.daignosis.daignosis.R
import com.daignosis.daignosis.databinding.ActivityRegisterBinding
import com.daignosis.daignosis.ui.login.LoginActivity
import com.daignosis.daignosis.utils.Result
import com.daignosis.daignosis.utils.Util.isValidEmail
import com.daignosis.daignosis.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        registerViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[RegisterViewModel::class.java]

        binding.btnRegispage.setOnClickListener {
            regis()
        }

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun regisAuth() {
        val username = binding.edtRegisUsername.text.toString()
        val email = binding.edtRegisEmail.text.toString()
        val password = binding.edtRegisPw.text.toString()

        registerViewModel.register(username, email, password).observe(this@RegisterActivity) {
            when(it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        window.decorView.rootView,
                        getString(R.string.snack_regis),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        window.decorView.rootView,
                        getString(R.string.snack_regisError),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun resetForm() {
        binding.apply {
            edtRegisEmail.text = null
            edtRegisPw.text = null
            edtRegisUsername.text = null

            emailTextLayout.helperText = " "
            passwordTextLayout.helperText = " "
            usernameTextLayout.helperText = " "
        }
    }
    private fun validPassword(): String? {
        val passwordText = binding.edtRegisPw.text.toString()
        if (passwordText.length < 8) {
            return "Minimum 8 Karakter Password"
        }
        return null
    }
    private fun validEmail(): String? {
        val email = binding.edtRegisEmail.text.toString()
        if (!email.isValidEmail()){
            return "Format Email Tidak Valid"
        }
        return null
    }
    private fun regis() {
        binding.emailTextLayout.helperText = validEmail()
        binding.passwordTextLayout.helperText = validPassword()

        val validEmail = binding.emailTextLayout.helperText == null
        val validPassword = binding.passwordTextLayout.helperText == null

        if (validEmail && validPassword) {
            binding.progressBar.visibility = View.VISIBLE
            regisAuth()
            resetForm()
        }
        else
            Toast.makeText(this, "Invalid Form", Toast.LENGTH_SHORT).show()
    }
}