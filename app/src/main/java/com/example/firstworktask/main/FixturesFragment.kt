package com.example.firstworktask.main

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstworktask.databinding.FragmentFirstDateBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * This fragment observes the StateFlow that contains fixtures
 * Populates a DiffUtilAdapter with fixtures and binds it to a RecyclerView.
 */
private const val ARG_DATE = "date"

@AndroidEntryPoint
class FixturesFragment : Fragment() {

    lateinit var date: LocalDate


    private lateinit var binding: FragmentFirstDateBinding
    private val viewModel : FixturesViewModel by viewModels()

    private var adapter = FixturesAdapter()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        date = LocalDate.parse(arguments?.getString(ARG_DATE),DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstDateBinding.inflate(inflater, container, false)
        binding.MatchesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.MatchesRecyclerView.adapter = adapter

        binding.reloadBtn.setOnClickListener {
            viewModel.getFixturesByDate(date)
        }
        return binding.root
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {

        viewModel.getFixturesByDate(date)

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

    companion object {
        fun newInstance(date: String): FixturesFragment {
            val args = Bundle()
            args.putString(ARG_DATE, date)
            val fragment = FixturesFragment()
            fragment.arguments = args
            Log.d("details","created argument with date $date")
            return fragment
        }
    }

}