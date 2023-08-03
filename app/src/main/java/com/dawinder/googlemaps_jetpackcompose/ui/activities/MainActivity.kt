package com.dawinder.googlemaps_jetpackcompose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dawinder.googlemaps_jetpackcompose.ui.composables.MainScreen
import com.dawinder.googlemaps_jetpackcompose.ui.theme.GoogleMapsJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoogleMapsJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    MainScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoogleMapsJetpackComposeTheme {
        MainScreen()
    }
}