package com.example.lostfoundapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

import com.example.lostfoundapp.ui.screens.*
import com.example.lostfoundapp.data.model.ReportType

@Composable
fun AppNavigation() {

    val navController =
        rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(
            Routes.Login.route
        ) {

            LoginScreen(
                onLoginClick = {
                    navController.navigate(
                        Routes.Home.route
                    )
                },

                onSignupClick = {
                    navController.navigate(
                        Routes.Signup.route
                    )
                }
            )
        }

        composable(
            Routes.Signup.route
        ) {

            SignUpScreen(
                onLoginClick = {
                    navController.popBackStack()
                },

                onSignupSuccess = {
                    navController.navigate(
                        Routes.Home.route
                    )
                }
            )
        }

        composable(
            Routes.Home.route
        ) {

            HomeScreen(
                onLostClick = {
                    navController.navigate(
                        Routes.ReportLost.route
                    )
                },

                onFoundClick = {
                    navController.navigate(
                        Routes.ReportFound.route
                    )
                }
            )
        }

        composable(
            Routes.ReportLost.route
        ) {

            ReportItemScreen(
                reportType = ReportType.LOST,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            Routes.ReportFound.route
        ) {

            ReportItemScreen(
                reportType = ReportType.FOUND,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}