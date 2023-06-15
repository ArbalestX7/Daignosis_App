package com.daignosis.daignosis.ui.adapter

import android.content.ContentValues
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daignosis.daignosis.data.repository.DaignosisRepository
import com.daignosis.daignosis.data.response.DataHistory
import com.daignosis.daignosis.data.response.DataSession
import com.daignosis.daignosis.databinding.ItemHistoryBinding
import com.daignosis.daignosis.databinding.ItemMessageBinding
import com.daignosis.daignosis.ui.article.DetailArticleActivity
import com.daignosis.daignosis.ui.consultation.ConsultActivity
import com.daignosis.daignosis.utils.UserPref
import com.daignosis.daignosis.utils.Util.withDateFormat
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class HistoryAdapter (
    private val listMessage: List<DataHistory>
): RecyclerView.Adapter<HistoryAdapter.ListViewHolder>() {

    class ListViewHolder(
        var binding: ItemHistoryBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listMessage.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val allMessage = listMessage[position]

        holder.binding.apply {
            tvSessionId.text = allMessage.sessionId
            tvMessage.text = allMessage.message
            tvMsgDate.text = allMessage.latestChatDate.withDateFormat()
        }
    }
}