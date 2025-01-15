package com.example.exrate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.exrate.ExrateApplication
import com.example.exrate.ui.theme.ExrateTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = (application as ExrateApplication).appComponent
        enableEdgeToEdge()
        setContent {
            ExrateTheme {
                App(component.getViewModelFactory())
            }
        }
    }
}