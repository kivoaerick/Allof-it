package com.example.classattendance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
<<<<<<< HEAD
import androidx.compose.ui.Modifier
import com.example.classattendance.navigation.AppNavHost
import com.example.classattendance.ui.theme.ClassattendanceTheme

=======
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.classattendancemanagementapp.data.ProductViewModel
import com.example.classattendancemanagementapp.navigation.AppNavHost
import com.example.classattendancemanagementapp.ui.theme.ClassAttendanceManagementAppTheme
>>>>>>> 9963188c0ea532e912777466734b3221c9e5c992

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
<<<<<<< HEAD
            ClassattendanceTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavHost()

                    }

=======
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
>>>>>>> 9963188c0ea532e912777466734b3221c9e5c992
                }
            }
        }
    }
<<<<<<< HEAD
=======
}
>>>>>>> 9963188c0ea532e912777466734b3221c9e5c992
