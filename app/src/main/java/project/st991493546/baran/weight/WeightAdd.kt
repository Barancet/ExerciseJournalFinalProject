package project.st991493546.baran.weight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.database.ApplicationDatabase
import project.st991493546.baran.databinding.FragmentWeightAddBinding

class WeightAdd : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.title = "Add Weight"

        val binding = DataBindingUtil.inflate<FragmentWeightAddBinding>(
            inflater,
            R.layout.fragment_weight_add, container, false
        )
        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).weightDao()
        val weightViewModelFactory = WeightViewModelFactory(dataSource, application)
        val weightViewModel =
            ViewModelProvider(this, weightViewModelFactory).get(WeightViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.weightViewModel = weightViewModel

        binding.btnInsertWeight.setOnClickListener {view:View ->

            if(binding.editTextName.text.toString() != "" && binding.editTextDate.text.toString() != "" &&
                binding.editTextReps.text.toString() != "" && binding.editTextSets.text.toString() != ""){
                var name = binding.editTextName.text.toString()
                var date = binding.editTextDate.text.toString()
                var reps = binding.editTextReps.text.toString().toInt()
                var sets = binding.editTextSets.text.toString().toInt()
                weightViewModel.insertIntoDB(name, date, reps, sets)
                Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_SHORT).show()
                //view.findNavController().navigate(R.id.action_weightAdd_to_weight)
            }
            else{
                Toast.makeText(requireContext(), "Please enter all values", Toast.LENGTH_SHORT).show()
            }

        }
        // Inflate the layout for this fragment
        return binding.root
    }

}