package com.wealthPark.testApplication.ui.cityAndFoodList

import com.wealthPark.testApplication.data.local.model.CityItem
import com.wealthPark.testApplication.data.local.model.FoodItem
import com.wealthPark.testApplication.ui.base.IBaseNavigator

interface ICityAndFoodNavigator : IBaseNavigator {
    fun onSetCityInfo(cityList: ArrayList<CityItem>)
    fun onSetFoodInfo(foodList: ArrayList<FoodItem>)
    fun messageDialog(message: String)
}