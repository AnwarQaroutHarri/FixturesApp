package com.example.firstworktask.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstworktask.databinding.FragmentFirstDateBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private var ARG_DATE = "date"

@AndroidEntryPoint
class FixturesFragment() : Fragment() {


    private lateinit var binding: FragmentFirstDateBinding

   // @Inject
   // lateinit var viewModelFactory: ViewModelFactory
   val viewModel : FixturesViewModel by viewModels()

   // private lateinit var viewModel: FixturesViewModel
    private var adapter = DiffUtilAdapterMain()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // viewModel = viewModelFactory.create(FixturesViewModel::class.java)
        ARG_DATE = arguments?.getSerializable("date").toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstDateBinding.inflate(inflater, container, false)
        adapter = DiffUtilAdapterMain()
        binding.MatchesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.MatchesRecyclerView.adapter = adapter
        binding.MatchesRecyclerView.isClickable = true



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getFixturesByDate(ARG_DATE)
        viewModel.fixtureRequiredFields.observe(viewLifecycleOwner, Observer { e ->
            adapter.submitList(e)
        })
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