package com.example.yapechallenge.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.yapechallenge.data.database.entities.MenuEntity

@Dao
interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMenus(listMenu: List<MenuEntity>)

    @Query("SELECT * FROM menu_table")
    suspend fun getAllMenus():List<MenuEntity>

    @Query("DELETE FROM menu_table")
    suspend fun deleteAllMenus()
}