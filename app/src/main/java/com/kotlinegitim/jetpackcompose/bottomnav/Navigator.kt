package com.kotlinegitim.jetpackcompose.bottomnav

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kotlinegitim.jetpackcompose.MainActivity
import com.kotlinegitim.jetpackcompose.database.SharedDB


@Composable
fun BottomNav() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        Modifier.padding(it)
        BottomNavGraph(
            navController = navController
        )
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBar.Home,
        BottomBar.List,

    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp)
            .background(Color.Transparent)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }

}

@Composable
fun AddItem(
    screen: BottomBar,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        Brush.linearGradient(
            /*listOf(
                Color(0xFFC5E1A5),
                Color(0xFF80DEEA)
            )*/

            listOf(
                Color(0xFFEF3977),
                Color.Green
            )
        )



    val contentColor =
        if (selected) Color.White else Color.Black



    Box(
        modifier = Modifier
            .height(50.dp)
            .clip(RectangleShape)
            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })


    ) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            if (screen.route == "list" && selected) {

                val context  = LocalContext.current


                var count = SharedDB().getListCountPre(context)
                MainActivity.clicked = false


                BadgedBox(badge = { Badge (
                    modifier = Modifier.graphicsLayer {
                        translationY = (-1).dp.toPx()
                        translationX = (-4).dp.toPx()
                    }
                     ,backgroundColor= Color.Blue) { Text(count.toString(), fontSize= 10.sp, color = Color.White) } }
                ) {
                    Icon(

                        painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                        contentDescription = "icon",
                        tint = contentColor

                    )
                }


            }
            else{

                Icon(

                    painter = painterResource(id = if (selected) screen.icon_focused else screen.icon),
                    contentDescription = "icon",
                    tint = contentColor

                )
            }
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
                    color = contentColor
                )
            }
        }
    }
}




@Composable
@Preview
fun BottomNavPreview() {
    BottomNav()
}