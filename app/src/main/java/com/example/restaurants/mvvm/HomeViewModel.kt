package com.example.restaurants.mvvm

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurants.model.Restaurant
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading = _isLoading

    private val _isError = mutableStateOf(false)
    val isError = _isError

    private val _errorMessage = mutableStateOf("")
    val errorMessage = _errorMessage

    private val _data = mutableStateListOf<Restaurant>()
    val data = _data

    fun getRestaurantsList() = viewModelScope.launch {
        _isLoading.value = true

        firestore.collection("restaurants")
            .get()
            .addOnSuccessListener { result ->
                _data.clear()
                for (document in result) {
                    val id = document.id
                    val name = document.data["name"] as String
                    val description = document.data["description"] as String
                    val location = document.data["location"] as GeoPoint
                    val profileImage = document.data["profileImage"] as String
                    val images = document.data["images"] as ArrayList<String>
                    val mainFooter = document.data["mainFooter"] as String

                    _data.add(Restaurant(id, name, description, location.longitude, location.latitude, profileImage, images, mainFooter))
                }

                _isLoading.value = false
            }
            .addOnFailureListener { exception ->
                _isLoading.value = false
                _isError.value = true
                _errorMessage.value = exception.localizedMessage?.toString() ?: ""
            }
    }

}