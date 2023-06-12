package com.daignosis.daignosis.ui.consultation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.daignosis.daignosis.R
import com.daignosis.daignosis.databinding.ActivityHistoryBinding
import com.daignosis.daignosis.ui.profile.ProfileViewModel
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

        binding.fabNewSession.setOnClickListener {
            newSession()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
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

                    }
                    is Result.Error -> {
                        Toast.makeText(this, R.string.fail_session, Toast.LENGTH_SHORT).show()
                        binding.progressBar6.visibility = View.GONE
                    }
                }
            }
        }
    }
}