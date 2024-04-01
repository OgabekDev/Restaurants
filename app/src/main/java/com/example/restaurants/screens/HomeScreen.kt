package com.example.restaurants.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.restaurants.R
import com.example.restaurants.mvvm.HomeViewModel
import com.example.restaurants.screens.destinations.DetailsScreenDestination
import com.example.restaurants.screens.destinations.MapScreenDestination
import com.example.restaurants.views.RestaurantView
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.extension.compose.MapboxMap
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        viewModel.getRestaurantsList()
    }

    val context = LocalContext.current

    if (viewModel.isError.value) {
        Toast.makeText(context, viewModel.errorMessage.value, Toast.LENGTH_SHORT).show()
        viewModel.isError.value = false
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        if (viewModel.isLoading.value) CircularProgressIndicator()

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(id = R.string.str_restaurants),
                modifier = Modifier
                    .padding(10.dp),
                fontStyle = FontStyle.Normal,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            LazyColumn {
                items(items = viewModel.data.toList(), key = { it.id }) { it ->
                    RestaurantView(restaurant = it) {
                        navigator.navigate(DetailsScreenDestination(it))
                    }
                }
            }
        }

    }

}