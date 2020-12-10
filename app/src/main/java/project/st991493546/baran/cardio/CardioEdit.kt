package project.st991493546.baran.cardio

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.database.CardioEntity
import project.st991493546.baran.databinding.FragmentCardioAddBinding
import project.st991493546.baran.databinding.FragmentCardioEditBinding


class CardioEdit : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Add Cardio"
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCardioEditBinding>(inflater,
            R.layout.fragment_cardio_edit, container, false)

        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).cardioDao()

        val cardioViewModelFactory = CardioViewModelFactory(dataSource, application)
        val cardioViewModel = ViewModelProvider(this, cardioViewModelFactory).get(CardioViewModel::class.java)

        var id = cardioViewModel.getIDforUpdate()
        var oneCardio : CardioEntity? = cardioViewModel.OneList()

        Log.i("test edit", "$oneCardio")

        binding.editTextName.setText(oneCardio?.cardioName)
        binding.editTextDate.setText(oneCardio?.date)
        binding.editTextDistance.setText(oneCardio?.distance.toString())
        binding.editTextDuration.setText(oneCardio?.duration.toString())



        return binding.root
    }
}