package com.example.yapechallenge.data.database.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.yapechallenge.domain.model.Menu
import kotlinx.parcelize.Parcelize

@Entity(tableName = "menu_table")
data class MenuEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "ingredient") val ingredient: String,
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "lat") val latitude: String,
    @ColumnInfo(name = "long") val longitude: String
)

fun Menu.toDatabase() = MenuEntity(
    name = name,
    description = description,
    ingredient = ingredient,
    img = img,
    latitude = latitude,
    longitude = longitude
)