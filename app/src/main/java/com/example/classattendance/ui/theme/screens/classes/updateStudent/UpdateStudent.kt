//package com.example.classattendance.ui.theme.screens.classes.updateStudent
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import com.example.classattendancemanagementapp.data.ProductViewModel
//import com.example.classattendancemanagementapp.ui.theme.screens.classes.Students
//
//@Composable
//fun UpdateStudentScreen(
//    navController: NavHostController,
//    studentId: String,
//    productViewModel: ProductViewModel
//) {
//    var student by remember { mutableStateOf(Students("", emptyList())) }
//
//    LaunchedEffect(key1 = studentId) {
//        student = productViewModel.getStudentById(studentId)
//    }
//
//    var name by remember { mutableStateOf(student.name) }
//    var subjects by remember { mutableStateOf(student.subjects.joinToString(", ")) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//        Text("Update Student")
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Name") }
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        OutlinedTextField(
//            value = subjects,
//            onValueChange = { subjects = it },
//            label = { Text("Subjects (comma separated)") }
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            val subjectsList = subjects.split(",").map { it.trim() }
//            productViewModel.updateStudent(studentId, name, subjectsList)
//            navController.popBackStack()
//        }) {
//            Text("Update")
//        }
//    }
//}
//
//
