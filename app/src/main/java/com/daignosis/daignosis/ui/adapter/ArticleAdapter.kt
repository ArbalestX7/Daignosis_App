package com.daignosis.daignosis.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daignosis.daignosis.R
import com.daignosis.daignosis.data.response.DataItem
import com.daignosis.daignosis.databinding.ItemArticleBinding
import com.daignosis.daignosis.ui.article.DetailArticleActivity
import com.daignosis.daignosis.utils.Util.withDateFormat

class ArticleAdapter (
    private val listArticle: List<DataItem>
    ): RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {

    class ListViewHolder(
        var binding: ItemArticleBinding
    ): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val allArticle = listArticle[position]

        holder.binding.apply {
            Glide.with(holder.itemView.context)
                .load(allArticle.photoArticle)
                .placeholder(R.drawable.holder)
                .into(imgArticlePhoto)
            tvArticleTitle.text = allArticle.articleName
            tvArticleDate.text = allArticle.creationDate.withDateFormat()
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailArticleActivity::class.java)
            intent.putExtra(DetailArticleActivity.EXTRA_ARTICLE, allArticle)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listArticle.size
}