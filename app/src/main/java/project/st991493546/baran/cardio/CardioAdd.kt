package project.st991493546.baran.cardio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.st991493546.baran.MainActivity
import project.st991493546.baran.R


class CardioAdd : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.title = "Add Cardio"

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cardio_add, container, false)
    }
}