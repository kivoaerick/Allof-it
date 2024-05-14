package com.example.classattendance.navigation

import MarkAttendanceScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
<<<<<<< HEAD

=======
import androidx.navigation.navArgument
import com.example.classattendance.ui.theme.screens.classes.updateStudent.UpdateStudentScreen
>>>>>>> 9963188c0ea532e912777466734b3221c9e5c992
import com.example.classattendance.ui.theme.screens.classes.viewAttendance.ViewAttendanceScreen
import com.example.classattendance.ui.theme.screens.classes.viewStudent.ViewStudentsScreen
import com.example.classattendance.ui.theme.screens.home.HomeScreen
import com.example.classattendance.ui.theme.screens.login.LoginScreen
import com.example.classattendance.ui.theme.screens.signup.SignUpScreen
//import com.example.classattendancemanagementapp.data.ProductViewModel
import com.example.classattendancemanagementapp.ui.theme.screens.classes.AddClassScreen
import com.example.classattendancemanagementapp.ui.theme.screens.classes.AddStudentScreen
import com.example.classattendancemanagementapp.ui.theme.screens.classes.ForegroundScreen
import com.example.classattendancemanagementapp.ui.theme.screens.classes.UpdateDeleteStudentScreen


<<<<<<< HEAD
=======
//import com.example.classattendancemanagementapp.ui.theme.screens.register.RegisterActivity
//import com.example.myapplication100.ui.theme.screens.products.UpdateProductsScreen
//import com.example.myapplication100.ui.theme.screens.products.ViewProductsScreen

>>>>>>> 9963188c0ea532e912777466734b3221c9e5c992
@Composable
fun TopBar(navController: NavHostController) {

}

@Composable
fun AppNavHost(modifier: Modifier = Modifier,
               navController: NavHostController = rememberNavController(),
               startDestination: String = ROUTE_GET_STARTED,
<<<<<<< HEAD

){

=======
//               productViewModel: ProductViewModel
){
//    val navController = rememberNavController()
//
//    navController.navigate(ROUTE_UPDATE_STUDENT) {
//        val studentId = passedData.arguments?.getString("studentId") ?: ""
//        it.passedData.putString("studentId", studentId)
//    }
>>>>>>> 9963188c0ea532e912777466734b3221c9e5c992
    NavHost(navController =navController ,
        modifier = modifier,
        startDestination = startDestination){
        composable(ROUTE_HOME){
            TopBar(navController)
            HomeScreen(navController)
        }
        composable(ROUTE_GET_STARTED){
            ForegroundScreen(navController)
        }
        composable(ROUTE_LOGIN){
            TopBar(navController)
            LoginScreen(navController)
        }
        composable(ROUTE_SIGN_UP){
            TopBar(navController)
            SignUpScreen(navController)
        }
        composable(ROUTE_ADD_STUDENT){
            TopBar(navController)
            AddStudentScreen(navController)

        }
        composable(ROUTE_UPDATE_DELETE){
            UpdateDeleteStudentScreen(navController )
        }
        composable(ROUTE_ADD_CLASS){
            AddClassScreen(navController)
        }
        composable(ROUTE_MARK_ATTENDANCE){
            MarkAttendanceScreen<Any?>(navController)
        }
        composable(ROUTE_VIEW_STUDENTS){
            TopBar(navController)
            ViewStudentsScreen(navController)
        }
        composable(ROUTE_VIEW_ATTENDANCE){
            ViewAttendanceScreen(navController)
        }
//        composable(ROUTE_FETCH_STUDENTS)
//            StudentsScreen(navController)
//        }
//        composable(
//            route = "$ROUTE_UPDATE_STUDENT/{studentId}",
//            arguments = listOf(navArgument("studentId") { type = NavType.StringType })
//        ) { navBackStackEntry ->
//            val studentId = navBackStackEntry.arguments?.getString("studentId")
//            studentId?.let {
//                UpdateStudentScreen(navController = navController, studentId = it)
//            }
        }
    }