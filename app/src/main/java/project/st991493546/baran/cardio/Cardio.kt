package project.st991493546.baran.cardio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import project.st991493546.baran.database.CardioListItems

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_cardio.*
import project.st991493546.baran.R
import project.st991493546.baran.databinding.FragmentCardioBinding
import project.st991493546.baran.databinding.FragmentTitleBinding


class Cardio : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<CardioView.MyViewHolder>? = null


    //keep in case we need to read livedata in here for the list?????
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun generateList(size : Int): List<CardioListItems> {
        val list = ArrayList<CardioListItems>()
        val item = CardioListItems("Running", "12/05/2020","10 mins", "2km")
        val item2 = CardioListItems("biking", "12/05/2020","20 mins", "5km")
        list += item
        list += item2

        return list
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentCardioBinding>(inflater,
            R.layout.fragment_cardio, container, false)

        binding.btnAddCardio.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_cardio_to_cardioAdd)
        }
        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_cardio, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val ourList = generateList(1)
        recycler_view.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = CardioView(ourList)
        }
    }


}