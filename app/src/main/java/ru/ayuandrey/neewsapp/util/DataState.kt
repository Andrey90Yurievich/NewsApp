package ru.ayuandrey.neewsapp.util


sealed class DataState<T> {

    data class Loading<T>(val isLoading: Boolean) : DataState<T>()

    data class Success<T>(val data: T) : DataState<T>()

    data class Response<T>(val uiComponent: UIComponent, val error: Exception?=null) : DataState<T>()
}

sealed class UIComponent {                                  //компонент пользоватеьского интерфейса

    data class Toast(val message: String): UIComponent()        //объект тост принимает строку в сообщение

    data class Dialog(val title: String, val message: String): UIComponent()

    data class None(val message: String? = null): UIComponent()

}