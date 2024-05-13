package com.example.classattendancemanagementapp.ui.theme.screens.classes

//import com.example.classattendancemanagementapp.models.Student
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.classattendance.navigation.ROUTE_HOME

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Class(
    val className: String,
    val teacherName: String,
    val students: List<Student>
)

@Composable
fun AddClassScreen(navController: NavController) {
    var className by remember { mutableStateOf("") }
    var teacherName by remember { mutableStateOf("") }
    var studentId by remember { mutableStateOf("") }
    val students = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = className,
            onValueChange = { className = it },
            label = { Text("Class Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = teacherName,
            onValueChange = { teacherName = it },
            label = { Text("Teacher Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Student ID:")
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = studentId,
                onValueChange = { studentId = it },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (studentId.isNotBlank()) {
                    students.add(studentId)
                    studentId = ""
                }
//                navController.navigate(ROUTE_ADD_STUDENT)

            }
        ) {
            Text("Add Student")
        }

        Spacer(modifier = Modifier.height(16.dp))

        students.forEach { student ->
            Text(text = student)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (className.isNotBlank() && teacherName.isNotBlank() && students.isNotEmpty()) {
                    val classData = Class(className, teacherName, emptyList())
                    saveClassToFirestore(classData)
                    // Reset fields
                    className = ""
                    teacherName = ""
                    students.clear()
                }

            }
        ) {
            Text("Add Class")
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(text = "Back to home page", modifier = Modifier.clickable{navController.navigate(
            ROUTE_HOME
        )}
            , color = Color.Green, textDecoration = TextDecoration.Underline, fontFamily = FontFamily.Cursive)
    }
}

private fun saveClassToFirestore(classData: Class) {
    val db = Firebase.firestore
    db.collection("classes")
        .add(classData)
        .addOnSuccessListener { documentReference ->
            println("Class added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding class: $e")
        }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddClassPreview(){
    AddClassScreen(rememberNavController())
}

