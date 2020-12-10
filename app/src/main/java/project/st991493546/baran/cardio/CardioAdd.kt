package project.st991493546.baran.cardio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_cardio_add.*
import androidx.databinding.DataBindingUtil
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.databinding.FragmentCardioAddBinding
import project.st991493546.baran.databinding.FragmentCardioBinding


class CardioAdd : Fragment() {

//    private val cardioViewModel by lazy {
//        ViewModelProvider(this).get(CardioViewModel::class.java)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentCardioAddBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_cardio_add, container, false
        )

        (activity as MainActivity).supportActionBar?.title = "Add Cardio"

        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).cardioDao()

        val cardioViewModelFactory = CardioViewModelFactory(dataSource, application)
        val cardioViewModel = ViewModelProvider(this, cardioViewModelFactory).get(CardioViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.cardioViewModel = cardioViewModel

        return binding.root

        // Inflate the layout for this fragment

    }


}