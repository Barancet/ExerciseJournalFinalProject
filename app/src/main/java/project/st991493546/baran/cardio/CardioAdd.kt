package project.st991493546.baran.cardio
// This class was done all by baran
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
import project.st991493546.baran.databinding.FragmentCardioAddBinding

class CardioAdd : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Add Cardio"
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentCardioAddBinding>(
            inflater,
            R.layout.fragment_cardio_add, container, false
        )

        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).cardioDao()
        val cardioViewModelFactory = CardioViewModelFactory(dataSource, application)
        val cardioViewModel =
            ViewModelProvider(this, cardioViewModelFactory).get(CardioViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.cardioViewModel = cardioViewModel
        binding.btnInsertCardio.setOnClickListener {view: View ->

            if(binding.editTextName.text.toString() != "" && binding.editTextDate.text.toString() != "" &&
                binding.editTextDistance.text.toString() != "" && binding.editTextDuration.text.toString() != ""){
                var name = binding.editTextName.text.toString()
                var date = binding.editTextDate.text.toString()
                var distance = binding.editTextDistance.text.toString().toInt()
                var duration = binding.editTextDuration.text.toString().toInt()
                cardioViewModel.insertIntoDB(name, date, duration, distance)
                Toast.makeText(requireContext(), "Added Successfully", Toast.LENGTH_SHORT).show()
                //view.findNavController().navigate(R.id.action_cardioAdd_to_cardio)
            }
            else{
                Toast.makeText(requireContext(), "Please enter all values", Toast.LENGTH_SHORT).show()
            }

        }
        return binding.root
    }
}