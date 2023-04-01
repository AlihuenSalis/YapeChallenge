package com.example.yapechallenge.domain.usesCase

import com.example.yapechallenge.data.MenuRepository
import com.example.yapechallenge.data.database.entities.toDatabase
import com.example.yapechallenge.domain.model.Menu
import javax.inject.Inject

class GetMenusUseCase @Inject constructor(private val repository: MenuRepository) {
    suspend operator fun invoke(): List<Menu> {
        val menuList = repository.getAllMenusFromApi()
        return if (menuList.isNotEmpty()){
            repository.deleteAllMenus()
            repository.insertMenus(menuList.map { it.toDatabase() })
            menuList
        } else {
            repository.getAllMenusFromDatabase()
        }
    }
}