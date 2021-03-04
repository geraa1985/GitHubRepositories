package com.geraa1985.githubrepositories.di.modules

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.geraa1985.githubrepositories.adapters.IImgLoader
import com.geraa1985.githubrepositories.adapters.repository.INetworkStatus
import com.geraa1985.githubrepositories.adapters.repository.IWeb
import com.geraa1985.githubrepositories.frameworks.glide.GlideImgLoader
import com.geraa1985.githubrepositories.frameworks.network.IGitHubData
import com.geraa1985.githubrepositories.frameworks.network.NetworkStatus
import com.geraa1985.githubrepositories.frameworks.network.WebData
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Singleton
    @Provides
    fun api(baseUrl: String, gson: Gson): IGitHubData =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IGitHubData::class.java)

    @Singleton
    @Provides
    fun webData(gitHubApi: IGitHubData): IWeb = WebData(gitHubApi)

    @Singleton
    @Provides
    fun networkStatus(baseUrl: String): INetworkStatus =
        NetworkStatus(baseUrl)

    @Singleton
    @Provides
    fun imgLoader(): IImgLoader<ImageView, RequestOptions> = GlideImgLoader()

}