package com.daignosis.daignosis.ui.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.model.LoginModel
import com.daignosis.daignosis.databinding.ActivityLoginBinding
import com.daignosis.daignosis.ui.main.MainActivity
import com.daignosis.daignosis.ui.register.RegisterActivity
import com.daignosis.daignosis.utils.ViewModelFactory
import com.daignosis.daignosis.utils.Result
import com.daignosis.daignosis.utils.Util.gone
import com.daignosis.daignosis.utils.Util.visible
import com.google.android.material.snackbar.Snackbar

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()

        loginViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[LoginViewModel::class.java]

        binding.btnLoginpage.setOnClickListener {
            login()
        }

        binding.btnRegis.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
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

    /*private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -15f, 15f).apply {
            duration = 2000
            repeatCount = ObjectAnimator.RESTART
            repeatMode = ObjectAnimator.REVERSE
        }.start()


        val email = ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(500)
        val emailEd =
            ObjectAnimator.ofFloat(binding.edLoginEmailLayout, View.ALPHA, 1f).setDuration(500)
        val pw = ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(500)
        val pwEd =
            ObjectAnimator.ofFloat(binding.edLoginPasswordLayout, View.ALPHA, 1f).setDuration(500)
        val signin = ObjectAnimator.ofFloat(binding.signinButton, View.ALPHA, 1f).setDuration(500)


        val together = AnimatorSet().apply {
            playTogether(email, pw, emailEd, pwEd)
        }

        AnimatorSet().apply {
            playSequentially(signin, together)
            start()
        }
    }*/

    private fun validPassword(): String? {
        val passwordText = binding.edtLoginPw.text.toString()
        if (passwordText.length < 8) {
            return "Minimum 8 Character Password"
        }
        return null
    }

    private fun checkIfEmailIsValid(): String? {
        val emailInputText = binding.edtLoginEmail.text.toString()
        binding.edtLoginEmail.doOnTextChanged { text, start, before, count ->
            if(!Patterns.EMAIL_ADDRESS.matcher(emailInputText).matches()){
                binding.edtLoginEmail.error = "Invalid Email Address"
            }
            else{
                binding.edtLoginEmail.error = null
            }
        }
        return null
    }

    private fun login() {
        binding.emailTextLayout.helperText = checkIfEmailIsValid()
        binding.passwordTextLayout.helperText = validPassword()

        val checkIfEmailIsValid = binding.emailTextLayout.helperText == null
        val validPassword = binding.passwordTextLayout.helperText == null

        if (checkIfEmailIsValid && validPassword) {
            binding.progressBar.visibility = View.VISIBLE
            signupAuth()
            resetForm()

        }
        else
            Toast.makeText(this, "Invalid Form", Toast.LENGTH_SHORT).show()
    }

    private fun signupAuth() {
        val email = binding.edtLoginEmail.text.toString()
        val password = binding.edtLoginPw.text.toString()

        loginViewModel.login(email, password).observe(this@LoginActivity) {
            when(it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        window.decorView.rootView,
                        getString(R.string.snack_login),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        window.decorView.rootView,
                        getString(R.string.snack_loginError),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun resetForm() {
        binding.edtLoginEmail.text = null
        binding.edtLoginPw.text = null

        binding.emailTextLayout.helperText = " "
        binding.passwordTextLayout.helperText = " "
    }
}