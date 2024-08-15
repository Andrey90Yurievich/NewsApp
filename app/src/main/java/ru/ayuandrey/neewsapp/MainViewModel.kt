package ru.ayuandrey.neewsapp

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.ayuandrey.neewsapp.domain.usercase.AppEntryUseCase
import ru.ayuandrey.neewsapp.presentation.navgraph.Route
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
) : ViewModel() {
    var splashCondition by mutableStateOf(true)
    private set //чтобы свойство name было доступно только для чтения вне класса. ограничить сеттер свойства с помощью private set.

     //val splashCondition: State<Boolean> = _splashCondition


    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set


    init {
        appEntryUseCase.readAppEntry().onEach {shouldStartFromHomeScreen -> //на каждом
            if (shouldStartFromHomeScreen) {
                startDestination = Route.NewsNavigation.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }
            delay(3000)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}