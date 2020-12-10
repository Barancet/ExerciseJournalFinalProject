package project.st991493546.baran.cardio

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.fragment_cardio.*
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.databinding.FragmentCardioBinding


class Cardio : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Cardio Journal"

        val binding = DataBindingUtil.inflate<FragmentCardioBinding>(inflater,
            R.layout.fragment_cardio, container, false)

        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).cardioDao()

        val cardioViewModelFactory = CardioViewModelFactory(dataSource, application)
        val cardioViewModel = ViewModelProvider(this, cardioViewModelFactory).get(CardioViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.cardioViewModel = cardioViewModel

        binding.btnAddCardio.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_cardio_to_cardioAdd)
        }

        return binding.root


    }


}

// Inflate the layout for this fragment
//return inflater.inflate(R.layout.fragment_cardio, container, false)


// Inflate the layout for this fragment
//return inflater.inflate(R.layout.fragment_cardio, container, false)


//    private val cardioViewModel by lazy {
//        ViewModelProvider(this).get(CardioViewModel::class.java)
//    }
//keep in case we need to read livedata in here for the list?????

//private lateinit var cardioViewModel: CardioViewModel





/*
       val application = requireNotNull(activity).application
       val dataSource = ApplicationDatabase.getInstance(application).cardioDao()

       val cardioViewModelFactory = CardioViewModelFactory(dataSource, application)
       val cardioViewModel = ViewModelProvider(this, cardioViewModelFactory).get(CardioViewModel::class.java)
       binding.setLifecycleOwner(this)
       binding.cardioViewModel = cardioViewModel

       binding.btnAddCardio.setOnClickListener { view: View ->
           view.findNavController().navigate(R.id.action_cardio_to_cardioAdd)
       }

       var list = cardioViewModel.getCardioFromDB()

       recycler_view.apply {
           layoutManager = LinearLayoutManager(this@Cardio.context)
           adapter = CardioViewAdapter(list)
       }
       */




//    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(itemView, savedInstanceState)
//        var list = cardioViewModel.getCardioFromDB()
//        recycler_view.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = CardioViewAdapter(list)
//        }
//    }
