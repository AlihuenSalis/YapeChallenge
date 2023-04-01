package com.example.yapechallenge.data

import com.example.yapechallenge.data.database.MenuDao
import com.example.yapechallenge.data.database.entities.MenuEntity
import com.example.yapechallenge.domain.model.Menu
import com.example.yapechallenge.data.network.MenuService
import com.example.yapechallenge.domain.model.toDomain
import javax.inject.Inject

class MenuRepository @Inject constructor(private val menuService: MenuService, private val menuDao: MenuDao) {

    suspend fun getAllMenusFromApi(): List<Menu> {
        return menuService.getMenus()
    }

    suspend fun getAllMenusFromDatabase(): List<Menu> {
        return menuDao.getAllMenus().map { it.toDomain() }
    }

    suspend fun insertMenus(menus: List<MenuEntity>) {
        return menuDao.insertAllMenus(menus)
    }

    suspend fun deleteAllMenus() {
        return menuDao.deleteAllMenus()
    }
}