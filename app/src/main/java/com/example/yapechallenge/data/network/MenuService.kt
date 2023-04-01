package com.example.yapechallenge.data.network

import com.example.yapechallenge.domain.model.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MenuService @Inject constructor(private val api: ApiClient){
    suspend fun getMenus(): List<Menu> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllMenus()
            response.body() ?: emptyList()
        }
    }
}