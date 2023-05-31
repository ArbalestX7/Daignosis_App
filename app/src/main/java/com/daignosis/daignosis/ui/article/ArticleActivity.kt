package com.daignosis.daignosis.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daignosis.daignosis.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}