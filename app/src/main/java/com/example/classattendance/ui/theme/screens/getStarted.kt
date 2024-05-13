package com.example.classattendancemanagementapp.ui.theme.screens.classes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.classattendance.R
import com.example.classattendance.navigation.ROUTE_LOGIN

@Composable
fun ForegroundScreen(navController: NavHostController) {

    Column(modifier= Modifier
        .fillMaxSize()
        .background(Color.Cyan)
        , verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(painter = painterResource(id = R.drawable.teachericon), contentDescription ="", modifier=Modifier.size(116.dp))
        Text(text = "Welcome to teachers app", fontFamily = FontFamily.Cursive, fontSize = 40.sp, color = Color.Yellow)
        Text(text = "This app will enable teachers to master the attendance of students.It is a user friendly app that can allow you to navigate from one screen to another without any problems", color = Color.Black)
        Text(text = "So create an account with us to enjoy our services to create an account, click on the getStarted button and fill the fields required.Once you have done that, you can navigate to home screen where you'll be able to add a class , list of students and the subjects they do. You can also view all the students and their attendance ", color = Color.Black)
        Spacer(modifier = Modifier.height(30.dp))
        Row(modifier=Modifier.fillMaxWidth())
        {
Card {
    Image(painter = painterResource(id = R.drawable.ic1), contentDescription ="", modifier = Modifier.size(116.dp) )
}
            Card {
                Image(painter = painterResource(id = R.drawable.ic2), contentDescription ="", modifier=Modifier.size(160.dp))
            }
            Card {
                Image(painter = painterResource(id = R.drawable.ic3), contentDescription ="", modifier=Modifier.size(116.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(ROUTE_LOGIN)}, colors = ButtonDefaults.buttonColors(Color.Green)) {
            Text(text = "getStarted", textDecoration = TextDecoration.Underline)
            
        }

    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ForegroundPreview(){
    ForegroundScreen(rememberNavController())
}