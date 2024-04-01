package com.example.restaurants.screens

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.example.restaurants.R
import com.google.firebase.firestore.GeoPoint
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapInitOptions
import com.mapbox.maps.MapboxExperimental
import com.mapbox.maps.Style
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotationGroup
import com.mapbox.maps.extension.style.expressions.dsl.generated.literal
import com.mapbox.maps.extension.style.expressions.generated.Expression
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.annotation.AnnotationConfig
import com.mapbox.maps.plugin.annotation.AnnotationSourceOptions
import com.mapbox.maps.plugin.annotation.ClusterOptions
import com.mapbox.maps.plugin.annotation.createAnnotationPlugin
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.ramcosta.composedestinations.annotation.Destination

@OptIn(MapboxExperimental::class)
@Destination
@Composable
fun MapScreen(
    longitude: Double,
    latitude: Double
) {

    val context = LocalContext.current

    val mapViewportState = rememberMapViewportState {
        // Set the initial camera position
        setCameraOptions {
            center(Point.fromLngLat(longitude, latitude))
            zoom(16.0)
            pitch(0.0)
        }
    }

    Box(
        contentAlignment = Alignment.BottomEnd
    ) {
        MapboxMap(
            modifier = Modifier
                .fillMaxSize(),
            mapInitOptionsFactory = { context ->
                MapInitOptions(
                    context = context,
                    styleUri = Style.STANDARD,
                    cameraOptions = CameraOptions.Builder()
                        .center(Point.fromLngLat(longitude, latitude))
                        .zoom(16.0)
                        .build()
                )
            },
            mapViewportState = mapViewportState
        ) {

            PointAnnotationGroup(
                annotations = listOf(
                    PointAnnotationOptions()
                        .withPoint(Point.fromLngLat(longitude, latitude))
                        .withIconImage(getBitmapFromVectorDrawable(context, R.drawable.ic_location))
                        .apply {
                            iconSize = 1.5
                        }
                ),
                annotationConfig = AnnotationConfig(
                    annotationSourceOptions = AnnotationSourceOptions(
                        clusterOptions = ClusterOptions(
                            textColorExpression = Expression.color(Color.YELLOW),
                            textColor = Color.BLACK,
                            textSize = 20.0,
                            circleRadiusExpression = literal(25.0),
                            colorLevels = listOf(
                                Pair(100, Color.RED),
                                Pair(50, Color.BLUE),
                                Pair(0, Color.GREEN)
                            )
                        )
                    )
                )
            )

        }

        Button(onClick = {
            mapViewportState.flyTo(
                cameraOptions = cameraOptions {
                    center(Point.fromLngLat(longitude, latitude))
                    zoom(16.0)
                    pitch(0.0)
                },
                MapAnimationOptions.mapAnimationOptions { duration(500) }
            )
        }) {
            Icon(
                painterResource(id = R.drawable.ic_location),
                contentDescription = null
            )
        }
    }

}

private fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
    val drawable = ContextCompat.getDrawable(context, drawableId)
    val bitmap = Bitmap.createBitmap(
        drawable!!.intrinsicWidth,
        drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}