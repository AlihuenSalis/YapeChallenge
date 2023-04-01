package com.example.yapechallenge.data.network

import com.example.yapechallenge.domain.model.Menu
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("/all-menu")
    suspend fun getAllMenus(): Response<List<Menu>>
}