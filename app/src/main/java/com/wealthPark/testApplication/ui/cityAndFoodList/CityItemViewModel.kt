package com.wealthPark.testApplication.ui.cityAndFoodList

import androidx.lifecycle.MutableLiveData
import com.wealthPark.testApplication.data.local.model.CityItem

class CityItemViewModel(
    position: Int,
    mCityItemModel: CityItem,
    listener: CityItemViewModelListener
) {

    private val mListener: CityItemViewModelListener = listener

    var itemId: MutableLiveData<Int> = MutableLiveData(position)
    var cityName: MutableLiveData<String> = MutableLiveData(mCityItemModel.cityName)
    var cityDescription: MutableLiveData<String> = MutableLiveData(mCityItemModel.cityDescription)
    var cityImage: MutableLiveData<String> = MutableLiveData(mCityItemModel.cityImage)


    fun onItemClick() {
        itemId.value?.let { mListener.onContentDetails(it) }
    }

    interface CityItemViewModelListener {
        fun onContentDetails(position: Int)
    }
}