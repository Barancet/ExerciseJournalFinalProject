package project.st991493546.baran.cardio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cardio.view.*
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R
import project.st991493546.baran.database.ApplicationDatabase

class Cardio : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).supportActionBar?.title = "Cardio Journal"

        val view = inflater.inflate(R.layout.fragment_cardio, container, false)
        val application = requireNotNull(activity).application
        val dataSource = ApplicationDatabase.getInstance(application).cardioDao()
        val cardioViewModelFactory = CardioViewModelFactory(dataSource, application)
        val cardioViewModel =
            ViewModelProvider(this, cardioViewModelFactory).get(CardioViewModel::class.java)

        val adapter = CardioViewAdapter()
        val recyclerview = view.recyclerView

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())
        cardioViewModel.readAllData.observe(viewLifecycleOwner, Observer { cardio ->
            adapter.setData(cardio)
        })

        view.btnAddCardio.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_cardio_to_cardioAdd)
        }

        view.dltBtn.setOnClickListener {
            if(view.txtID.text.toString().trim() != ""){
                var id = view.txtID.text.toString().toLong()
                cardioViewModel.deleteById(id)
                view.txtID.setText("")
                Toast.makeText(requireContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Please enter correct Integer ID", Toast.LENGTH_SHORT).show()
            }

        }
        return view
    }
}