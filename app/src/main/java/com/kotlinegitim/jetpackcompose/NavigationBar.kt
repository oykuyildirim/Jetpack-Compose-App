package com.kotlinegitim.jetpackcompose


sealed class NavigationBar(var title:String, var icon:Int, var screen_route:String) {

    object Home : NavigationBar("Home", R.drawable.home, "home")
    object List : NavigationBar("List", R.drawable.list_icon, "list")
}