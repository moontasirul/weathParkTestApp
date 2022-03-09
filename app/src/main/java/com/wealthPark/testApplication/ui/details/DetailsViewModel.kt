package com.wealthPark.testApplication.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wealthPark.testApplication.data.model.CityItem
import com.wealthPark.testApplication.data.model.FoodItem
import com.wealthPark.testApplication.data.repository.AppRepository
import com.wealthPark.testApplication.ui.base.BaseViewModel
import com.wealthPark.testApplication.utils.AppEnum
import com.wealthPark.testApplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: AppRepository
) : BaseViewModel<IDetailsNavigator>() {

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

    private val _cityResponse: MutableLiveData<Resource<CityItem>> = MutableLiveData()
    val response: LiveData<Resource<CityItem>> = _cityResponse

    fun setCarId(id: Int) {
        carId.value = id
    }


    fun fetchCarDetails() {
        viewModelScope.launch {
            carId.value?.let {
                repository.getCityDetails(it).collect { value ->
                    _cityResponse.value = value
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




}