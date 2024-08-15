package ru.ayuandrey.neewsapp.presentation.search

sealed class SearchEvent {                                                   //Событие поиска
    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()    //Обновить поисковый запрос - принимает поисковый запрос типа строки
    object SearchNews : SearchEvent()                                         //Поиск новостей - анонимный клааа - только  один объект будет создан
}