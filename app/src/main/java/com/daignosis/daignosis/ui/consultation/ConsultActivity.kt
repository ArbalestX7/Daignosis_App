package com.daignosis.daignosis.ui.consultation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.response.Data
import com.daignosis.daignosis.data.response.DataItem
import com.daignosis.daignosis.databinding.ActivityConsultBinding
import com.daignosis.daignosis.ui.adapter.ArticleAdapter
import com.daignosis.daignosis.ui.adapter.ChatAdapter
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

        viewModel = ViewModelProvider(
            this, ViewModelFactory(this)
        )[ConsultViewModel::class.java]

        binding.sendButton.setOnClickListener { sendMessage() }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        viewModel.rmvSession()
        finish()
        return false
    }

    private fun sendMessage(){
        val userMsg = binding.messageEditText.toString()
        viewModel.getToken().observe(this){
            val token = it.token
            viewModel.getSession().observe(this){
                viewModel.sendMsg(token, userMsg, it.sessionId).first.observe(this){
                    when(it) {
                        is Result.Loading -> {}
                        is Result.Success -> {
                            binding.messageEditText.setText("")
                        }
                        is Result.Error -> {
                            Toast.makeText(
                                this, R.string.fail_sendMsg, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
                viewModel.sendMsg(token,userMsg,it.sessionId).second.observe(this){
                    setRecycler(it)
                }
            }
        }
    }
    private fun setRecycler(msg: List<Data>){
        binding.messageRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ConsultActivity, LinearLayoutManager.VERTICAL, false)
            adapter = ChatAdapter(msg)
        }
    }
}