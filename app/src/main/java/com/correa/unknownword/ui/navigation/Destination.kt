package com.correa.unknownword.ui.navigation

sealed class Destination(val route: String) {
    object SetQuantity: Destination(route = "setQuantity")
    object Game: Destination(route = "game")
}
