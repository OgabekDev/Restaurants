package com.example.restaurants.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.restaurants.R
import com.example.restaurants.model.Restaurant
import com.example.restaurants.screens.destinations.MapScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DetailsScreen(
    navigator: DestinationsNavigator,
    restaurant: Restaurant
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(rememberScrollState(), Orientation.Vertical),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                AsyncImage(
                    model = restaurant.mainFooter,
                    contentDescription = null,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
            }

            Text(
                text = restaurant.name,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier
                    .padding(10.dp)
            )

            Text(
                text = restaurant.description,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
            )

            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                items(restaurant.images) {
                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.5f),
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                navigator.navigate(MapScreenDestination(restaurant.longitude, restaurant.latitude))
            }
        ) {
            Text(text = stringResource(id = R.string.str_show_location))
        }

    }
}