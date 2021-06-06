package com.juandelarosa.data.api

import com.juandelarosa.data.api.responses.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

//Available services
interface TMobileService {
    @GET("test/home")
    suspend fun HomePageFeeds() : Response<ApiResponse>
}