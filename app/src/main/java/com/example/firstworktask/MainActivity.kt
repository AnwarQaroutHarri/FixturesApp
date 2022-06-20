package com.example.firstworktask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstworktask.main.DiffUtilAdapterMain
import com.example.firstworktask.main.MainViewModel
import com.example.firstworktask.main.models.FixtureModelPackage.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView= findViewById(R.id.MatchesRecyclerView)


        println("CURRENTLY IN ACTIVITY LMFAO -SAD-ASD-----")
        val viewModel = MainViewModel()
        val adapter = DiffUtilAdapterMain()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.isClickable = true

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
        viewModel.fixtureRequiredFields.observe(this, Observer { e->
            adapter.submitList(e)
        })


        /*

        recyclerView.isClickable = true

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )

         */

        /*
        GlobalScope.launch {
            /*val jobs = listOf(
                launch{ viewModel.getFixtures() }
            )
            jobs.joinAll()

             */


          withContext(Dispatchers.Main){
                delay(3000)
                val result = viewModel.fixtureRequiredFields.value
                val adapter = RecyclerViewAdapterMain(result)
                recyclerView.adapter = adapter
           }
        }

         */









        /*
        recyclerView= findViewById(R.id.MatchesRecyclerView)
       val adapter = RecyclerViewAdapterMain(216)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

         */



    }
}