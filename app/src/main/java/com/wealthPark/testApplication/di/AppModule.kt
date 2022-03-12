package com.wealthPark.testApplication.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wealthPark.testApplication.data.local.database.AppDatabase
import com.wealthPark.testApplication.data.local.database.AppDatabase.Companion.DATABASE_NAME
import com.wealthPark.testApplication.data.local.database.dao.CityDao
import com.wealthPark.testApplication.data.local.database.dao.FoodDao
import com.wealthPark.testApplication.data.local.prefs.AppPreferences
import com.wealthPark.testApplication.data.remote.ApiEndPoint.Companion.BASE_URL
import com.wealthPark.testApplication.data.remote.apiService.ICityAndFoodService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    internal fun provideAppPreferenece(context: Context, mGson: Gson): AppPreferences {
        return AppPreferences(context, mGson)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideCarService(retrofit: Retrofit): ICityAndFoodService =
        retrofit.create(ICityAndFoodService::class.java)


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    internal fun provideCityDao(context: Context): CityDao {
        return provideDatabase(context).cityDao()
    }

    @Provides
    @Singleton
    internal fun provideFoodDao(context: Context): FoodDao {
        return provideDatabase(context).foodDao()
    }
}