package com.example.classattendance.data
import android.app.ProgressDialog
import com.example.classattendance.navigation.ROUTE_HOME
import com.example.classattendance.navigation.ROUTE_LOGIN
import com.example.classattendance.navigation.ROUTE_SIGN_UP
import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController: NavController, var context: Context) {

    var mAuth: FirebaseAuth
    val progress: ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("PLease Wait.....")
    }

    fun signup(email: String, password: String, confirmPass: String, name: String) {
        progress.show()

        if (email.isBlank() || password.isBlank() || confirmPass.isBlank()) {
            progress.dismiss()
            Toast.makeText(context, "Please email and password cannot be blank", Toast.LENGTH_LONG)
                .show()

            return
        } else if (password != confirmPass) {
            Toast.makeText(context, "Password do not match", Toast.LENGTH_LONG).show()
            return
        } else if (name.isBlank()) {
            Toast.makeText(context, "Please enter your name", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userdata = com.example.classattendance.models.User(
                        email,
                        password,
                        mAuth.currentUser!!.uid
                    )
                    val regRef = FirebaseDatabase.getInstance().getReference()
                        .child("Users/" + mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener {

                        if (it.isSuccessful) {
                            Toast.makeText(context, "Registered Successfully", Toast.LENGTH_LONG)
                                .show()
                            navController.navigate(ROUTE_HOME)

                        } else {
                            Toast.makeText(context, "${it.exception!!.message}", Toast.LENGTH_LONG)
                                .show()
                            navController.navigate(ROUTE_SIGN_UP)
                        }
                    }
                } else {
                    navController.navigate(ROUTE_SIGN_UP)
                }

            }
        }
        progress.dismiss()

    }

    fun login(email: String, password: String) {
        progress.show()

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            progress.dismiss()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Log in successful, navigate to home screen
                            navController.navigate("home")
                        } else {
                            // Log in failed, display error message
                            val errorMessage = task.exception?.message ?: "Unknown error"
                            // You can display the error message using a Snackbar or Toast
                            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
                            navController.navigate(ROUTE_LOGIN)
                        }
                    }
            } else {
                // Email or password is empty, display error message
                // You can display the error message using a Snackbar or Toast
                Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }

    }

    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)
    }

    fun isloggedin(): Boolean {
        return mAuth.currentUser != null
    }

}