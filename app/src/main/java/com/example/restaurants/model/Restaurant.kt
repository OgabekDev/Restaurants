package com.example.restaurants.model

import com.google.firebase.firestore.GeoPoint
import java.io.Serializable

data class Restaurant(
    val id: String,
    val name: String,
    val description: String,
    val longitude: Double,
    val latitude: Double,
    val profileImage: String,
    val images: ArrayList<String>,
    val mainFooter: String
): Serializable