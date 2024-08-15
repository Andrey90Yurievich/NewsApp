package ru.ayuandrey.neewsapp.domain.usercase.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.domain.repository.NewsRepository


class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(
            searchQuery = searchQuery,
            sources = sources
        )
    }
}