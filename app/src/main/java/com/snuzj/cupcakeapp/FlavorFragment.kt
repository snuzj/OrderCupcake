package com.snuzj.cupcakeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.snuzj.cupcakeapp.databinding.FragmentFlavorBinding
import com.snuzj.cupcakeapp.model.OrderViewModel

class FlavorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var binding: FragmentFlavorBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            //Specify the fragment as the lifecycle ownder
            lifecycleOwner = viewLifecycleOwner
            //Assign the view model to a property in this binding class
            viewModel = sharedViewModel
            //Assign the fragment
            flavorFragment = this@FlavorFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    fun goToNextScreen(){
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
        }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
        }


}