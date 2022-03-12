package com.wealthPark.testApplication.ui.details

import androidx.lifecycle.MutableLiveData
import com.wealthPark.testApplication.data.local.model.CityItem
import com.wealthPark.testApplication.data.local.model.FoodItem
import com.wealthPark.testApplication.data.repository.AppRepository
import com.wealthPark.testApplication.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: AppRepository
) : BaseViewModel<IDetailsNavigator>() {

    var title = MutableLiveData<String>()
    var description = MutableLiveData<String>()
    var cityImage = MutableLiveData<String>()


    fun setCityInfo(cityItem: CityItem) {
        cityItem.cityImage?.let {
            cityImage.value = it
        }
        cityItem.cityName?.let {
            title.value = it
        }
        cityItem.cityDescription?.let {
            description.value = it
        }
    }

    fun setFoodInfo(foodItem: FoodItem) {
        foodItem.foodImage?.let {
            cityImage.value = it
        }
        foodItem.foodName?.let {
            title.value = it
        }
    }

}