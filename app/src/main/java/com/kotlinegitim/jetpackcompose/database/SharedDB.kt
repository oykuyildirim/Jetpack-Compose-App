package com.kotlinegitim.jetpackcompose.database

import android.content.Context
import com.kotlinegitim.jetpackcompose.screen.languages

class SharedDB {

    fun addToList(context: Context, text:String){

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


    fun SaveListCountPreferences(context: Context){


        val prefences = context.getSharedPreferences("countList", Context.MODE_PRIVATE)
        val editor = prefences.edit()
        println(languages.count() )
        editor.putInt("countList", languages.count() )
        editor.apply()
    }

    fun getListCountPre(context:Context) : Int{

        val prefences = context.getSharedPreferences("countList", Context.MODE_PRIVATE)

        var count2 = prefences.getInt("countList", languages.count())

        return count2
    }




}