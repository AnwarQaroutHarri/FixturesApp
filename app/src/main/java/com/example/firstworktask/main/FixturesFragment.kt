package com.example.firstworktask.main

import android.os.Bundle
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

/**
 * This fragment observes the StateFlow that contains fixtures
 * Populates a DiffUtilAdapter with fixtures and binds it to a RecyclerView.
 */
private var ARG_DATE = "date"

@AndroidEntryPoint
class FixturesFragment : Fragment() {


    private lateinit var binding: FragmentFirstDateBinding
    private val viewModel : FixturesViewModel by viewModels()

    private var adapter = DiffUtilAdapterMain()

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        ARG_DATE = arguments?.getSerializable("date").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFirstDateBinding.inflate(inflater, container, false)
        binding.MatchesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.MatchesRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(
        view: View, savedInstanceState: Bundle?
    ) {

        viewModel.getFixturesByDate(ARG_DATE)
        lifecycleScope.launchWhenStarted {
            viewModel.fixtureRequiredFields.collectLatest {
                adapter.submitList(it)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(date: String): FixturesFragment {
            val args = Bundle()
            args.putString("date", date)
            val fragment = FixturesFragment()
            fragment.arguments = args
            return fragment
        }
    }

}