package project.st991493546.baran.cardio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_cardio_edit.*
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.database.CardioEntity
import project.st991493546.baran.databinding.FragmentCardioEditBinding

class CardioEdit : Fragment() {

    private val args by navArgs<CardioEditArgs>()
    private lateinit var cardioViewModel: CardioViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Edit Cardio"
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCardioEditBinding>(
            inflater, R.layout.fragment_cardio_edit, container, false
        )

        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).cardioDao()
        val cardioViewModelFactory = CardioViewModelFactory(dataSource, application)

        cardioViewModel =
            ViewModelProvider(this, cardioViewModelFactory).get(CardioViewModel::class.java)

        binding.editTextName.setText(args.currentCardio.cardioName)
        binding.editTextDate.setText(args.currentCardio.date)
        binding.editTextDistance.setText(args.currentCardio.distance.toString())
        binding.editTextDuration.setText(args.currentCardio.duration.toString())

        binding.btnUpdateCardio.setOnClickListener {view:View ->
            updateCardio()
            //view.findNavController().navigate(R.id.action_cardioEdit_to_cardio)
        }
        binding.setLifecycleOwner(this)
        binding.cardioViewModel = cardioViewModel
        return binding.root
    }

    private fun updateCardio() {

        if(editTextName.text.toString() != "" && editTextDate.text.toString() != "" &&
            editTextDistance.text.toString() != "" && editTextDuration.text.toString() != ""){
            val name = editTextName.text.toString()
            val date = editTextDate.text.toString()
            val distance = editTextDistance.text.toString().toInt()
            val duration = editTextDuration.text.toString().toInt()
            val cardioUpdated = CardioEntity(args.currentCardio.id, date, name, duration, distance)
            cardioViewModel.updateCardio(cardioUpdated)
            Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(requireContext(), "Please update all the fields", Toast.LENGTH_SHORT).show()
        }


    }
}