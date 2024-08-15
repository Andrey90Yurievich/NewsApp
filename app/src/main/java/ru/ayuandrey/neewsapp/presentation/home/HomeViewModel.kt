package ru.ayuandrey.neewsapp.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.ayuandrey.neewsapp.domain.usercase.news.GetNews
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(                                    //вью модель домашней страницы
    private val getNewsUseCase: GetNews    //поток новостей                 //параметр в конструктор передан свойство типа GetNews
): ViewModel() {                                                            //GetNews - Получать новости
    var state = mutableStateOf(HomeState())                                 //сотсояние классов данных HomeState
        private set                                                         // чтобы свойство name было доступно только для чтения вне класса. Вы можете ограничить сеттер свойства с помощью private set.

    val news = getNewsUseCase(                                              //пример использования получения новостей
        sources = listOf("bbc-news","abc-news","al-jazeera-english")        //список строк
    ).cachedIn(viewModelScope)                                              //запустит в корутине для вью модели

    fun onEvent(event: HomeEvent){                                          //событие - значения прокрутки какие то
        when(event){                                                             //когда событие - значение прокрутки
            is HomeEvent.UpdateScrollValue -> updateScrollValue(event.newValue)   //соттветсвует типу начального значения то
            is HomeEvent.UpdateMaxScrollingValue -> updateMaxScrollingValue(event.newValue)
        }
    }

    private fun updateScrollValue(newValue: Int){                         //начальное значение прокрутки
        state.value = state.value.copy(scrollValue = newValue)            //создается копия объекта с новым значением
    }
    private fun updateMaxScrollingValue(newValue: Int){                   //обновить максимальное значение прокрутки
        state.value = state.value.copy(maxScrollingValue = newValue)
    }

}