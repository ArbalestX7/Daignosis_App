package com.daignosis.daignosis.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daignosis.daignosis.data.response.Data
import com.daignosis.daignosis.databinding.ItemMessageBinding
import com.daignosis.daignosis.utils.Util.withDateFormat
import com.daignosis.daignosis.utils.Util.withFormatDateMsg

class ChatAdapter (
    private val listMessage: List<Data>
): RecyclerView.Adapter<ChatAdapter.ListViewHolder>() {

    class ListViewHolder(
        var binding: ItemMessageBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemMessageBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listMessage.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val allMessage = listMessage[position]

        holder.binding.apply {
            tvMessage.text = allMessage.message
            tvDateMsg.text = allMessage.date.withDateFormat()
        }
    }
}