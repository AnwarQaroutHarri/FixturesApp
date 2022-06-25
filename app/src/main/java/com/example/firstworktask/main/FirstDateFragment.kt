package com.example.firstworktask.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstworktask.R
import com.example.firstworktask.SwipeViewActivity
import com.example.firstworktask.dagger.ViewModelFactory
import com.example.firstworktask.databinding.FragmentFirstDateBinding
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerFragment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstDateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
    class FirstDateFragment : DaggerFragment() {
    val current = LocalDateTime.now().minusDays(1)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formatted = current.format(formatter)

    private lateinit var binding: FragmentFirstDateBinding

    @Inject
     lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MainViewModel
     private var adapter = DiffUtilAdapterMain()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelFactory.create(MainViewModel::class.java)
        val tab : TabLayout? = activity?.findViewById(R.id.tabLayout)
        println("${tab?.selectedTabPosition} --das-das-d-asd-asd-as-d")

        if(tab?.selectedTabPosition == 0){

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstDateBinding.inflate(inflater,container,false)
        adapter = DiffUtilAdapterMain()
        binding.MatchesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.MatchesRecyclerView.adapter = adapter
        binding.MatchesRecyclerView.isClickable = true



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getFixturesByDate(formatted)
        viewModel.fixtureRequiredFields.observe(viewLifecycleOwner, Observer { e->
            adapter.submitList(e)
        })
        super.onViewCreated(view, savedInstanceState)
    }



}