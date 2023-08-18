package com.kotlinegitim.jetpackcompose.screen

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi


import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import com.kotlinegitim.jetpackcompose.MainActivity
import com.kotlinegitim.jetpackcompose.database.SharedDB
import androidx.compose.runtime.remember as remember

val colors = mutableListOf<Color>(Color.Gray, Color.Cyan,Color.Magenta, Color.Blue, Color.Red)

var clicked = false


var languages = mutableListOf<String>()



@Composable
fun SettingScreen() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {

        displayList()

        SimpleFilledTextFieldSample({ displayList() })
    }



}

@OptIn(ExperimentalUnitApi::class, ExperimentalTextApi::class)
@Composable
fun displayList() {


    val context  = LocalContext.current
    val prefences2= context.getSharedPreferences("listItems", Context.MODE_PRIVATE)
    languages = prefences2.all.map { it.key } as MutableList<String>

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(10.dp),


            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Shopping List",

                modifier = Modifier.padding(10.dp),

                style = TextStyle(

                        brush = Brush.linearGradient(
                            colors = colors
                        ),
                    fontSize = TextUnit(value = 25.0F, type = TextUnitType.Sp)
                ), fontWeight = FontWeight.Black
            )

            LoadList()

        }





}

@Composable
fun LoadList(){
    LazyColumn {


        items(languages) { language ->

            Text(language, modifier = Modifier.padding(15.dp))

            Divider()
        }
    }
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun SimpleFilledTextFieldSample(listUpdated: @Composable () -> Unit) {

    val context  = LocalContext.current


    var text by remember { mutableStateOf("") }

    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.fillMaxWidth()
            .padding(10.dp)
    ) {
        OutlinedTextField(

            value = text,

            onValueChange = { text = it
            },
            leadingIcon = {
                Icon(imageVector =Icons.Filled.Add  , contentDescription ="input", tint = Color.Magenta  )
            },

            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Color.Red),


            textStyle  = TextStyle(
                brush = Brush.linearGradient(
                    colors = colors
                )
            ),




        )

        val clickState = remember { mutableStateOf(0) }

        Button(onClick = {


            //Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
            SharedDB().addToList(context,text)
            text =""
           
            MainActivity.clicked = true


        },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red))

        {
            Text(text = "Add",color = Color.White)
        }

    }


  
}


/*fun addToList(context: Context,text:String){

    saveItemPreferences(context,text)

    languages.add(text)

    SaveListCountPreferences(context)

}

fun saveItemPreferences(context: Context, text:String){

    val prefences2= context.getSharedPreferences("listItems", Context.MODE_PRIVATE)
    val editor2 = prefences2.edit()
    editor2.putString(text,text)
    editor2.apply()
}


fun SaveListCountPreferences(context:Context){


    val prefences = context.getSharedPreferences("countList", Context.MODE_PRIVATE)
    val editor = prefences.edit()
    println(languages.count() )
    editor.putInt("countList",languages.count() )
    editor.apply()
}
*/
