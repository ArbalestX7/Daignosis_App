package com.daignosis.daignosis.ui.article

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.daignosis.daignosis.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        //setupView()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }
        supportActionBar?.hide()
    }
}