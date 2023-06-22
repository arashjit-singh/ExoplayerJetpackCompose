package com.android.exoplayerjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.android.exoplayerjetpackcompose.presentation.home_screen.HomeScreen
import com.android.exoplayerjetpackcompose.ui.theme.ExoplayerJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExoplayerJetpackComposeTheme {
                HomeScreen()
            }
        }
    }
}
