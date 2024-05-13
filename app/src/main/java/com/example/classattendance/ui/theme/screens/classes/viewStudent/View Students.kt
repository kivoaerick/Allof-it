package com.example.classattendance.ui.theme.screens.classes.viewStudent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import com.example.classattendance.navigation.ROUTE_UPDATE_STUDENT
import com.example.classattendancemanagementapp.ui.theme.screens.classes.Students
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun ViewStudentsScreen(navController: NavController) {
    var students by remember { mutableStateOf<List<Students>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }


    // Fetch students data from Firestore when the screen is first displayed
    LaunchedEffect(Unit) {
        val db = Firebase.firestore
        try {
            db.collection("students")
                .get()
                .addOnSuccessListener { result ->
                    val studentList = mutableListOf<Students>()
                    for (document in result) {
                        val student = document.toObject(Students::class.java)
                        studentList.add(student)
                    }
                    students = studentList
                    loading = false
                }
                .addOnFailureListener {
                    // Handle any errors
                    loading = false
                }
        }
        catch (e: Exception) {
            // Handle any exceptions
            loading = false
        }
    }

    if (loading) {
        // Show loading indicator
        Text("Loading...")
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Students List")
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(students) { student ->
                    StudentItem(student = student)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            UpdateButton(navController = navController)
        }

}

}

@Composable
fun StudentItem(student: Students) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "Name: ${student.name}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Subjects: ${student.subjects.joinToString(", ")}")
    }
}

@Composable
fun UpdateButton(navController: NavController) {
    Button(
        onClick = {
            // Navigate to the update student screen
            navController.navigate(ROUTE_UPDATE_STUDENT)
        }
    ) {
        Text("Update")
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ViewStudentsPreview(){
    ViewStudentsScreen(rememberNavController())
}
