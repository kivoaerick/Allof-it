package com.example.classattendancemanagementapp.ui.theme.screens.classes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Student(
    val studentId: String,
    val name: String,
    val subjects: List<String>
)

@Composable
fun UpdateDeleteStudentScreen(navController: NavController) {
    var studentId by remember { mutableStateOf("") }
    var studentName by remember { mutableStateOf("") }
    var subjectName by remember { mutableStateOf("") }
    val subjects = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        OutlinedTextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Student Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = subjectName,
                onValueChange = { subjectName = it },
                label = { Text("Subject Name") },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    subjects.add(subjectName)
                    subjectName = ""
                }
            ) {
                Text("Add Subject")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        subjects.forEach { subject ->
            Text(text = subject)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    if (studentId.isNotBlank() && studentName.isNotBlank() && subjects.isNotEmpty()) {
                        val student = Student(studentId, studentName,subjects)
                        updateStudent(student)
                        // Reset fields
                        studentId = ""
                        studentName = ""
                        subjects.clear()
                    }
                }
            ) {
                Text("Update")
            }

            Button(
                onClick = {
                    if (studentId.isNotBlank()) {
                        deleteStudent(studentId)
                        // Reset fields
                        studentId = ""
                        studentName = ""
                        subjects.clear()
                    }
                }
            ) {
                Text("Delete")
            }
        }
    }
}

private fun updateStudent(student: com.example.classattendancemanagementapp.ui.theme.screens.classes.Student) {
    val db = Firebase.firestore
    db.collection("students")
        .document(student.studentId)
        .set(student)
        .addOnSuccessListener {
            println("Student updated successfully")
        }
        .addOnFailureListener { e ->
            println("Error updating student: $e")
        }
}

private fun deleteStudent(studentId: String) {
    val db = Firebase.firestore
    db.collection("students")
        .document(studentId)
        .delete()
        .addOnSuccessListener {
            println("Student deleted successfully")
        }
        .addOnFailureListener { e ->
            println("Error deleting student: $e")
        }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UpdateDeletePreview(){
    UpdateDeleteStudentScreen(rememberNavController())
}
