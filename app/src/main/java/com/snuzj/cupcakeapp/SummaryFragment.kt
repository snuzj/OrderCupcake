@file:Suppress("DEPRECATION")

package com.snuzj.cupcakeapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.snuzj.cupcakeapp.databinding.FragmentSummaryBinding
import com.snuzj.cupcakeapp.model.OrderViewModel

class SummaryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var binding:FragmentSummaryBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    fun sendOrder(){
        // Construct the order summary text with information from the view model
        val numberOfCuppakes = sharedViewModel.quantity.value?: 0
        val orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes,numberOfCuppakes,numberOfCuppakes),
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.value.toString(),
            sharedViewModel.price.value.toString()
        )
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT,getString(R.string.new_cupcake_order))
            .putExtra(Intent.EXTRA_TEXT,orderSummary)
        if (activity?.packageManager?.resolveActivity(intent, 0) != null){
            startActivity(intent)
        }
    }

    fun cancelOrder(){
        sharedViewModel.resetPickup()
        findNavController().navigate(R.id.action_summaryFragment_to_pickupFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}