package ru.ayuandrey.neewsapp.domain.usercase.app_entry

import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.manger.LocalUserManger
import javax.inject.Inject

class ReadAppEntry @Inject constructor(                 //Прочитать запись в приложении
    private val localUserManger: LocalUserManger        //классу передается свойство localUserManger - менеджер локальных пользователей который используется в функции /расширяет интерфейс LocalUserManger
) {

    operator fun invoke(): Flow<Boolean> {             //invoke - вызвать
        return localUserManger.readAppEntry()          //менеджер локальных пользователей.прочитать запись в приложении/ возвращает записи - новости наверно
    }

}
