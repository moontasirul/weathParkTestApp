package com.wealthPark.testApplication.ui.carDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wealthPark.testApplication.data.model.FoodItem
import com.wealthPark.testApplication.data.model.QuickRentalRequest
import com.wealthPark.testApplication.data.repository.AppRepository
import com.wealthPark.testApplication.ui.base.BaseViewModel
import com.wealthPark.testApplication.utils.AppEnum
import com.wealthPark.testApplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: AppRepository
) : BaseViewModel<IProductDetailsNavigator>() {

    private val carId = MutableLiveData<Int>()
    var carTitle = MutableLiveData<String>()
    var carCleanStatus = MutableLiveData<String>()
    var carDamageStatus = MutableLiveData<String>()
    var licencePlate = MutableLiveData<String>()
    var fuelLevel = MutableLiveData<String>()
    var vehicleStateId = MutableLiveData<String>()
    var hardwareId = MutableLiveData<String>()
    var vehicleTypeId = MutableLiveData<String>()
    var pricingTime = MutableLiveData<String>()
    var pricingParking = MutableLiveData<String>()
    var activatedByHardwareStatus = MutableLiveData<String>()
    var locationId = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    var zipCode = MutableLiveData<String>()
    var city = MutableLiveData<String>()
    var reservationState = MutableLiveData<String>()
    var damageDescription = MutableLiveData<String>()
    var vehicleTypeImage = MutableLiveData<String>()

    private val _carResponse: MutableLiveData<Resource<FoodItem>> = MutableLiveData()
    val response: LiveData<Resource<FoodItem>> = _carResponse

    fun setCarId(id: Int) {
        carId.value = id
    }


    fun fetchCarDetails() {
        viewModelScope.launch {
            carId.value?.let {
                repository.getCarDetails(it).collect { value ->
                    _carResponse.value = value
                }
            }
        }
    }

    fun getCarData(foodItem: Resource<FoodItem>) {
        when (foodItem.status.name) {
            AppEnum.API_CALL_STATUS.SUCCESS.name -> {
                foodItem.data?.let {
                    print(it)
                    isLoading.set(false)

                }
            }
            AppEnum.API_CALL_STATUS.ERROR.name -> {
                isLoading.set(false)
                print(foodItem.message)
            }
            AppEnum.API_CALL_STATUS.LOADING.name -> {
                isLoading.set(true)
                print(foodItem.message)
            }
        }

    }




    fun onClickQuickRent() {
        isLoading.set(true)
        viewModelScope.launch {
            carId.value?.let {
                repository.setQuickRentalReservation(QuickRentalRequest(it)).collect { response ->
                    when (response.status.name) {
                        AppEnum.API_CALL_STATUS.SUCCESS.name -> {
                            response.data?.let {
                                isLoading.set(false)
                                repository.setReservationData(it)
                                navigator.showSuccessDialog()
                            }
                        }
                        AppEnum.API_CALL_STATUS.ERROR.name -> {
                            isLoading.set(false)
                            navigator.showFailedDialog()
                            print(response.message)
                        }
                        AppEnum.API_CALL_STATUS.LOADING.name -> {
                            isLoading.set(true)
                        }
                    }
                }
            }
        }
    }
}