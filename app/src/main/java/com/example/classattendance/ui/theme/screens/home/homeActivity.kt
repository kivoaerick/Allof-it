package com.example.classattendance.ui.theme.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.classattendance.R
import com.example.classattendance.navigation.ROUTE_ADD_STUDENT
import com.example.classattendance.navigation.ROUTE_MARK_ATTENDANCE
import com.example.classattendance.navigation.ROUTE_VIEW_ATTENDANCE
import com.example.classattendance.navigation.ROUTE_VIEW_STUDENTS


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Teacher's App",
                        color = Color.Green,
                        textDecoration = TextDecoration.Underline,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 35.sp,
                        textAlign = TextAlign.Center
                    )
                },
                Modifier.background(color = Color.Cyan)
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp).background(Color.Cyan),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            LazyRow(
                modifier = Modifier.fillMaxWidth().background(Color.Green),
                content = {
                    items(6) { index ->
                        Image(
                            painter = painterResource(id = getImageResourceId(index)),
                            contentDescription = null,
                            modifier = Modifier
                                .size(190.dp)
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            )
            Image(
                painter = painterResource(id = R.drawable.teachericon),
                contentDescription = "TeachersIcon",
                modifier = Modifier.size(120.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { navController.navigate(ROUTE_ADD_STUDENT) },
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text("Add Student")
            }
//            Spacer(modifier = Modifier.height(6.dp))
//            Button(
//                onClick = { navController.navigate(ROUTE_UPDATE_STUDENT) },
//                colors = ButtonDefaults.buttonColors(Color.Green)
//            ) {
//                Text("View Students")
//            }
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = { navController.navigate(ROUTE_MARK_ATTENDANCE) },
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text("Mark Attendance")
            }
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = { navController.navigate(ROUTE_VIEW_ATTENDANCE) },
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text("View Attendance")
            }
            Text(text = "Click here to view students",modifier=Modifier.clickable{ navController.navigate(
                ROUTE_VIEW_STUDENTS)}
                , color = Color.Green, textDecoration = TextDecoration.Underline, fontFamily = FontFamily.Cursive)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Go view attendance", modifier = Modifier.clickable{navController.navigate(
                ROUTE_VIEW_ATTENDANCE)}
                , color = Color.Green, textDecoration = TextDecoration.Underline, fontFamily = FontFamily.Cursive)
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}

// Function to get image resource id based on index
private fun getImageResourceId(index: Int): Int {
    return when (index) {
        0 -> R.drawable.class1
        1 -> R.drawable.class2
        2 -> R.drawable.class7
        3 -> R.drawable.class3
        4 -> R.drawable.class4
        5 -> R.drawable.class5
        else -> R.drawable.placeholder // Placeholder image or default image
    }
}



