package project.st991493546.baran.weight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cardio.*
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R

import project.st991493546.baran.database.WeightListItems
import project.st991493546.baran.databinding.FragmentCardioBinding
import project.st991493546.baran.databinding.FragmentWeightBinding

class Weight : Fragment() {

    private val weightModel by lazy {
        ViewModelProvider(this).get(WeightViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.title = "Weight Journal"

        val binding = DataBindingUtil.inflate<FragmentWeightBinding>(inflater,
            R.layout.fragment_weight, container, false)

        binding.btnAddWeight.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_weight_to_weightAdd)
        }
        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_cardio, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        //val ourList = generateList(1)
//        recycler_view.apply {
//            // set a LinearLayoutManager to handle Android
//            // RecyclerView behavior
//            layoutManager = LinearLayoutManager(activity)
//            // set the custom adapter to the RecyclerView
//            adapter = WeightView(weightModel.getOurList())
    }
}