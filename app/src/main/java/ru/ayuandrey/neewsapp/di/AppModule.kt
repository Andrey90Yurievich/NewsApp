package ru.ayuandrey.neewsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ayuandrey.neewsapp.data.local.NewsDao
import ru.ayuandrey.neewsapp.data.local.NewsDatabase
import ru.ayuandrey.neewsapp.data.local.NewsTypeConvertor
import ru.ayuandrey.neewsapp.data.manager.LocalUserMabagerImpl
import ru.ayuandrey.neewsapp.data.remote.NewsApi
import ru.ayuandrey.neewsapp.data.repository.NewsRepositoryImpl
import ru.ayuandrey.neewsapp.domain.manager.LocalUserManager
import ru.ayuandrey.neewsapp.domain.repository.NewsRepository
import ru.ayuandrey.neewsapp.domain.usercase.app_entry.AppEntryUseCase
import ru.ayuandrey.neewsapp.domain.usercase.app_entry.ReadAppEntry
import ru.ayuandrey.neewsapp.domain.usercase.app_entry.SaveAppEntry
import ru.ayuandrey.neewsapp.domain.usercase.news.GetNews
import ru.ayuandrey.neewsapp.domain.usercase.news.NewsUseCases
import ru.ayuandrey.neewsapp.domain.usercase.news.SearchNews
import ru.ayuandrey.neewsapp.util.Constants.BASE_URL
import ru.ayuandrey.neewsapp.util.Constants.NEWS_DATABASE_NAME
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


    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }




    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        //newsDao: NewsDao
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi,)//newsDao)
    }


    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        //newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
//            upsertArticle = UpsertArticle(newsDao),
//            deleteArticle = DeleteArticle(newsDao),
//            getArticles = GetArticles(newsDao),
//            getArticle = GetArticle(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao



}