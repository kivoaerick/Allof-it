package com.example.classattendancemanagementapp.ui.theme.screens.classes

//import com.example.classattendancemanagementapp.models.Student
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.classattendance.R
import com.example.classattendance.navigation.ROUTE_HOME
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase




data class Students(
//    val id: String = "",
    val name: String,
    val subjects: List<Any>
)
@Composable
fun AddStudentScreen(navController: NavController) {
    var studentName by remember { mutableStateOf("") }

    var subjectName by remember { mutableStateOf("") }
    val subjects = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.padding(6.dp).background(Color.Cyan)

    ) {
        Image(painter = painterResource(id = R.drawable.teachericon), contentDescription ="", modifier=Modifier.size(116.dp), alignment = Alignment.CenterStart)
        Box(){
            Column(modifier=Modifier.background(Color.White).padding(5.dp)) {
                OutlinedTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    label = { Text("Student Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))



                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = subjectName,
                    onValueChange = { subjectName = it },
                    label = { Text("Subject Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            if (subjectName.isNotBlank()) {
                                subjects.add(subjectName)
                                subjectName = ""
                            }
                        },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Add Subject")
                    }

                    Button(
                        onClick = {
                            if (studentName.isNotBlank() && subjects.isNotEmpty()) {
                                val student = Students(studentName,  subjects)
                                saveStudentToFirestore(student)
                                // Reset fields
                                studentName = ""

                                subjects.clear()
                            }
                        }
                    ) {
                        Text("Add Student")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                subjects.forEach { subject ->
                    Text(text = subject)
                }
                Spacer(modifier = Modifier.height(50.dp))
                Text(text = "Back to home page", modifier = Modifier.clickable{navController.navigate(
                    ROUTE_HOME
                )}
                    , color = Color.Green, textDecoration = TextDecoration.Underline, fontFamily = FontFamily.Cursive)
            }

        }
        Spacer(modifier = Modifier.height(2.dp))
        Image(painter = painterResource(id = R.drawable.class6), contentDescription = "", modifier = Modifier
            .fillMaxWidth()
            .size(1000.dp))
Spacer(modifier = Modifier.height(5.dp))    }
}

private fun saveStudentToFirestore(student: Students) {
    val db = Firebase.firestore
    db.collection("students")
        .add(student)
        .addOnSuccessListener { documentReference ->
            println("Student added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            println("Error adding student: $e")
        }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AddStudentPreview(){
    AddStudentScreen(rememberNavController())
}
