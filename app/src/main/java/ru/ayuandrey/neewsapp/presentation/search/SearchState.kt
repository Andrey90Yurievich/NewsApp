package ru.ayuandrey.neewsapp.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.model.Article

data class SearchState(                                //Состояние поиска
    val searchQuery: String = "",                      //свойство класса данных в конструктор - поисковый запрос/ иницивлизировано как пустое
    val articles: Flow<PagingData<Article>>? = null     //статьи - типа потока статей с параметрами = инициализированоно как нулл
)