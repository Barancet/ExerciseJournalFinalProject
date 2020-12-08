package project.st991493546.baran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import project.st991493546.baran.databinding.FragmentTitleBinding

class Title : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.title = "Exercise Journal"

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater,
            R.layout.fragment_title, container, false)

        binding.btnOpenCardio.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_title_to_cardio)
        }

        binding.btnOpenWeights.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_title_to_weight)
        }

        return binding.root
    }
}