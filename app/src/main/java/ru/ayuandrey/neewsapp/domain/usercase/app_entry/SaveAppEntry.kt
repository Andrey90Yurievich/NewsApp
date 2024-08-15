package ru.ayuandrey.neewsapp.domain.usercase.app_entry

import ru.ayuandrey.neewsapp.domain.manger.LocalUserManger
import javax.inject.Inject

class SaveAppEntry @Inject constructor(            //Сохранить запись в приложении
    private val localUserManger: LocalUserManger    //localUserManger расширяет, применяет интерфейс LocalUserManger
) {
    suspend operator fun invoke(){                  //оператор invoke теперь будет применять функцию приостановки saveAppEntry
        localUserManger.saveAppEntry()
    }

}
