package com.daignosis.daignosis.ui.article

import androidx.lifecycle.ViewModel
import com.daignosis.daignosis.data.repository.DaignosisRepository

class ArticleViewModel(private val repository: DaignosisRepository): ViewModel(){
    fun getArticle() = repository.getListArticle()
}