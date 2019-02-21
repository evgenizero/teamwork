package com.teamwork.network.di.modules

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.teamwork.network.interceptors.BasicAuthInterceptor
import com.teamwork.network.services.ProjectsService
import com.teamwork.network.utils.NetworkConstants
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Named(TEAMWORK_BASE_URL_KEY)
    @Singleton
    @Provides
    fun provideTeamworkUrl(): String {
        return NetworkConstants.TEAMWORK_BASE_URL
    }

    @Provides
    @Singleton
    @Named(API_TOKEN_KEY)
    fun provideApiTokenKey(): String {
        return NetworkConstants.API_TOKEN
    }

    @Singleton
    @Provides
    fun provideOkHttp(@Named(API_TOKEN_KEY) username: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(BasicAuthInterceptor(username))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        @Named(TEAMWORK_BASE_URL_KEY) teamworkBaseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(teamworkBaseUrl)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideProjectsService(retrofit: Retrofit): ProjectsService {
        return retrofit.create(ProjectsService::class.java)
    }

    companion object {
        private const val TEAMWORK_BASE_URL_KEY = "teamworkBaseUrl"
        private const val API_TOKEN_KEY = "ApiTokenKey"
    }
}
