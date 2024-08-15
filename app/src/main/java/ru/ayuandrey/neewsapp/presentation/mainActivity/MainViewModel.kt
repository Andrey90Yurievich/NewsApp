package ru.ayuandrey.neewsapp.presentation.mainActivity

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
import ru.ayuandrey.neewsapp.domain.usercase.app_entry.ReadAppEntry
import ru.ayuandrey.neewsapp.presentation.navgraph.Route
import javax.inject.Inject


@HiltViewModel                                            //специальная аннотация в библиотеке Hilt для ViewModel/ привязывает к жизенному циклу
class MainViewModel @Inject constructor(
    private val readAppEntry: ReadAppEntry
): ViewModel() {                                                                //MainViewModel наследует функциональность от ViewModel
    private val _splashCondition = mutableStateOf(true)                         //состояние всплеска - начала приложения/ наблюдает за состоянием всплеска
    val splashCondition: State<Boolean> = _splashCondition                            //держит соcтояние в памяти / значение true присваивается переменной splashCondition типа State<Boolean>/  наблюдаемый держатель данных
    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)    //_startDestination - начальный пункт назначения/ "appStartNavigation" - этот маршрут держит
    val startDestination: State<String> = _startDestination                            //стартовый экран - состояние строки в маршрутах навигации/ либо переходит на всплеск экран либо сразу на домашний экран новостей
    init {                                                                              //блок инициализации, выполняется сразу при запуске
        readAppEntry().onEach { shouldStartFromHomeScreen ->                            //readAppEntry - прочитать запись в приложении / onEach - на каждом - перечисление/ параметр лямда функции shouldStartFromHomeScreen - должно начинаться С Главного экрана
            if(shouldStartFromHomeScreen){                                               //используя условный опереато if если shouldStartFromHomeScreen значение true
                _startDestination.value = Route.NewsNavigation.route                    //то значению стартового экрана передаем маршрутя для перемещения на экран "newsNavigation" экран новостей
            }else{
                _startDestination.value = Route.AppStartNavigation.route                 //иначе значению наблюдателю передаем стартовый экран со самого старта
            }
            delay(300)                                                          //Без этой задержки на экране ввода в эксплуатацию будет отображаться импульс. //задержка
            _splashCondition.value = false
        }.launchIn(viewModelScope)                                                     //launchIn - обычно используется с операторами onEach,
    }                                                                                  //запускает сбор данного потока в области. Это сокращение от scope.launch
}