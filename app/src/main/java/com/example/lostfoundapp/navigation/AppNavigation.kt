package com.example.lostfoundapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.lostfoundapp.data.local.SessionManager
import com.example.lostfoundapp.data.mock.mockPosts
import com.example.lostfoundapp.data.model.ItemPost
import com.example.lostfoundapp.data.model.NotificationType
import com.example.lostfoundapp.data.model.PostsScreenType

import com.example.lostfoundapp.ui.screens.*
import com.example.lostfoundapp.data.model.PostType
import com.example.lostfoundapp.data.model.Request
import com.example.lostfoundapp.data.repository.CatalogRepository
import com.example.lostfoundapp.data.repository.NotificationsRepository
import com.example.lostfoundapp.data.repository.PostsRepository
import com.example.lostfoundapp.data.repository.RequestsRepository
import com.example.lostfoundapp.data.repository.UserRepository
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
fun AppNavigation(
    sessionManager: SessionManager
) {

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

    val userRepository = remember {
        UserRepository(sessionManager)
    }

    val postsRepository = remember {
        PostsRepository(sessionManager)
    }

    val catalogRepository = remember {
        CatalogRepository(sessionManager)
    }

    val requestsRepository = remember {
        RequestsRepository(sessionManager)
    }

    val notificationsRepository = remember {
        NotificationsRepository(sessionManager)
    }

    val userViewModel = remember {

        UserViewModel(
            repository = userRepository
        )
    }

    val postsViewModel = remember {

        PostsViewModel(
            postsRepository = postsRepository,
            catalogRepository = catalogRepository
        )
    }

    val searchViewModel = remember {

        SearchViewModel(
            repository = postsRepository
        )
    }

    val requestsViewModel = remember {

        RequestsViewModel(
            repository = requestsRepository
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

        NotificationsViewModel(
            repository = notificationsRepository
        )
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

    // Poll backend every 5 seconds
    // to keep notifications and requests updated
    LaunchedEffect(Unit) {
        while (isActive){
            notificationsViewModel.loadNotifications()
            requestsViewModel.loadRequests()
            delay(5000)
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
                postType = PostType.LOST,

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
                postType = PostType.FOUND,

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

            if(selectedPost == null) {

                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }

            } else {

                ItemDetailScreen(
                    itemPost = selectedPost!!,

                    postsViewModel = postsViewModel,

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

                        NotificationType.REQUEST_APPROVED,
                        NotificationType.MATCH_FOUND -> {

                            notification.postId?.let { itemId ->

                                val post =
                                    postsViewModel.findPostById(itemId)

                                if(post != null) {

                                    selectedPost = post

                                    navController.navigate(
                                        "item_detail/$itemId"
                                    )
                                }
                            }
                        }

                        NotificationType.REQUEST_REJECTED,
                        NotificationType.SYSTEM -> Unit
                    }
                }
            )
        }

        composable(
            Routes.RequestDetail.route
        ) {

            if(selectedRequest == null) {

                LaunchedEffect(Unit) {
                    navController.popBackStack()
                }

            } else {

                RequestDetailScreen(
                    request = selectedRequest!!,

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

                    userViewModel.clearUpdateSuccess()

                    navController.popBackStack()
                },

                onChangePhotoClick = {

                }
            )
        }

        composable(
            Routes.UserPosts.route
        ) {

            LaunchedEffect(Unit) {
                postsViewModel.loadMyPosts()
            }

            PostsScreen(
                title = "Mis publicaciones",
                posts = postsViewModel.myPosts,
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

            LaunchedEffect(Unit) {
                postsViewModel.loadBookmarks()
            }

            PostsScreen(
                title = "Guardadas",
                posts = postsViewModel.bookmarkedPosts,
                screenType = PostsScreenType.SAVED_POSTS,

                onBackClick = {
                    navController.popBackStack()
                },

                onItemClick = ::navigateToItemDetail,
            )
        }
    }
}