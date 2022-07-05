package com.example.firstworktask.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstworktask.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * This fragment has a CalendarView and a RecyclerView.
 * First, the RecyclerView is hidden, until the user chooses a date from the CalendarView.
 * When the user chooses a date, the CalendarView is hidden, and the Recycler is populated.
 */
@AndroidEntryPoint
class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding

    private val viewModel : FixturesViewModel by viewModels()

    private var adapter = FixturesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCalendarBinding.inflate(inflater,container,false)
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
        binding.calendarView.setOnDateChangeListener { _, i, i2, i3 ->
            val month: String = if (i2 != 12 && i2 != 11 && i2 != 10) {
                "0$i2"
            } else {
                i2.toString()
            }

            val day: String = if (i3.toString().length != 2){
                "0$i3"
            } else {
                i3.toString()
            }

            val dateStr = "$i-$month-$day"
            val date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

            binding.MatchesRecyclerView.alpha = 1F
            binding.calendarView.alpha = 0F
            viewModel.getFixturesByDate(date)

            /* Reload button to do api call again */
            binding.reloadBtn.setOnClickListener {
                viewModel.getFixturesByDate(date)
            }

        }

        /* Check for errors and hide/show UI elements accordingly */
        lifecycleScope.launchWhenStarted {
            viewModel.error.collectLatest {
                if(it.isNotEmpty()){
                    binding.MatchesRecyclerView.alpha = 0F
                    binding.errorMessage.text = it
                    binding.errorMessage.visibility = View.VISIBLE
                    binding.reloadBtn.visibility = View.VISIBLE
                }
                else {
                    binding.reloadBtn.visibility = View.GONE
                    binding.MatchesRecyclerView.alpha = 1F
                    binding.errorMessage.visibility = View.GONE
                    viewModel.fixtureRequiredFields.collect { item ->
                        adapter.submitList(item)
                    }
                }
            }
        }



        super.onViewCreated(view, savedInstanceState)
    }



}