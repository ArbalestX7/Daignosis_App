package com.daignosis.daignosis.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daignosis.daignosis.databinding.ItemArticleBinding
import java.util.*

class ArticleAdapter ()
    /*private val listArticle: ArrayList<ResultsItemArticle>
    ): RecyclerView.Adapter<ListArticleAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val allArticle = listArticle[position]

        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(allArticle.)
                .into(imgArticlePhoto)
            tvArticleTitle.text = title
            tvArticleDate.text = date
        }


        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailArticleActivity::class.java)
            intent.putExtra(key,allArticle.key)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = listArticle.size

    companion object{
        var key = "KEY"
    }
}*/