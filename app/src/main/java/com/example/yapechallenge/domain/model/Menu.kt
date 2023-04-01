package com.example.yapechallenge.domain.model

import android.os.Parcelable
import com.example.yapechallenge.data.database.entities.MenuEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class Menu(
    val name: String,
    val description: String,
    val ingredient: String,
    val img: String,
    val latitude: String,
    val longitude: String
): Parcelable

fun MenuEntity.toDomain() = Menu(name, description, ingredient, img, latitude, longitude)