package com.group.to_doornotto_do.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.group.to_doornotto_do.R
import com.group.to_doornotto_do.screens.home.HomeScreen
import com.group.to_doornotto_do.screens.todolist.ToDoListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.Home
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    onOpenList = { id ->
                        navController.navigate(
                            "${Screen.ListScreen.route}?id=${id}"
                        )
                    }
                )
            }

            composable(
                route = "${Screen.ListScreen.route}?id={id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType }),
            ) {
                ToDoListScreen(id = it.arguments?.getInt("id") ?: -1)
            }
        }
    }
}
