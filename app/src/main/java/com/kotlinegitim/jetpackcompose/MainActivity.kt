package com.kotlinegitim.jetpackcompose

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Black
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kotlinegitim.jetpackcompose.bottomnav.BottomNav
import com.kotlinegitim.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.coroutines.delay




class MainActivity : ComponentActivity() {
    companion object {
        var clicked = false
        var count = 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {



                    BottomNav()
                    /*var refreshing by remember { mutableStateOf(false) }
                    LaunchedEffect(refreshing) {
                        if (refreshing) {
                            delay(1000)
                            refreshing = false
                        }
                    }*/
                    /*SwipeRefresh(
                        state = rememberSwipeRefreshState(isRefreshing = refreshing),
                        onRefresh = { refreshing = true },
                    ) {

                    }*/
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun myBadgedBox(){


        BottomNavigation {
            BottomNavigationItem(
                icon = {
                    BadgedBox(badge = { Badge { Text("8") } }) {
                        Icon(
                            Icons.Filled.Home,
                            contentDescription = "Home"
                        )
                    }

                },
                selected = false,
                onClick = {})

            BottomNavigationItem(
                icon = {
                    BadgedBox(badge = { Badge { Text("4") } }) {
                        Icon(
                            Icons.Filled.Call,
                            contentDescription = "CALL"
                        )
                    }

                },
                selected = false,
                onClick = {})

            BottomNavigationItem(
                icon = {
                    BadgedBox(badge = { Badge { Text("8") } }) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = "Favorite"
                        )
                    }

                },
                selected = false,
                onClick = {})
        }

}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Home Screen",
            fontWeight = FontWeight.Bold,

            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}



@Preview(name = "Preview1", device = Devices.PIXEL, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        //Greeting("Android")
        //myBadgedBox()
        BottomNav()
    }
}