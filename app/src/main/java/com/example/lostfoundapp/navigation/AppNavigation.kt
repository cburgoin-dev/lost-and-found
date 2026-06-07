package com.example.lostfoundapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.mock.mockPosts
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.PostsScreenType

import com.example.lostfoundapp.ui.screens.*
import com.example.lostfoundapp.data.model.ReportType
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.repository.NotificationsRepository
import com.example.lostfoundapp.ui.viewmodel.ActivityViewModel
import com.example.lostfoundapp.ui.viewmodel.EditProfileViewModel
import com.example.lostfoundapp.ui.viewmodel.NotificationsViewModel
import com.example.lostfoundapp.ui.viewmodel.PostsViewModel
import com.example.lostfoundapp.ui.viewmodel.RequestsViewModel
import com.example.lostfoundapp.ui.viewmodel.SearchViewModel
import com.example.lostfoundapp.ui.viewmodel.UserViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun AppNavigation(sessionManager: SessionManager) {
    val context = LocalContext.current

    val sessionManager = SessionManager(context)

    val startDestination =
        if(sessionManager.hasToken())
            Routes.Home.route
        else
            Routes.Login.route

    var selectedPost by remember {
        mutableStateOf<ItemPost?>(null)
    }

    var selectedRequest by remember {
        mutableStateOf<Request?>(null)
    }

    val navController =
        rememberNavController()

    val userViewModel: UserViewModel = viewModel()

    val postsViewModel = remember {

        PostsViewModel(
            SessionManager(context)
        )
    }

    val searchViewModel = remember {

        SearchViewModel(
            SessionManager(context)
        )
    }

    val requestsViewModel = remember {

        RequestsViewModel(
            SessionManager(context)
        )
    }



    val activityViewModel: ActivityViewModel = viewModel()

    LaunchedEffect(
        requestsViewModel.requests
    ) {

        activityViewModel.updatePendingRequests(
            requestsViewModel.requests
        )
    }

    val editProfileViewModel: EditProfileViewModel = viewModel()

    val notificationsViewModel = remember {
        NotificationsViewModel(repository = NotificationsRepository(sessionManager))
    }

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

    LaunchedEffect(Unit) {
        while (isActive){
            notificationsViewModel.getNotifications()
            requestsViewModel.loadRequests()
            delay(3000)
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
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

                postsViewModel = postsViewModel,

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

                postsViewModel = postsViewModel,

                onBackClick = {
                    navController.popBackStack()
                },

                onPostCreated = {

                    navController.navigate(
                        Routes.Home.route
                    )
                }
            )
        }

        composable(
            Routes.ReportFound.route
        ) {

            ReportItemScreen(
                reportType = ReportType.FOUND,

                postsViewModel = postsViewModel,

                onBackClick = {
                    navController.popBackStack()
                },

                onPostCreated = {

                    navController.navigate(
                        Routes.Home.route
                    )
                }
            )
        }

        composable(
            Routes.Search.route
        ) {

            SearchScreen(
                searchViewModel = searchViewModel,

                postsViewModel = postsViewModel,

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

                    requestsViewModel = requestsViewModel,

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

                hasUnreadActivity =
                    activityViewModel.hasUnreadActivity,

                activityViewModel = activityViewModel,

                requestsViewModel = requestsViewModel,

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

                notificationsViewModel = notificationsViewModel,

                onNotificationClick = { notification ->

                    when(notification.type) {

                        "Solicitud aprobada",
                        "Posible coincidencia" -> {

                            notification.post_id?.let { itemId ->

                                selectedPost =
                                    mockPosts.firstOrNull {
                                        it.id == itemId
                                    }

                                navController.navigate(
                                    "item_detail/$itemId"
                                )
                            }
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

                    requestsViewModel = requestsViewModel,

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
                },

                onLogoutClick =  {
                    navController.navigate(Routes.Login.route)
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