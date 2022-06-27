package com.example.firstworktask.second

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.firstworktask.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Second Activity
 * This activity opens the fixture details (Cards, Goals, Assists, etc)
 */

@AndroidEntryPoint
class FixtureDetailsActivity : AppCompatActivity() {
  lateinit var listView: ListView
    //@Inject
   // lateinit var viewModelFactory: ViewModelFactory
  private val viewModel: FixtureDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
       /* val binding: ActivitySecondBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_second)

        */
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
       listView = findViewById(R.id.listView)

        val id = intent.getIntExtra("id",0) //get the ID of the selected fixture
       // val viewModel : FixtureDetailsViewModel = viewModelFactory.create(FixtureDetailsViewModel::class.java) //inject the viewmodel
        viewModel.getFixtureDetailsByID(id)
        viewModel.fixtureDetails.observe(this, Observer { e ->
            listView.adapter = ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,e)
        })


    }
}