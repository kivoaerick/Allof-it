package com.example.classattendance.ui.theme.screens.classes.viewAttendance

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.classattendance.navigation.ROUTE_HOME
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

data class Attendance(
    val studentId: String,
    val isPresent: Boolean,
    val date: String
)

// Import necessary packages

@Composable
fun ViewAttendanceScreen(navController: NavController) {
    var attendanceList by remember { mutableStateOf<List<Attendance>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    // Fetch attendance data from Firestore when the screen is first displayed
    LaunchedEffect(Unit) {
        val db = Firebase.firestore
        try {
            db.collection("attendance")
                .get()
                .addOnSuccessListener { result ->
                    val list = mutableListOf<Attendance>()
                    for (document in result) {
                        @Suppress("DEPRECATION") val attendance = document.toObject<Attendance>()
                        list.add(attendance)
                    }
                    attendanceList = list
                    loading = false
                }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error fetching attendance data: $exception")
                    loading = false
                }
        } catch (e: Exception) {
            Log.e(TAG, "Exception fetching attendance data: $e")
            loading = false
        }
    }

    if (loading) {
        // Show loading indicator
        Text("Loading...")
    } else {
        // Show attendance list
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Attendance List")
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(attendanceList) { attendance ->
                    AttendanceItem(attendance = attendance)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Go back to home page",
                modifier = Modifier.clickable {
                    navController.navigate(ROUTE_HOME)
                }
            )
        }
    }
}

@Composable
fun AttendanceItem(attendance: Attendance) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Student ID: ${attendance.studentId}")
        Text(text = "Present: ${attendance.isPresent}")
        Text(text = "Date: ${attendance.date}")
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewAttendancePreview() {
    ViewAttendanceScreen(navController = rememberNavController())
}
