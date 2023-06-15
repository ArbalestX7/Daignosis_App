package com.daignosis.daignosis.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.response.DataItemMsg
import com.daignosis.daignosis.databinding.ItemMessageBinding
import com.daignosis.daignosis.utils.Util.withDateFormat

class ChatAdapter (
    private val listMessage: List<DataItemMsg>
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
            tvName.text = allMessage.isBot.toString()
            tvMessage.text = allMessage.message
            tvDateMsg.text = allMessage.date.withDateFormat()
            setText(allMessage.isBot, tvMessage, tvName)
        }
    }

    private fun setText(isBot: Int?,textView: TextView, tv: TextView) {
        if (isBot == 1) {
            textView.setBackgroundResource(R.drawable.rounded_message_blue)
            tv.setText(R.string.bot)
        } else {
            textView.setBackgroundResource(R.drawable.rounded_message_gray)
            tv.setText(R.string.me)
        }
    }
}