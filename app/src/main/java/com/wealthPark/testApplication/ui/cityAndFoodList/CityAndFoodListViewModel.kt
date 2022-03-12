package com.wealthPark.testApplication.ui.cityAndFoodList

import com.wealthPark.testApplication.data.local.model.CityItem
import com.wealthPark.testApplication.data.local.model.FoodItem
import com.wealthPark.testApplication.data.repository.AppRepository
import com.wealthPark.testApplication.ui.base.BaseViewModel
import com.wealthPark.testApplication.utils.AppEnum
import com.wealthPark.testApplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CityAndFoodListViewModel @Inject constructor(
    private val repository: AppRepository
) : BaseViewModel<ICityAndFoodNavigator>() {

    var cityResponse = repository.getAllCitys()
    var foodResponse = repository.getAllFood()

    fun getCityResponse(response: Resource<List<CityItem>>) {
           isLoading.value = true
        when (response.status.name) {
            AppEnum.API_CALL_STATUS.SUCCESS.name -> {
                response.data?.let {
                    navigator.onSetCityInfo(ArrayList(it))
                }
                isLoading.value = false
            }
            AppEnum.API_CALL_STATUS.ERROR.name -> {
                isLoading.value = false
                print(response.message)
            }
            AppEnum.API_CALL_STATUS.LOADING.name -> {
                isLoading.value = true
            }
        }
    }

    fun getFoodResponse(response: Resource<List<FoodItem>>) {
        when (response.status.name) {
            AppEnum.API_CALL_STATUS.SUCCESS.name -> {
                response.data?.let {
                    navigator.onSetFoodInfo(ArrayList(it))
                }
                isLoading.value = false

            }
            AppEnum.API_CALL_STATUS.ERROR.name -> {
                isLoading.value = false
                print(response.message)
            }
            AppEnum.API_CALL_STATUS.LOADING.name -> {
                isLoading.value = true
                print(response.message)
            }
        }
    }


}