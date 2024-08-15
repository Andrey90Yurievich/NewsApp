package ru.ayuandrey.neewsapp.domain.usercase.news

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.model.Article
import ru.ayuandrey.neewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository          //класс принимает в конструктор свойство типа NewsRepository которое расширяет интерфейс контракт что создание оъекта с его
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {   //принимает список и возвращает тип данных поток данных типа PagingData<Article
                                                                              //PagingData - данные поиска по страницам Article - статьи
        return newsRepository.getNews(sources = sources)                      //вернуть интерфейса функция getNews получать новости
                                                                              // в которую передаются параметры sources - источники
    }
}