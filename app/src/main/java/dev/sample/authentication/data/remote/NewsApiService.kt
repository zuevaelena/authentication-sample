package dev.sample.authentication.data.remote

import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {

    @GET("top-headlines")
    fun getPage(): Call<NewsPageResponse>

}
