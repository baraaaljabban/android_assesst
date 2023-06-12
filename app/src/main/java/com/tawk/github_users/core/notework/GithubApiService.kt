package com.tawk.github_users.core.notework

import com.tawk.github_users.core.connectivity.ConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun retrofitBuilder(connectivityInterceptor: ConnectivityInterceptor): Retrofit {

    val requestInterceptor = Interceptor { chain ->

        val url = chain.request()
            .url
            .newBuilder()
            .build()
        val request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return@Interceptor chain.proceed(request)
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply { this.level = HttpLoggingInterceptor.Level.BODY })
        .addInterceptor { chain ->
            val request = chain.request().newBuilder().addHeader("Authorization", "Bearer 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").build()
            chain.proceed(request)}
        .addInterceptor(connectivityInterceptor)
        .build()

    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}




