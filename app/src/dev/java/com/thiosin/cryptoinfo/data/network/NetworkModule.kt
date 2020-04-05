package com.thiosin.cryptoinfo.data.network

import com.squareup.moshi.Moshi
import com.thiosin.cryptoinfo.BuildConfig
import com.thiosin.cryptoinfo.data.network.coinlayer.CoinLayerApi
import com.thiosin.cryptoinfo.data.network.coinlib.CoinLibApi
import com.thiosin.cryptoinfo.data.network.common.interceptors.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    @CoinLibAuthentication
    fun provideCoinLibAuthInterceptor(): Interceptor {
        return AuthInterceptor("key", BuildConfig.COINLIB_API_KEY)
    }

    @Provides
    @Singleton
    @CoinLibOkHttp
    fun provideCoinLibOkHttpClient(
        @CoinLibAuthentication coinLibAuthInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(coinLibAuthInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(loggingInterceptor)
                }
            }
            .build()
    }

    @Provides
    @Singleton
    @CoinLibRetrofit
    fun provideCoinLibRetrofit(@CoinLibOkHttp okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(BuildConfig.COINLIB_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinLibApi(@CoinLibRetrofit retrofit: Retrofit): CoinLibApi = retrofit.create()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CoinLibAuthentication

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CoinLibOkHttp

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CoinLibRetrofit

    @Provides
    @Singleton
    @CoinLayerAuthentication
    fun provideCoinLayerAuthInterceptor(): Interceptor {
        return AuthInterceptor("access_key", BuildConfig.COINLAYER_API_KEY)
    }

    @Provides
    @Singleton
    @CoinLayerOkHttp
    fun provideCoinLayerOkHttpClient(
        @CoinLayerAuthentication coinLayerAuthInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(coinLayerAuthInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(loggingInterceptor)
                }
            }
            .build()
    }

    @Provides
    @Singleton
    @CoinLayerRetrofit
    fun provideCoinLayerRetrofit(@CoinLayerOkHttp okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .baseUrl(BuildConfig.COINLAYER_BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinLayerApi(@CoinLayerRetrofit retrofit: Retrofit): CoinLayerApi = retrofit.create()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CoinLayerAuthentication

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CoinLayerOkHttp

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CoinLayerRetrofit


}