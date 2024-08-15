package ru.ayuandrey.neewsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.ayuandrey.neewsapp.domain.usercase.news.SearchNews
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(                              //вью модель поиска
    private val searchNewsUseCase: SearchNews                           //свойство определенов конструкторе пример использования поисковых новостей
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())                   //состояние поиска -
    val state: State<SearchState> = _state                                //переменная состояние хз зачем


    fun onEvent(event: SearchEvent) {                                     //событие -
        when (event) {                                                    //когда этот объект события
            is SearchEvent.UpdateSearchQuery -> {                         //объект соответсвует типу запроса поискового хз
                _state.value = _state.value.copy(searchQuery = event.searchQuery)     //наблюдаемому состоянию присвоить строку запроса что написали
            }

            is SearchEvent.SearchNews -> {                                 //если соответсвует типу поиск новостей
                searchNews()                                               //выполнить функцию поиск новостей
            }
        }
    }

    private fun searchNews() {                                               //поиск новостей
        val articles = searchNewsUseCase(                                     //статьи - пример использования поисковых новостей
            searchQuery = _state.value.searchQuery,                           //принимает поисковый запрос - что ввел пользователь
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")     //принимает список со строками
        ).cachedIn(viewModelScope)                                             //запустить в корутине для вью модели
        _state.value = _state.value.copy(articles = articles)                  //найденнеы статьи передать в состояние
    }


}