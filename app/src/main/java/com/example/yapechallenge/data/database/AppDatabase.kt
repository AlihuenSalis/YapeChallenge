package com.example.yapechallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.yapechallenge.data.database.entities.MenuEntity
import com.example.yapechallenge.domain.model.Menu

@Database(entities = [MenuEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getMenuDao(): MenuDao
}