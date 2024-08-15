package ru.ayuandrey.neewsapp.domain.usercase.app_entry

import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.manger.LocalUserManger
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    private val localUserManger: LocalUserManger
) {

    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }

}
