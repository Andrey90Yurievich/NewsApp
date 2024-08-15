package ru.ayuandrey.neewsapp.presentation.onboarding

sealed class OnBoardingEvent {     //доска событий чтоли

    object SaveAppEntry: OnBoardingEvent() //анонимный класс - Сохранить запись в приложении будет создан только в 1-м экземпляре

}