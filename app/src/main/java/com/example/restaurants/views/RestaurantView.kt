package com.example.restaurants.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.restaurants.model.Restaurant
import com.google.firebase.firestore.GeoPoint

@Composable
fun RestaurantView(
    restaurant: Restaurant,
    onClick: (Restaurant) -> Unit
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
                    onClick.invoke(restaurant)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(75.dp, 75.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator()

                AsyncImage(
                    model = restaurant.profileImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(75.dp, 75.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.FillWidth
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp)
            ) {
                Text(
                    text = restaurant.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal
                )

                Text(
                    text = restaurant.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    fontStyle = FontStyle.Normal,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0X88888888))
        )
    }

}

@Preview
@Composable
fun RestaurantViewPreview() {
    RestaurantView(
        restaurant = Restaurant(
            "",
            "Favvora Oilaviy Restarani",
            "Favvora oilaviy restorani bu Urganch shahar markazida joylashgan oilaviy restoran hisoblanadi. Biz barchani  restaranimizga taklif qilamiz. Yaqin insonlaringiz bilan vaqtni shirin va maroqli o'tiring.",
            41.55890447572358,
            60.613889184096756,
            "https://api.favvora-urgench.uz/media/cache/65/ff/65ffbad88bf28cea1da40017c2c10618.jpg",
            arrayListOf(
                "https://api.favvora-urgench.uz/media/cache/d3/d3/d3d3eadd4f0a858993dab8b04d99dd2a.jpg",
                "https://api.favvora-urgench.uz/media/cache/d3/d3/d3d3eadd4f0a858993dab8b04d99dd2a.jpg",
                "https://api.favvora-urgench.uz/media/cache/d3/d3/d3d3eadd4f0a858993dab8b04d99dd2a.jpg",
                "https://api.favvora-urgench.uz/media/cache/d3/d3/d3d3eadd4f0a858993dab8b04d99dd2a.jpg",
                "https://api.favvora-urgench.uz/media/cache/d3/d3/d3d3eadd4f0a858993dab8b04d99dd2a.jpg",
                "https://api.favvora-urgench.uz/media/cache/d3/d3/d3d3eadd4f0a858993dab8b04d99dd2a.jpg",
                "https://api.favvora-urgench.uz/media/cache/d3/d3/d3d3eadd4f0a858993dab8b04d99dd2a.jpg"
            ),
            "https://firebasestorage.googleapis.com/v0/b/restaurants-d0340.appspot.com/o/Screenshot%202023-12-16%20at%2010.40.58%E2%80%AFPM%20Cropped.png?alt=media&token=6fa2082c-8423-4c70-a9b5-f87576a490e3"
        )
    ) {}
}