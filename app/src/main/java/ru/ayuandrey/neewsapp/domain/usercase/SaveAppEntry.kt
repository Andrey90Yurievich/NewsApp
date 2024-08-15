package ru.ayuandrey.neewsapp.domain.usercase

import ru.ayuandrey.neewsapp.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
  suspend operator fun invoke() {
      localUserManager.saveAppEntry()
  }
}