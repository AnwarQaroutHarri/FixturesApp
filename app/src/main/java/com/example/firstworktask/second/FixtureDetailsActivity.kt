package com.example.firstworktask.second

import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.firstworktask.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * Second Activity
 * This activity opens the fixture details (Cards, Goals, Assists, etc)
 */

@AndroidEntryPoint
class FixtureDetailsActivity : AppCompatActivity() {

  private lateinit var listView: ListView
  private val viewModel: FixtureDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        listView = findViewById(R.id.listView)

        val id = intent.getIntExtra("id",0) //get the ID of the selected fixture
        viewModel.getFixtureDetailsByID(id) //update the stateflow with fixture details from given id

        lifecycleScope.launchWhenStarted {
            viewModel.fixtureDetails.collectLatest {
                listView.adapter = ArrayAdapter(applicationContext, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,it)
            }
        }

        // calling the action bar
        var actionBar = getSupportActionBar()

        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            finish()
        }
        return true
    }
}