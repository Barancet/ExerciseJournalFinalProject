package project.st991493546.baran.weight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cardio.view.*
import kotlinx.android.synthetic.main.fragment_weight.view.*
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.database.ApplicationDatabase

class Weight : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Weight Journal"
        val view = inflater.inflate(R.layout.fragment_weight, container, false)

        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).weightDao()
        val weightViewModelFactory = WeightViewModelFactory(dataSource, application)
        val weightViewModel =
            ViewModelProvider(this, weightViewModelFactory).get(WeightViewModel::class.java)

        //recyclerviewweight was done by Alkesh with Ebrahim's contribution
        val adapter = WeightView()
        val recyclerview = view.recyclerviewweight
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        weightViewModel.readAllData.observe(viewLifecycleOwner, Observer { weight ->
            adapter.setData(weight)
        })

        // btnAddWeight done by Baran
        view.btnAdd.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_weight_to_weightAdd)
        }

        // btnWeightDelete done by Alkesh
        view.btnWeightDelete.setOnClickListener {
            if(view.txtWeightID.text.toString().trim() != ""){
                var id = view.txtWeightID.text.toString().toLong()
                weightViewModel.deleteById(id)
                view.txtWeightID.setText("")
                Toast.makeText(requireContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Please enter correct Integer ID", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}