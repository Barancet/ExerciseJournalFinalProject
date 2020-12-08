package project.st991493546.baran.weight

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R

class WeightAdd : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.title = "Add Weight"

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weight_add, container, false)
    }

}