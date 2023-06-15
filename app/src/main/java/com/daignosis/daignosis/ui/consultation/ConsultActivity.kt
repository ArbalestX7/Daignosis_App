package com.daignosis.daignosis.ui.consultation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.response.DataHistory
import com.daignosis.daignosis.data.response.DataItemMsg
import com.daignosis.daignosis.databinding.ActivityConsultBinding
import com.daignosis.daignosis.ui.adapter.ChatAdapter
import com.daignosis.daignosis.ui.article.DetailArticleActivity
import com.daignosis.daignosis.utils.Result
import com.daignosis.daignosis.utils.ViewModelFactory

class ConsultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConsultBinding
    private lateinit var viewModel: ConsultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.consult)

        setDetail()

        viewModel = ViewModelProvider(
            this, ViewModelFactory(this)
        )[ConsultViewModel::class.java]
        viewModel.getSession()

        binding.sendButton.setOnClickListener { sendMessage() }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        viewModel.rmvSession()
        finish()
        return false
    }

    private fun sendMessage(){
        val userMsg = binding.messageEditText.text.toString()
        viewModel.getToken().observe(this){
            val token = it.token
            viewModel.getSession().observe(this){
                val sessionId = it.sessionId
                viewModel.sendMsg(token, userMsg, it.sessionId).first.observe(this){
                    when(it) {
                        is Result.Loading -> {
                            binding.progressBar8.visibility = View.VISIBLE
                        }
                        is Result.Success -> {
                            binding.messageEditText.setText("")
                            binding.progressBar8.visibility = View.GONE
                            viewModel.historyMsg(token,sessionId)
                            viewModel.historyMsg(token, sessionId).second.observe(this){
                                setRecycler(it)
                            }
                        }
                        is Result.Error -> {
                            binding.progressBar8.visibility = View.GONE
                                Toast.makeText(
                                this, R.string.fail_sendMsg, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
    private fun setRecycler(msg: List<DataItemMsg>){
        binding.messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@ConsultActivity, LinearLayoutManager.VERTICAL, true)
            adapter = ChatAdapter(msg)
        }
    }

    private fun setDetail() {
        val detailArticle = intent.getParcelableExtra<DataHistory>(DetailArticleActivity.EXTRA_ARTICLE)
        detailArticle?.sessionId
    }
}