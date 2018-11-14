package dev.sample.authentication.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    fun getPage(@Query("page") pageNo: Int, @Query("pageSize") pageSize: Int): Call<NewsPageResponse>

}
