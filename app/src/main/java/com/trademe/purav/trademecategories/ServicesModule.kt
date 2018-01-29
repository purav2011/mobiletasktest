package com.trademe.purav.trademecategories

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor
import javax.inject.Singleton

/**
 * Created by purav on 22/07/16.
 */
@Module
object ServicesModule {

    @JvmStatic
    @Provides
    @Singleton
    fun tradeMeService(
            retrofit: Retrofit
    ): TradeMeService = retrofit.create(TradeMeService::class.java)

    @JvmStatic
    @Provides
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

    @JvmStatic
    @Provides
    fun okHttpOAuthConsumer(): OkHttpOAuthConsumer = OkHttpOAuthConsumer(
            BuildConfig.CONSUMER_KEY,
            BuildConfig.CONSUMER_SECRET
    )

    @JvmStatic
    @Provides
    fun signingInterceptor(consumer: OkHttpOAuthConsumer) = SigningInterceptor(consumer)

    @JvmStatic
    @Provides
    fun okHttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            signingInterceptor: SigningInterceptor
    ): OkHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(signingInterceptor)
            .build()

    @JvmStatic
    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder().create()

    @JvmStatic
    @Provides
    fun retrofit(
            client: OkHttpClient,
            gson: Gson
    ): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
}
