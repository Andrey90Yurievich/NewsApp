package ru.ayuandrey.neewsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ayuandrey.neewsapp.data.manger.LocalUserMangerImpl
import ru.ayuandrey.neewsapp.domain.manger.LocalUserManger
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MangerModule {

    @Binds
    @Singleton
    abstract fun bindLocalUserManger(localUserMangerImpl: LocalUserMangerImpl) : LocalUserManger
}