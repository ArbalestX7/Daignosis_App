package com.daignosis.daignosis.ui.article

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.daignosis.daignosis.utils.Result
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daignosis.daignosis.data.response.DataItem
import com.daignosis.daignosis.data.socket.SocketHandler
import com.daignosis.daignosis.databinding.ActivityArticleBinding
import com.daignosis.daignosis.ui.adapter.ArticleAdapter
import com.daignosis.daignosis.ui.main.MainActivity
import com.daignosis.daignosis.utils.ViewModelFactory

@Suppress("DEPRECATION")
class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private lateinit var articleViewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        SocketHandler.closeConnection()

        articleViewModel = ViewModelProvider(
            this,
            ViewModelFactory(this)
        )[ArticleViewModel::class.java]

        articleViewModel.getArticle().second.observe(this){
            setRecycler(it)
        }
        articleViewModel.getArticle().first.observe(this){
            when (it) {
                is Result.Success -> binding.progressBar2.visibility = View.GONE
                is Result.Loading -> binding.progressBar2.visibility = View.VISIBLE
                is Result.Error -> Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnBackArticleList.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setRecycler(article: List<DataItem>){
        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(this@ArticleActivity, LinearLayoutManager.VERTICAL, false)
            adapter = ArticleAdapter(article)
        }
    }
}