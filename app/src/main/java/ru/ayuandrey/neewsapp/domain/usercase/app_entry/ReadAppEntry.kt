package ru.ayuandrey.neewsapp.domain.usercase.app_entry

import kotlinx.coroutines.flow.Flow
import ru.ayuandrey.neewsapp.domain.manager.LocalUserManager

class ReadAppEntry (
    private val localUserManager: LocalUserManager
    ) {
        operator fun invoke(): Flow<Boolean> {
            return localUserManager.readAppEntry()
        }
    }
