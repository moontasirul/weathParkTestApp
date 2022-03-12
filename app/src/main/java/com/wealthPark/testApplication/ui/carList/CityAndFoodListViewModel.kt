package com.wealthPark.testApplication.ui.carList

import com.wealthPark.testApplication.data.model.CityItem
import com.wealthPark.testApplication.data.model.FoodItem
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

    fun getCarResponse(response: Resource<List<CityItem>>) {
        when (response.status.name) {
            AppEnum.API_CALL_STATUS.SUCCESS.name -> {
                response.data?.let {

                }
                isLoading.set(false)

            }
            AppEnum.API_CALL_STATUS.ERROR.name -> {
                isLoading.set(false)
                print(response.message)
            }
            AppEnum.API_CALL_STATUS.LOADING.name -> {
                isLoading.set(true)
                print(response.message)
            }
        }
    }

    fun getFoodResponse(response: Resource<List<FoodItem>>) {
        when (response.status.name) {
            AppEnum.API_CALL_STATUS.SUCCESS.name -> {
                response.data?.let {

                }
                isLoading.set(false)

            }
            AppEnum.API_CALL_STATUS.ERROR.name -> {
                isLoading.set(false)
                print(response.message)
            }
            AppEnum.API_CALL_STATUS.LOADING.name -> {
                isLoading.set(true)
                print(response.message)
            }
        }
    }


}