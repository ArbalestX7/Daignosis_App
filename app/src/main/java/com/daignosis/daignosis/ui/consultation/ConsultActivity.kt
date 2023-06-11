package com.daignosis.daignosis.ui.consultation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.socket.SocketHandler
import com.daignosis.daignosis.databinding.ActivityConsultBinding

class ConsultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityConsultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.consult)


        SocketHandler.setSocket()
        SocketHandler.establishConnection()

        //val mSocket = SocketHandler.getSocket()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return false
    }
}