
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

data class Attendance(
    val studentId: String,
    val isPresent: Boolean,
    val date: String
)

@Composable
inline fun <reified MyStudent> MarkAttendanceScreen(navController: NavController) {
    val db = Firebase.firestore
    var students by remember { mutableStateOf<List<MyStudent>>(emptyList()) }
    var currentIndex by remember { mutableStateOf(0) }
    var present by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf("") }

    // Fetch students from Firestore when the screen is first displayed
    LaunchedEffect(Unit) {
        db.collection("students")
            .get()
            .addOnSuccessListener { result ->
                val studentsList = mutableListOf<MyStudent>()
                for (document in result) {
                    val student = document.toObject(MyStudent::class.java)
                    studentsList.add(student)
                }
                students = studentsList
            }
            .addOnFailureListener { exception ->
                // Handle any errors
            }
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.teachericon), contentDescription ="", modifier=Modifier.size(116.dp), alignment = Alignment.CenterStart)
        Text("Mark Attendance", color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))
        Box (modifier = Modifier.fillMaxWidth()){
            Column {
                Text("Student Name: ${students.getOrNull(currentIndex) ?: ""}")

                Spacer(modifier = Modifier.height(16.dp))

                Checkbox(
                    checked = present,
                    onCheckedChange = { isChecked ->
                        present = isChecked
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = selectedDate,
                    onValueChange = { selectedDate = it },
                    label = { Text("Select Date", color = Color.Black) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            // Save attendance data to Firestore
                            students.getOrNull(currentIndex)?.let { student ->
                                val attendanceData = Attendance(student.toString(), present, selectedDate)
                                db.collection("attendance")
                                    .add(attendanceData)
                                    .addOnSuccessListener {
                                        // Handle success
                                    }
                                    .addOnFailureListener { exception ->
                                        // Handle failure
                                    }
                            }

                            // Move to the next student
                            if (currentIndex < students.size - 1) {
                                currentIndex++
                                present = false
                            } else {

                                // All students marked, navigate back or to another screen
                                // Example:
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Next")
                    }
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "Back to home page", modifier = Modifier.clickable{navController.navigate(
                        ROUTE_HOME
                    )}
                        , color = Color.Green, textDecoration = TextDecoration.Underline, fontFamily = FontFamily.Cursive)

            }

        }


//            Spacer(modifier = Modifier.height(16.dp))
//            Image(painter = painterResource(id = R.drawable.class6), contentDescription = "", modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//                .size(500.dp))
//            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MarkAttendancePreview() {
    MarkAttendanceScreen<Any?>(navController = rememberNavController())
}
