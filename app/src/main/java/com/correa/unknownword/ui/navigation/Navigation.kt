package com.correa.unknownword.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.correa.unknownword.ui.game.GameScreen
import com.correa.unknownword.ui.game.GameViewModel
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
                onConfirmClicked = { quantity ->
                    navController.navigate(
                        "${Destination.Game.route}?quantity=$quantity"
                    )
                }
            )
        }
        composable(
            route = "${Destination.Game.route}?quantity={quantity}",
            arguments = listOf(
                navArgument("quantity") { type = NavType.IntType }
            )
        ) { backStack ->
           val viewModel: GameViewModel = hiltViewModel()
           GameScreen(
               viewModel = viewModel,
               quantity = backStack.arguments?.getInt("quantity") ?: 1,
               onFinishClicked = {
                   navController.navigate(Destination.SetQuantity.route)
               }
           )
        }
    }
}