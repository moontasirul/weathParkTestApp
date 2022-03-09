package com.wealthPark.testApplication.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wealthPark.testApplication.data.local.prefs.AppPreferences
import com.wealthPark.testApplication.data.remote.ApiEndPoint.Companion.BASE_URL
import com.wealthPark.testApplication.data.remote.ApiEndPoint.Companion.BASE_URL_FOR_RESERVATION
import com.wealthPark.testApplication.data.remote.apiService.IProductService
import com.wealthPark.testApplication.data.remote.apiService.IReservationService
import com.wealthPark.testApplication.utils.Constants.Companion.ASSESS_TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
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
    fun provideCarService(retrofit: Retrofit): IProductService =
        retrofit.create(IProductService::class.java)

    @Singleton
    @Provides
    fun provideCarServicePost(
        @Named("reservation") retrofit: Retrofit
    ): IReservationService =
        retrofit.create(IReservationService::class.java)


    @Singleton
    @Provides
    @Named("reservation")
    fun provideRetrofitWithToken(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_FOR_RESERVATION)
            .client(
                OkHttpClient.Builder().addNetworkInterceptor(HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                        level = HttpLoggingInterceptor.Level.HEADERS
                    })
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder().addHeader(
                            "Authorization",
                            "Bearer $ASSESS_TOKEN"
                        ).build()
                        chain.proceed(request)
                    }.build()
            )
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}