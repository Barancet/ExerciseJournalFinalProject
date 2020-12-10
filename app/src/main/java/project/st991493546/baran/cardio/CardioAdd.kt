package project.st991493546.baran.cardio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_cardio_add.view.*
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.databinding.FragmentCardioAddBinding
import project.st991493546.baran.databinding.FragmentCardioBinding


class CardioAdd : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Add Cardio"
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCardioAddBinding>(inflater,
            R.layout.fragment_cardio_add, container, false)

        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).cardioDao()

        val cardioViewModelFactory = CardioViewModelFactory(dataSource, application)
        val cardioViewModel = ViewModelProvider(this, cardioViewModelFactory).get(CardioViewModel::class.java)



        binding.setLifecycleOwner(this)
        binding.cardioViewModel = cardioViewModel


        binding.btnInsertCardio.setOnClickListener{
            var name = binding.editTextName.text.toString()
            var date = binding.editTextDate.text.toString()
            var distance = binding.editTextDistance.text.toString().toInt()
            var duration = binding.editTextDuration.text.toString().toInt()
            cardioViewModel.insertIntoDB(name,date,duration,distance)


        }

        return binding.root
    }


}