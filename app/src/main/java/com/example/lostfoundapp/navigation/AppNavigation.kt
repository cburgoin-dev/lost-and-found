package com.example.lostfoundapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lostfoundapp.data.mock.mockPosts
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.NotificationType
import com.example.lostfoundapp.data.model.PostsScreenType

import com.example.lostfoundapp.ui.screens.*
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.ui.viewmodel.ActivityViewModel
import com.example.lostfoundapp.ui.viewmodel.EditProfileViewModel
import com.example.lostfoundapp.ui.viewmodel.UserViewModel

@Composable
fun AppNavigation() {
    var selectedPost by remember {
        mutableStateOf<ItemPost?>(null)
    }

    var selectedRequest by remember {
        mutableStateOf<Request?>(null)
    }

    val navController =
        rememberNavController()

    val userViewModel: UserViewModel = viewModel()

    val activityViewModel: ActivityViewModel = viewModel()

    val editProfileViewModel: EditProfileViewModel = viewModel()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route

    fun navigateToItemDetail(
        item: ItemPost
    ) {

        selectedPost = item

        navController.navigate(
            "item_detail/${item.id}"
        )
    }

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
                userViewModel = userViewModel,

                hasUnreadActivity = activityViewModel.hasUnreadActivity,

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

                onItemClick = ::navigateToItemDetail,

                currentRoute = currentRoute,

                onHomeClick = {

                },

                onSearchClick = {
                    navigateToBottomBarRoute(
                        Routes.Search.route
                    )
                },

                onActivityClick = {
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

        composable(
            Routes.Search.route
        ) {

            SearchScreen(
                currentRoute = currentRoute,

                hasUnreadActivity = activityViewModel.hasUnreadActivity,

                onItemClick = ::navigateToItemDetail,

                onHomeClick = {
                    navigateToBottomBarRoute(
                        Routes.Home.route
                    )
                },

                onSearchClick = {

                },

                onActivityClick = {
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

                activityViewModel = activityViewModel,

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
                userViewModel = userViewModel,

                currentRoute = currentRoute,

                hasUnreadActivity = activityViewModel.hasUnreadActivity,

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
                    navigateToBottomBarRoute(
                        Routes.Activity.route
                    )
                },

                onProfileClick = {

                },

                onEditProfileClick = {

                    editProfileViewModel.startEditing(
                        currentPhone = userViewModel.phone,
                        currentImage = userViewModel.profileImageUri
                    )

                    navController.navigate(
                        Routes.EditProfile.route
                    )
                },

                onMyPostsClick = {
                    navController.navigate(
                        Routes.UserPosts.route
                    )
                },

                onSavedPostsClick = {
                    navController.navigate(
                        Routes.SavedPosts.route
                    )
                }
            )
        }

        composable(
            Routes.EditProfile.route
        ) {

            EditProfileScreen(
                userViewModel = userViewModel,
                editProfileViewModel = editProfileViewModel,

                onBackClick = {
                    navController.popBackStack()
                },

                onSaveClick = {

                    navController.popBackStack()
                },

                onChangePhotoClick = {

                }
            )
        }

        composable(
            Routes.UserPosts.route
        ) {

            PostsScreen(
                title = "Mis publicaciones",
                posts = mockPosts,
                screenType = PostsScreenType.USER_POSTS,

                onBackClick = {
                    navController.popBackStack()
                },

                onItemClick = ::navigateToItemDetail,
            )
        }

        composable(
            Routes.SavedPosts.route
        ) {

            PostsScreen(
                title = "Guardadas",
                posts = emptyList(),
                screenType = PostsScreenType.SAVED_POSTS,

                onBackClick = {
                    navController.popBackStack()
                },

                onItemClick = ::navigateToItemDetail,
            )
        }
    }
}