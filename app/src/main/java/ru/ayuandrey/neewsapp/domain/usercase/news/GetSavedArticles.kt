package ru.ayuandrey.neewsapp.domain.usercase.news

import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.data.local.NewsDao
import ru.ayuandrey.neewsapp.domain.model.Article
import javax.inject.Inject

class GetSavedArticles @Inject constructor(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

}