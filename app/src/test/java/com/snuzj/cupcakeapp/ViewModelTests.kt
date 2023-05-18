package com.snuzj.cupcakeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.snuzj.cupcakeapp.model.OrderViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class ViewModelTests {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun quantity_twelve_cupcakes() {
        val viewModel = OrderViewModel()
        viewModel.quantity.observeForever {}
        viewModel.setQuantity(12)
        assertEquals(12, viewModel.quantity.value)
    }


    @Test
    fun priceCalculationForTwelveCupcakes() {
        // Arrange
        val viewModel = OrderViewModel()
        viewModel.price.observeForever {}

        // Act
        viewModel.setQuantity(12)

        // Assert
        assertEquals("420000", viewModel.price.value)
    }
}