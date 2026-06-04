package com.example.lostfoundapp.navigation

sealed class Routes(
    val route: String
) {

    object Login : Routes("login")

    object Signup : Routes("signup")

    object ForgotPassword : Routes("forgot_password")

    object Home : Routes("home")

    object ReportLost : Routes("report_lost")

    object ReportFound : Routes("report_found")

    object Search : Routes("search")

    object ItemDetail : Routes("item_detail/{itemId}")

    object Activity : Routes("activity")

    object RequestDetail : Routes("request_detail/{requestId}")

    object Profile : Routes("profile")

    object EditProfile : Routes("edit_profile")

    object UserPosts: Routes("user_posts")

    object SavedPosts : Routes("saved_posts")
}
