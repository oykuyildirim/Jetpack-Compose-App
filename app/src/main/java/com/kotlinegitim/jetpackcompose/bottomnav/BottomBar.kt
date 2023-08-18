package com.kotlinegitim.jetpackcompose.bottomnav

import com.kotlinegitim.jetpackcompose.R



sealed class BottomBar(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {


    object Home: BottomBar(
        route = "home",
        title = "Home",
        icon = R.drawable.home,
        icon_focused = R.drawable.home
    )


    object List: BottomBar(
        route = "list",
        title = "List",
        icon = R.drawable.list_icon,
        icon_focused = R.drawable.list_icon
    )


}