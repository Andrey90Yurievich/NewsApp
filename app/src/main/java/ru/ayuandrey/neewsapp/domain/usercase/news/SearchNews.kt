package ru.ayuandrey.neewsapp.domain.usercase.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.domain.repository.NewsRepository
import javax.inject.Inject


class SearchNews @Inject constructor(                     //поиск новостей
    private val newsRepository: NewsRepository              //свойство расширяет интерфейс NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {  //принимает поисковый запрос, источники список и возвращает поток страниц со статьями
        return newsRepository.searchNews(                    //расшмряемое свойство интерфейса для создания объекта чтоли
            searchQuery = searchQuery,                       //поисковый запрос
            sources = sources                                 //источники откуда искать наверно
        )
    }
}