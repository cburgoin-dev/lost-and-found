package com.example.lostfoundapp.navigation

sealed class Routes(
    val route: String
) {

    object Login : Routes("login")

    object Signup : Routes("signup")

    object ForgotPassword : Routes("forgot_password")

    object Home : Routes("home")

    object ReportLost :
        Routes("report_lost")

    object ReportFound :
        Routes("report_found")
}