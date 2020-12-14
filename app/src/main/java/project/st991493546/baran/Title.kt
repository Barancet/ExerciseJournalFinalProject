package project.st991493546.baran
//Done by Everyone
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import project.st991493546.baran.databinding.FragmentTitleBinding

class Title : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as MainActivity).supportActionBar?.title = "Exercise Journal"

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(
            inflater,
            R.layout.fragment_title, container, false
        )

        binding.btnOpenCardio.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_title_to_cardio)
        }

        binding.btnOpenWeights.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_title_to_weight)
        }
        binding.abt.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_title_to_about)
        }
        return binding.root
    }


}