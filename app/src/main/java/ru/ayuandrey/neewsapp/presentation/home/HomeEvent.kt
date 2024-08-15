package ru.ayuandrey.neewsapp.presentation.home

sealed class HomeEvent {                                         //домашнее событие

    data class UpdateScrollValue(val newValue: Int): HomeEvent()   //Обновить значение прокрутки/ новое значение типа число
    data class UpdateMaxScrollingValue(val newValue: Int): HomeEvent()  //Обновить максимальное значение прокрутки

}