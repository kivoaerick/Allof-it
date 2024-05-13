package com.example.classattendance.ui.theme.screens.signup

//import androidx.compose.material.Button
//import androidx.compose.material.OutlinedTextField
//import androidx.compose.material.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.classattendance.R
import com.example.classattendance.data.AuthViewModel


@Composable
fun SignUpScreen(navController: NavController ) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val context= LocalContext.current
//    val auth = Firebase.auth
Card {
    Image(painter = painterResource(id = R.drawable.class6), contentDescription ="" , contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello there", fontSize = 25.sp, color = Color.Yellow, fontFamily = FontFamily.Cursive)
        Text(text = "Create an account with us", fontSize = 30.sp, color = Color.Green, fontFamily = FontFamily.Cursive, textDecoration = TextDecoration.Underline)
        Image(
            painter = painterResource(id = R.drawable.signupicon),
            contentDescription = "Sign Up Icon",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Account name") },
            keyboardOptions = KeyboardOptions.Default,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, contentDescription = "")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription ="" )
            },
            visualTransformation = if (showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = if (showPassword) "Hide Password" else "Show Password")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = confirmPass,
            onValueChange = { confirmPass = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Confirm Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription ="" )
            },
            visualTransformation = if (showPassword) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { showPassword = !showPassword }) {
                    Icon(imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = if (showPassword) "Hide Password" else "Show Password")
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val myregister= AuthViewModel(navController,context)
                myregister.signup(email.trim(),password.trim(),
                    confirmPass.trim(),name.trim())
//                if (password == confirmPass) {
//                    auth.createUserWithEmailAndPassword(email, password)
//                        .addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                // Sign up successful, navigate to home screen
//                                navController.navigate("home")
//                            } else {
//                                // Sign up failed, display error message
//                                val errorMessage = task.exception?.message ?: "Sign Up failed"
//
//
//                            }
//                        }
//                } else {
//                    // Passwords do not match, display error message
//
//
//                }
            },
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Already have an account? Login",
            color = Color.Blue,
            modifier = Modifier.clickable { navController.navigate("login") }
        )
    }
}

//@Composable
//fun showToast(message: String) {
//    val context = LocalContext.current
//
//    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignUpPreview(){
    SignUpScreen(rememberNavController())
}




