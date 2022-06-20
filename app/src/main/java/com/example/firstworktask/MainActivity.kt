package com.example.firstworktask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstworktask.dagger.ViewModelFactory
import com.example.firstworktask.databinding.ActivityMainBinding
import com.example.firstworktask.main.DiffUtilAdapterMain
import com.example.firstworktask.main.MainViewModel
import com.example.firstworktask.main.models.FixtureModelPackage.Response
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    //lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel : MainViewModel = viewModelFactory.create(MainViewModel::class.java)
        val adapter = DiffUtilAdapterMain()

        binding.MatchesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MatchesRecyclerView.adapter = adapter
        binding.MatchesRecyclerView.isClickable = true

        binding.MatchesRecyclerView.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )

        viewModel.fixtureRequiredFields.observe(this, Observer { e->
            adapter.submitList(e)
        })
    }
}