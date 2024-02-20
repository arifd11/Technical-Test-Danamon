package com.arifdauhi.technicaldanamon.di

import com.arifdauhi.technicaldanamon.BuildConfig
import com.arifdauhi.technicaldanamon.interfaces.AlbumInterface
import com.arifdauhi.technicaldanamon.interfaces.UserInterface
import com.arifdauhi.technicaldanamon.services.AlbumServices
import com.arifdauhi.technicaldanamon.services.UserServices
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val base_url = BuildConfig.BASE_URL

    private const val album_url = BuildConfig.URL_ALBUM

    @Provides
    fun provideUserInterface(retrofitFactory: RetrofitFactory): UserInterface =
        retrofitFactory.create(base_url).build()

    @Provides
    fun provideAlbumInterface(retrofitFactory: RetrofitFactory): AlbumInterface =
        retrofitFactory.create(album_url).build()

    @Provides
    fun provideUserService(api: UserInterface): UserServices = UserServices(api)

    @Provides
    fun provideAlbumService(api: AlbumInterface): AlbumServices = AlbumServices(api)

    /*@Provides
    fun getInstance(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(base_url)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }*/
}

@AssistedFactory
interface RetrofitFactory {
    fun create(baseUrl: String): RetrofitBuilder
}

class RetrofitBuilder @AssistedInject constructor(
    @Assisted val baseUrl: String
) {
    inline fun <reified T> build(): T {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create()
    }
}