package com.snuzj.cupcakeapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.snuzj.cupcakeapp.databinding.FragmentStartBinding
import com.snuzj.cupcakeapp.model.OrderViewModel



class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var binding: FragmentStartBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        val numberEditText = fragmentBinding.numberEditText // Use fragmentBinding to access the EditText
        fragmentBinding.order.setOnClickListener {
            val inputText = numberEditText.text.toString()
            val numberCupcakes = inputText.toIntOrNull()

            if (numberCupcakes != null && numberCupcakes <= 100) {
                // Valid number was entered
                orderCupcake(numberCupcakes)
            } else {
                // Invalid input, handle the error
                Toast.makeText(
                    requireContext(),
                    "Số lượng giới hạn: 1 đến 100",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding = fragmentBinding
        return fragmentBinding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    private fun orderCupcake(quantity: Int){
        sharedViewModel.setQuantity(quantity)
        if(sharedViewModel.hasNoFlavorSet()){
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment) // Navigate to the next screen
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}