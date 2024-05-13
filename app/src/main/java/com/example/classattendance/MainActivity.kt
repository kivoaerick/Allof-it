package com.example.classattendance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.classattendancemanagementapp.data.ProductViewModel
import com.example.classattendancemanagementapp.navigation.AppNavHost
import com.example.classattendancemanagementapp.ui.theme.ClassAttendanceManagementAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClassAttendanceManagementAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val productViewModel = remember {
                        ProductViewModel(navController = navController, context = applicationContext)
                    }
                    AppNavHost(navController = navController, productViewModel = productViewModel)
                }
            }
        }
    }
}