package dev.andriniainal.droidkoin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.andriniainal.droidkoin.data.repository.ArticlesRepository
import dev.andriniainal.droidkoin.model.ArticleList
import dev.andriniainal.droidkoin.ui.base.BaseViewModel
import dev.andriniainal.droidkoin.utils.State
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel constructor(private val articlesRepository: ArticlesRepository) :
    BaseViewModel() {

    private val _articlesLiveData = MutableLiveData<State<ArticleList>>()

    val articlesLiveData: LiveData<State<ArticleList>>
        get() = _articlesLiveData

    fun getArticles() {
        viewModelScope.launch {
            articlesRepository.getAllArticles().collect {
                _articlesLiveData.value = it
            }
        }
    }
}