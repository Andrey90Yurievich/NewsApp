package ru.ayuandrey.neewsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ayuandrey.neewsapp.domain.usercase.app_entry.SaveAppEntry
import javax.inject.Inject


@HiltViewModel                                              //точка входа для ViewModel
class OnBoardingViewModel @Inject constructor(
    private val saveAppEntry: SaveAppEntry                  //передается свойство /SaveAppEntry - Сохранить запись в приложении/ используя интерфейсы создается объект как понимаю
) : ViewModel() {
    fun onEvent(event: OnBoardingEvent){                     //onEvent - событие/ принимает событие анонимный класс делает 1 экз объект
        when(event){                                         //когда это событие этот экземпляр анонимного класса/ Конструкция when проверяет значение некоторого объекта и в зависимости от его значения выполняет тот или иной код.
            is OnBoardingEvent.SaveAppEntry ->{              // если event = соответствует типу то оператор is вернет true/ Оператор is позволяет проверить выражение на принадлежность определенному типу данных
                saveUserEntry()                              //то выполнится лямбда функция
            }
        }
    }
    private fun saveUserEntry() {                             //сохранить запись пользователя
        viewModelScope.launch {                //получить доступ к CoroutineScope из ViewModel через viewModelScope свойство ViewModel
            saveAppEntry()                                     //saveAppEntry - сохранить запись в приложении
        }
    }

}