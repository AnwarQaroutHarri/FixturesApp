package com.example.firstworktask.second

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.firstworktask.R
import com.example.firstworktask.dagger.ViewModelFactory
import com.example.firstworktask.databinding.ActivityMainBinding
import com.example.firstworktask.databinding.ActivitySecondBinding
import com.example.firstworktask.main.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import java.lang.StringBuilder
import javax.inject.Inject

/**
 * Second Activity
 * This activity opens the fixture details (Cards, Goals, Assists, etc)
 */
class SecondActivity : DaggerAppCompatActivity() {
  lateinit var listView: ListView
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
       /* val binding: ActivitySecondBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_second)

        */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
       listView = findViewById(R.id.listView)

        val id = intent.getIntExtra("id",0) //get the ID of the selected fixture
        val viewModel : SecondViewModel = viewModelFactory.create(SecondViewModel::class.java) //inject the viewmodel
        viewModel.getFixtureDetailsByID(id)
        viewModel.fixtureDetails.observe(this, Observer { e ->
            listView.adapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,e)
        })


    }
}