package com.example.firstworktask.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstworktask.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * This fragment has a CalendarView and a RecyclerView.
 * First, the RecyclerView is hidden, until the user chooses a date from the CalendarView.
 * When the user chooses a date, the CalendarView is hidden, and the Recycler is populated.
 */
@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    val viewModel : FixturesViewModel by viewModels()

    private var adapter = DiffUtilAdapterMain()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCalendarBinding.inflate(inflater,container,false)
        adapter = DiffUtilAdapterMain()
        binding.MatchesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.MatchesRecyclerView.adapter = adapter
        binding.MatchesRecyclerView.isClickable = true
        binding.MatchesRecyclerView.alpha = 0F

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        binding.calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { _, i, i2, i3 ->
                var month: String = ""
                month = if(i2 != 12 && i2 != 11 && i2 != 10){
                    "0$i2"
                } else {
                    i2.toString()
                }
                val date = "$i-$month-$i3"
                println(date)
                binding.MatchesRecyclerView.alpha = 1F
                binding.calendarView.alpha = 0F
                viewModel.getFixturesByDate(date)
            }
        )

        lifecycleScope.launchWhenStarted {
            viewModel.fixtureRequiredFields.collectLatest {
                adapter.submitList(it)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }



}