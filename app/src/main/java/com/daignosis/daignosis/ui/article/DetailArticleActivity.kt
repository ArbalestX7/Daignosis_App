package com.daignosis.daignosis.ui.article

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.response.DataItem
import com.daignosis.daignosis.data.socket.SocketHandler
import com.daignosis.daignosis.databinding.ActivityDetailArticleBinding
import com.daignosis.daignosis.utils.Util.withDateFormat

@Suppress("DEPRECATION")
class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        SocketHandler.closeConnection()

        setDetail()

        binding.btnBackArticleDetail.setOnClickListener {
            onBackPressed()
        }

    }

    private fun setDetail() {
        val detailArticle = intent.getParcelableExtra<DataItem>(EXTRA_ARTICLE)

        binding.apply {
            Glide.with(this@DetailArticleActivity)
                .load(detailArticle?.photoArticle)
                .placeholder(R.drawable.holder)
                .into(imgDetailArticle)

            tvDetailTitle.text = detailArticle?.articlePost
            tvDetailDate.text = detailArticle?.creationDate?.withDateFormat()
            tvDetailDesc.text = detailArticle?.articlePost
        }
    }
    companion object {
        const val EXTRA_ARTICLE = "extra_article"
    }
}