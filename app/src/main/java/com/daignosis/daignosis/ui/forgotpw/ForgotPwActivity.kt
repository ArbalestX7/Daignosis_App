package com.daignosis.daignosis.ui.forgotpw

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.socket.SocketHandler
import com.daignosis.daignosis.databinding.ActivityForgotPwBinding
import com.daignosis.daignosis.ui.login.LoginActivity
import com.daignosis.daignosis.ui.register.RegisterViewModel
import com.daignosis.daignosis.utils.Result
import com.daignosis.daignosis.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class ForgotPwActivity : AppCompatActivity() {
    private lateinit var binding : ActivityForgotPwBinding
    private lateinit var forgotViewModel: ForgotViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPwBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        SocketHandler.closeConnection()

        forgotViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[ForgotViewModel::class.java]

        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnForgotpage.setOnClickListener { forgotAuth() }
    }

    private fun forgotAuth() {
        val username = binding.edtForgotUsername.text.toString()

        forgotViewModel.forgotPass(username).observe(this@ForgotPwActivity) {
            when(it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        window.decorView.rootView,
                        getString(R.string.snack_forgot),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        window.decorView.rootView,
                        getString(R.string.snack_forgotError),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
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
}