package com.wealthPark.testApplication.ui.cityAndFoodList

import androidx.lifecycle.MutableLiveData
import com.wealthPark.testApplication.data.local.model.FoodItem

class FoodItemViewModel(
    position: Int,
    mFoodItemModel: FoodItem,
    listener: FoodItemAdapterListener
) {

    private val mListener: FoodItemAdapterListener = listener

    var foodItem: FoodItem = mFoodItemModel
    var foodName: MutableLiveData<String> = MutableLiveData(mFoodItemModel.foodName)
    var foodImage: MutableLiveData<String> = MutableLiveData(mFoodItemModel.foodImage)


    fun onItemClick() {
        mListener.onContentDetails(foodItem)
    }

    interface FoodItemAdapterListener {
        fun onContentDetails(mFoodListModel: FoodItem)
    }
}