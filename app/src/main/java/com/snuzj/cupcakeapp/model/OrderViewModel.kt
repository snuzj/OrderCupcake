package com.snuzj.cupcakeapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


private const val PRICE_PER_CUPCAKE = 35000
private const val PRICE_FOR_SAME_DAY_PICKUP = 60000
class OrderViewModel: ViewModel() {

    //Quantity of cupcakes in this order
    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    //Cupcake flavor for this order
    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    //Possible date options
    val dateOptions: List<String> = getPickupOptions()

    //Pickup date
    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    //Price of the order so far
    private val _price = MutableLiveData<Int>()
    val price: LiveData<String> = _price.map{
        //Format the price into the local currency and return this as LiveData<String>
        NumberFormat.getCurrencyInstance().format(it)
    }

    init{
        // Set initial values for the order
        resetOrder()
        resetPickup()
    }

    fun setQuantity(numberCupcakes: Int){
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(desiredFlavor: String){
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String){
        _date.value = pickupDate
        updatePrice()
    }

    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }


    fun resetOrder() {
        _quantity.value = 0
        _price.value = 0
    }

    fun resetFlavor(){
        _flavor.value = ""
    }
    fun resetPickup(){
        _date.value = dateOptions[0]
    }

    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        // If the user selected the first option (today) for pickup, add the surcharge
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    private fun getPickupOptions(): List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("vi", "VN"))
        val calendar = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return options
    }

}