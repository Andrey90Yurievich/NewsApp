package ru.ayuandrey.neewsapp.presentation.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.model.Article

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)