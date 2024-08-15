package ru.ayuandrey.neewsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.data.remote.NewsApi
import ru.ayuandrey.neewsapp.data.remote.NewsPagingSource
import ru.ayuandrey.neewsapp.data.remote.SearchNewsPagingSource
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val newsApi: NewsApi
):  NewsRepository {
    override fun getNews(sources: List<String>) : Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }


}