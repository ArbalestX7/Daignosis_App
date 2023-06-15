package com.daignosis.daignosis.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daignosis.daignosis.data.response.DataHistory
import com.daignosis.daignosis.databinding.ItemHistoryBinding
import com.daignosis.daignosis.utils.Util.withDateFormat

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