package dev.sample.authentication.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class NewsApiRequestInterceptor(val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
                .addQueryParameter("category", "general") //TODO implement settings screen
                .build()

        val newRequest = request.newBuilder()
                .addHeader("X-Api-Key", apiKey)
                .url(url)
                .build()

        return chain.proceed(newRequest)
    }
}
