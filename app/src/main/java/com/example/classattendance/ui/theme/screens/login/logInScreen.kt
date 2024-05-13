package com.example.classattendance.ui.theme.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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


//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context= LocalContext.current
//    val auth = Firebase.auth
    Box(modifier= Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.ic3), contentDescription = "image", contentScale = ContentScale.FillBounds,modifier=Modifier.fillMaxSize())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Hello", color = Color.Yellow, fontFamily = FontFamily.Cursive, fontSize = 20.sp)
        Text(text = "Welcome to login page", color = Color.Green, fontFamily = FontFamily.Cursive, fontSize = 35.sp, textDecoration = TextDecoration.Underline)
        Image(
            painter = painterResource(id = R.drawable.loginicon),
            contentDescription = "Login Icon",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions.Default,
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Email, contentDescription ="" )
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_email),
//                    contentDescription = "Email Icon"
//                )
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
                          Icon(imageVector = Icons.Filled.Lock, contentDescription = "")
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_password),
//                    contentDescription = "Password Icon"
//                )
            },
            visualTransformation = if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = if (passwordVisible)Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = if (passwordVisible) "Hide Password" else "Show Password")

                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {val mylogin= AuthViewModel(navController,context)
                mylogin.login(email.trim(),password.trim())
//                if (email.isNotEmpty() && password.isNotEmpty()) {
//                    auth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                // Log in successful, navigate to home screen
//                                navController.navigate("home")
//                            } else {
//                                // Log in failed, display error message
//                                val errorMessage = task.exception?.message ?: "Unknown error"
//                                // You can display the error message using a Snackbar or Toast
//                            }
//                        }
//                } else {
//                    // Email or password is empty, display error message
//                    // You can display the error message using a Snackbar or Toast
//                }
            },
            colors = ButtonDefaults.buttonColors(Color.Cyan)
        ) {
            Text("Log In")
        }
        Text(
            text = "Don't have an account? Sign Up",
            color = Color.Blue,
            modifier = Modifier.clickable { navController.navigate("signup") }
        )
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(rememberNavController())
}

