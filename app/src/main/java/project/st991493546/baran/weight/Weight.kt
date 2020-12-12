package project.st991493546.baran.weight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
        val weightViewModel = ViewModelProvider(this, weightViewModelFactory).get(WeightViewModel::class.java)

//        binding.setLifecycleOwner(this)
//        binding.cardioViewModel = cardioViewModel

//        recycler_view.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = CardioViewAdapter(list)
//        }
        val adapter = WeightView()
        val recyclerview = view.recyclerviewweight
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        weightViewModel.readAllData.observe(viewLifecycleOwner, Observer { weight ->
            adapter.setData(weight)
        })


        view.btnAdd.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_weight_to_weightAdd)
        }

        view.btnWeightDelete.setOnClickListener{
            var id = view.txtWeightID.text.toString().toLong()
            weightViewModel.deleteById(id)
            view.txtWeightID.setText("")
        }



        return view
    }


}