package ru.ayuandrey.neewsapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ayuandrey.neewsapp.data.manager.LocalUserMabagerImpl
import ru.ayuandrey.neewsapp.domain.manager.LocalUserManager
import ru.ayuandrey.neewsapp.domain.usercase.AppEntryUseCase
import ru.ayuandrey.neewsapp.domain.usercase.ReadAppEntry
import ru.ayuandrey.neewsapp.domain.usercase.SaveAppEntry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ) : LocalUserManager = LocalUserMabagerImpl(application)


    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)

    )

}