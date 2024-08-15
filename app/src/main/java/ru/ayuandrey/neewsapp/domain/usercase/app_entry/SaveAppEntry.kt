package ru.ayuandrey.neewsapp.domain.usercase.app_entry

import ru.ayuandrey.neewsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
  suspend operator fun invoke() {
      localUserManager.saveAppEntry()
  }
}