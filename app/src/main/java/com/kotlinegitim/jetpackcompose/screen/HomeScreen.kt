package com.kotlinegitim.jetpackcompose.screen

import android.graphics.Paint
import android.graphics.Path
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import com.kotlinegitim.jetpackcompose.R
import com.kotlinegitim.jetpackcompose.bottomnav.BottomNav
import com.kotlinegitim.jetpackcompose.ui.theme.JetpackComposeTheme




@OptIn(ExperimentalTextApi::class)
@Composable

fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        TextWrite()
    }

}
@ExperimentalTextApi
@Composable
fun TextWrite(){

    val context  = LocalContext.current
   Column(
       verticalArrangement = Arrangement.spacedBy(5.dp),
       modifier = Modifier

           .background(Color.White)


    ) {
       Column(
           Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center
       ) {

           // Creating a canvas with various attributes
           Canvas(modifier = Modifier.size(300.dp)) {
               drawIntoCanvas {
                   val textPadding = 30.dp.toPx()
                   val arcHeight = 300.dp.toPx()
                   val arcWidth = 300.dp.toPx()

                   // Path for curved text
                   val path = Path().apply {
                       addArc(0f, textPadding, arcWidth, arcHeight, 0F, 360f)
                   }


                   it.nativeCanvas.drawTextOnPath(
                       context.getResources().getString(R.string.sentence),
                       path,
                       0f,
                       0f,
                       Paint().apply {
                           textSize = 25.sp.toPx()
                           textAlign = Paint.Align.CENTER
                           strokeWidth = 5f
                           color = Color.Green.toArgb()

                       }
                   )
               }
           }

           // Creating a boolean value for
           // storing expanded state
           var showMore by remember { mutableStateOf(false) }

           // Creating a long text
           val text =context.getResources().getString(R.string.longsentence)


           Column(modifier = Modifier.padding(20.dp)) {

               // Creating a clickable modifier
               // that consists text
               Column(modifier = Modifier
                   .animateContentSize(animationSpec = tween(100))
                   .clickable(
                       interactionSource = remember { MutableInteractionSource() },
                       indication = null
                   ) { showMore = !showMore }) {

                   if (showMore) {
                       Text(text = text)
                   } else {
                       Text(text = text, maxLines = 3, overflow = TextOverflow.Ellipsis)
                   }
               }
           }
       }


   }








}

