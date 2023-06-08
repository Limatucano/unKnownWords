package com.correa.unknownword.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.correa.unknownword.ui.setQuantity.SetQuantityScreen
import com.correa.unknownword.ui.setQuantity.SetQuantityViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destination.SetQuantity.route,
    ) {
        composable(
            route = Destination.SetQuantity.route
        ) {
            val viewModel: SetQuantityViewModel = hiltViewModel()
            SetQuantityScreen(
                viewModel = viewModel,
                onConfirmClicked = {

                }
            )
        }
    }
}