package com.example.firstworktask.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstworktask.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

   // @Inject
   // lateinit var viewModelFactory: ViewModelFactory

    val viewModel : FixturesViewModel by viewModels()

   // private lateinit var viewModel: FixturesViewModel
    private var adapter = DiffUtilAdapterMain()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   viewModel = viewModelFactory.create(FixturesViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater,container,false)
        adapter = DiffUtilAdapterMain()
        binding.MatchesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.MatchesRecyclerView.adapter = adapter
        binding.MatchesRecyclerView.isClickable = true
        binding.MatchesRecyclerView.alpha = 0F

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { calendarView, i, i2, i3 ->
                println(calendarView.dateTextAppearance.toString())
                var month: String = ""
                if(i2 != 12 && i2 != 11 && i2 != 10){
                    month = "0" + i2.toString()
                }
                else {
                    month = i2.toString()
                }
                val date = "$i-$month-$i3"
                println(date)
                binding.MatchesRecyclerView.alpha = 1F
                binding.calendarView.alpha = 0F
                viewModel.getFixturesByDate(date)
            }
        )

        viewModel.fixtureRequiredFields.observe(viewLifecycleOwner, Observer { e->
            adapter.submitList(e)
        })
        super.onViewCreated(view, savedInstanceState)
    }



}