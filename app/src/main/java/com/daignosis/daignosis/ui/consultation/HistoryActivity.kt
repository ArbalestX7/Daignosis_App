package com.daignosis.daignosis.ui.consultation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.response.DataHistory
import com.daignosis.daignosis.databinding.ActivityHistoryBinding
import com.daignosis.daignosis.ui.adapter.HistoryAdapter
import com.daignosis.daignosis.ui.login.LoginActivity
import com.daignosis.daignosis.utils.Result
import com.daignosis.daignosis.utils.ViewModelFactory

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHistoryBinding
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(
            this, ViewModelFactory(this)
        )[HistoryViewModel::class.java]
        history()

        binding.fabNewSession.setOnClickListener {
            checkToken()
            newSession()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return false
    }

    private fun newSession(){
        viewModel.getToken().observe(this){
            viewModel.newSession(it.token).observe(this){
                when(it) {
                    is Result.Loading -> {
                        binding.progressBar6.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar6.visibility = View.GONE
                        startActivity(Intent(this,ConsultActivity::class.java))
                        finish()
                    }
                    is Result.Error -> {
                        Toast.makeText(this, R.string.fail_session, Toast.LENGTH_SHORT).show()
                        binding.progressBar6.visibility = View.GONE
                    }
                }
            }
        }
    }
    private fun checkToken() {
        viewModel.getToken().observe(this) {
            if (it.token.isEmpty()) {
                Toast.makeText(this, R.string.must_login, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,LoginActivity::class.java))
            }
        }
    }

    private fun history(){
        viewModel.getToken().observe(this){
            viewModel.history(it.token).first.observe(this){
                when(it) {
                    is Result.Loading -> {
                        binding.progressBar6.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar6.visibility = View.GONE
                    }
                    is Result.Error -> {
                        Toast.makeText(
                            this, R.string.fail_history, Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            viewModel.history(it.token).second.observe(this){
                setRecycler(it)
            }
        }
    }
    private fun setRecycler(msg: List<DataHistory>){
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity, LinearLayoutManager.VERTICAL, false)
            adapter = HistoryAdapter(msg)
        }
    }
}