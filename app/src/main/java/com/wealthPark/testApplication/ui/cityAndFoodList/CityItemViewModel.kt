package com.wealthPark.testApplication.ui.cityAndFoodList

import androidx.lifecycle.MutableLiveData
import com.wealthPark.testApplication.data.local.model.CityItem

class CityItemViewModel(
    position: Int,
    mCityItemModel: CityItem,
    listener: CityItemViewModelListener
) {

    private val mListener: CityItemViewModelListener = listener

    var cityItem: MutableLiveData<CityItem> = MutableLiveData(mCityItemModel)
    var cityName: MutableLiveData<String> = MutableLiveData(mCityItemModel.cityName)
    var cityDescription: MutableLiveData<String> = MutableLiveData(mCityItemModel.cityDescription)
    var cityImage: MutableLiveData<String> = MutableLiveData(mCityItemModel.cityImage)


    fun onItemClick() {
        cityItem.value?.let { mListener.onContentDetails(it) }
    }

    interface CityItemViewModelListener {
        fun onContentDetails(mCityListModel: CityItem)
    }
}