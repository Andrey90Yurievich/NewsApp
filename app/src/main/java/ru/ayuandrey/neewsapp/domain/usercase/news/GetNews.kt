package ru.ayuandrey.neewsapp.domain.usercase.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.domain.repository.NewsRepository

class GetNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>) : Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}