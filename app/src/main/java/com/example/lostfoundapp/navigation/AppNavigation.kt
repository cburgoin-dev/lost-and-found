package com.example.lostfoundapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.*
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lostfoundapp.data.mock.mockPosts
import com.example.lostfoundapp.data.mock.mockRequests
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.NotificationType

import com.example.lostfoundapp.ui.screens.*
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.model.Request

@Composable
fun AppNavigation() {
    var selectedPost by remember {
        mutableStateOf<ItemPost?>(null)
    }

    var selectedRequest by remember {
        mutableStateOf<Request?>(null)
    }

    var hasUnreadActivity by remember {
        mutableStateOf(true)
    }

    val navController =
        rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    fun navigateToBottomBarRoute(route: String) {

        navController.navigate(route) {

            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }

            launchSingleTop = true

            restoreState = true
        }
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
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
                },

                onForgotPasswordClick = {
                    navController.navigate(
                        Routes.ForgotPassword.route
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
            Routes.ForgotPassword.route
        ) {

            ForgotPasswordScreen(
                onBackToLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            Routes.Home.route
        ) {

            HomeScreen(
                hasUnreadActivity = hasUnreadActivity,

                onLostClick = {
                    navController.navigate(
                        Routes.ReportLost.route
                    )
                },

                onFoundClick = {
                    navController.navigate(
                        Routes.ReportFound.route
                    )
                },

                onItemClick = { itemPost ->

                    selectedPost = itemPost

                    navController.navigate(
                        "item_detail/${itemPost.id}"
                    )
                },

                currentRoute = currentRoute,

                onHomeClick = {

                },

                onSearchClick = {
                    navigateToBottomBarRoute(
                        Routes.Search.route
                    )
                },

                onActivityClick = {
                    hasUnreadActivity = false

                    navigateToBottomBarRoute(
                        Routes.Activity.route
                    )
                },

                onProfileClick = {
                    navigateToBottomBarRoute(
                        Routes.Profile.route
                    )
                }
            )
        }

        composable(
            Routes.Search.route
        ) {

            SearchScreen(
                onItemClick = { itemPost ->

                    selectedPost = itemPost

                    navController.navigate(
                        "item_detail/${itemPost.id}"
                    )
                },

                currentRoute = currentRoute,

                onHomeClick = {
                    navigateToBottomBarRoute(
                        Routes.Home.route
                    )
                },

                onSearchClick = {

                },

                onActivityClick = {
                    hasUnreadActivity = false

                    navigateToBottomBarRoute(
                        Routes.Activity.route
                    )
                },

                onProfileClick = {
                    navigateToBottomBarRoute(
                        Routes.Profile.route
                    )
                }
            )
        }

        composable(
            route = Routes.ItemDetail.route
        ) {

            selectedPost?.let { itemPost ->

                ItemDetailScreen(
                    itemPost = itemPost,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(
            Routes.Activity.route
        ) {

            ActivityScreen(
                currentRoute = currentRoute,

                onHomeClick = {
                    navigateToBottomBarRoute(
                        Routes.Home.route
                    )
                },

                onSearchClick = {
                    navigateToBottomBarRoute(
                        Routes.Search.route
                    )
                },

                onActivityClick = {

                },

                onProfileClick = {
                    navigateToBottomBarRoute(
                        Routes.Profile.route
                    )
                },

                onRequestClick = { request ->
                    selectedRequest = request

                    navController.navigate(
                        Routes.RequestDetail.route
                    )
                },

                onNotificationClick = { notification ->

                    when(notification.type) {

                        NotificationType.REQUEST_APPROVED,
                        NotificationType.MATCH_FOUND -> {

                            notification.relatedItemId?.let { itemId ->

                                selectedPost =
                                    mockPosts.firstOrNull {
                                        it.id == itemId
                                    }

                                navController.navigate(
                                    "item_detail/$itemId"
                                )
                            }
                        }

                        NotificationType.REQUEST_REJECTED,
                        NotificationType.SYSTEM -> {

                            // No navigation
                        }
                    }
                }
            )
        }

        composable(
            Routes.RequestDetail.route
        ) {

            selectedRequest?.let { request ->

                RequestDetailScreen(
                    request = request,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onApproveClick = {},
                    onRejectClick = {}
                )
            }
        }

        composable(
            Routes.Profile.route
        ) {

            ProfileScreen(
                currentRoute = currentRoute,

                onHomeClick = {
                    navigateToBottomBarRoute(
                        Routes.Home.route
                    )
                },

                onSearchClick = {
                    navigateToBottomBarRoute(
                        Routes.Search.route
                    )
                },

                onActivityClick = {
                    hasUnreadActivity = false

                    navigateToBottomBarRoute(
                        Routes.Activity.route
                    )
                },

                onProfileClick = {

                },

                onEditProfileClick = {

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