package com.rtllabs.githubappexplore.di

import com.rtllabs.githubappexplore.BuildConfig
import com.rtllabs.githubappexplore.data.remote.GitRepoSearchApiService
import com.rtllabs.githubappexplore.utils.Constants.CONNECTION_TIMEOUT
import com.rtllabs.githubappexplore.utils.Constants.READ_TIMEOUT
import com.rtllabs.githubappexplore.utils.Constants.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE


        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // change to false if paging issue
            if (BuildConfig.DEBUG)
                addNetworkInterceptor(loggingInterceptor)
        }.build()
    }

    /*@Singleton
    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.githubAPI)
            .client(okHttpClient)
           //.addCallAdapterFactory(ApiResultCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
    }*/

    @Singleton
    @Provides
    fun  provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return  Retrofit.Builder()
            .baseUrl(BuildConfig.githubAPI)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerGitRepoSearchApiService(retrofit: Retrofit):GitRepoSearchApiService{
        return retrofit.create(GitRepoSearchApiService::class.java)
    }
}
