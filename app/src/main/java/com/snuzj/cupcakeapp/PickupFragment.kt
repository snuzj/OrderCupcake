package com.snuzj.cupcakeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.snuzj.cupcakeapp.databinding.FragmentPickupBinding
import com.snuzj.cupcakeapp.model.OrderViewModel


class PickupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var binding: FragmentPickupBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            pickupFragment = this@PickupFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentPickupBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    fun goToNextScreen(){
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }

    fun cancelOrder(){
        sharedViewModel.resetFlavor()
        findNavController().navigate(R.id.action_pickupFragment_to_flavorFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}