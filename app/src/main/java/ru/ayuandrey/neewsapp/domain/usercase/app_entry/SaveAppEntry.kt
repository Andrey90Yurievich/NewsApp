package ru.ayuandrey.neewsapp.domain.usercase.app_entry

import ru.ayuandrey.neewsapp.domain.manger.LocalUserManger
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManger: LocalUserManger
) {

    suspend operator fun invoke(){
        localUserManger.saveAppEntry()
    }

}
