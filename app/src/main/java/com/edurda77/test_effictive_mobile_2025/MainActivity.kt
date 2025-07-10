package com.edurda77.test_effictive_mobile_2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.edurda77.test_effictive_mobile_2025.ui.theme.Test_effictive_mobile_2025Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Test_effictive_mobile_2025Theme {

            }
        }
    }
}