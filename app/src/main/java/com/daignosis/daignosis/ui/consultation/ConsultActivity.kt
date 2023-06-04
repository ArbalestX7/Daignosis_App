package com.daignosis.daignosis.ui.consultation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.daignosis.daignosis.databinding.ActivityConsultBinding

class ConsultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConsultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}