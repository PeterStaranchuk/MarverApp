package com.ps.superheroapp.di.modules

import com.ps.superheroapp.BuildConfig
import com.ps.superheroapp.objects.HttpKeys
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
class InterceptorsModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Named(HEADER)
    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val builder = chain.request().newBuilder().apply {
                header(HttpKeys.CONTENT_TYPE, "application/json")
            }
            chain.proceed(builder.build())
        }
    }

    @Named(AUTH)
    @Provides
    @Singleton
    fun provideAuthInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url().newBuilder().apply {
                addQueryParameter(HttpKeys.API_KEY, BuildConfig.API_KEY)
                addQueryParameter(HttpKeys.TIMESTAMP, BuildConfig.TIMESTAMP)
                addQueryParameter(HttpKeys.HASH, BuildConfig.HASH.toLowerCase())
            }.build()

            chain.proceed(chain.request().newBuilder().url(url).build())
        }
    }

    companion object {
        const val LOGGING = "LOGGING"
        const val HEADER = "HEADER"
        const val AUTH = "AUTH"
    }
}