package com.daignosis.daignosis.ui.main

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.daignosis.daignosis.databinding.ActivityMainBinding
import com.daignosis.daignosis.ui.article.ArticleActivity
import com.daignosis.daignosis.ui.article.DetailArticleActivity
import com.daignosis.daignosis.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }


}