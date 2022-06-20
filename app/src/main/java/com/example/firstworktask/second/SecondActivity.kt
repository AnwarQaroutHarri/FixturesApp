package com.example.firstworktask.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.firstworktask.R
import java.lang.StringBuilder

class SecondActivity : AppCompatActivity() {
    lateinit var listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        listView = findViewById(R.id.listView)

        val id = intent.getIntExtra("id",0)
        println("----ID IS $id")
        Log.d("idShit","ID IS $id")
        val viewModel = SecondViewModel()
        viewModel.getFixtureDetailsByID(id)
        val strList : MutableList<String> = mutableListOf()
        viewModel.fixtureDetails.observe(this, Observer { e ->
            listView.adapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,e)

            /*
            val str = StringBuilder()
            val response = e.response[0].events
            for(event in response){
                println(event.time.elapsed.toString() + "-----1")
                str.append(event.time.elapsed.toString() + ": ")
                println(str.toString() + "-----2")
                if(event.type.equals("Card")) {
                    str.append(event.detail.toString())
                    str.append(" - ${event.player.name.toString()} (${event.team.name})")
                }
                else if(event.type.equals("Goal")) {
                    if(!event.assist.name.isNullOrEmpty())  {
                    str.append("Goal ${event.player.name} assist: ${event.assist.name} (${event.team.name.toString()}")
                    }
                    else {
                        str.append("Goal ${event.player.name} (${event.team.name.toString()})")
                    }
                }
                else if(event.type.equals("subst")) {
                    str.append("Substitution (${event.team.name.toString()}): ${event.assist.name} -> ${event.player.name}")
                }
                //str.append("\n")
                strList.add(str.toString())
                str.clear()
            }

             */
           // println("STRING IS $str --------=====")
         //   listView.adapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,strList)
        })


    }
}