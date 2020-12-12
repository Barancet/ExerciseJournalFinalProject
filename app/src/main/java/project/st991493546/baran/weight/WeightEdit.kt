package project.st991493546.baran.weight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_cardio_edit.*
import kotlinx.android.synthetic.main.fragment_cardio_edit.editTextDate
import kotlinx.android.synthetic.main.fragment_weight_edit.*
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.cardio.CardioEditArgs
import project.st991493546.baran.cardio.CardioViewModel
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.database.CardioEntity
import project.st991493546.baran.database.WeightEntity
import project.st991493546.baran.databinding.FragmentCardioEditBinding
import project.st991493546.baran.databinding.FragmentWeightEditBinding


class WeightEdit : Fragment() {

    private val args by navArgs<WeightEditArgs>()
    private lateinit var weightViewModel : WeightViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.title = "Edit Weight"

        val binding = DataBindingUtil.inflate<FragmentWeightEditBinding>(
            inflater, R.layout.fragment_weight_edit, container, false
        )


        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).weightDao()

        val weightViewModelFactory = WeightViewModelFactory(dataSource, application)
        weightViewModel = ViewModelProvider(this, weightViewModelFactory).get(WeightViewModel::class.java)


        binding.editTextType.setText(args.currentWeight.weightType)
        binding.editTextDateWeight.setText(args.currentWeight.date)
        binding.editTextResp.setText(args.currentWeight.reps.toString())
        binding.editTextSets.setText(args.currentWeight.sets.toString())

        binding.btnUpdateWeight.setOnClickListener{
            updateWeight()
        }

        binding.setLifecycleOwner(this)
        binding.weightViewModel = weightViewModel
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun updateWeight(){
        val type = editTextType.text.toString()
        val date = editTextDateWeight.text.toString()
        val reps = editTextResp.text.toString().toInt()
        val sets = editTextSets.text.toString().toInt()

        val weightUpdated = WeightEntity(args.currentWeight.id,date,type,reps,sets)
        weightViewModel.updateWeight(weightUpdated)
        Toast.makeText(requireContext(), "Updated Successfully", Toast.LENGTH_SHORT).show()
    }
}