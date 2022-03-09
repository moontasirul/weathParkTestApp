package com.wealthPark.testApplication.ui.carList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.example.QuickRentalResponse
import com.wealthPark.testApplication.data.model.Car
import com.wealthPark.testApplication.data.model.ProductItemItem
import com.wealthPark.testApplication.data.repository.AppRepository
import com.wealthPark.testApplication.ui.base.BaseViewModel
import com.wealthPark.testApplication.utils.AppEnum
import com.wealthPark.testApplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: AppRepository
) : BaseViewModel<IProductListNavigator>() {

    private val _response: MutableLiveData<Resource<Car>> = MutableLiveData()
    val response: LiveData<Resource<Car>> = _response


    var productItemList: ArrayList<ProductItemItem> = arrayListOf()

    fun fetchCarResponse() = viewModelScope.launch {
        repository.getCar().collect { values ->
            _response.value = values
        }
    }

    fun getCarResponse(response: Resource<Car>) {
        when (response.status.name) {
            AppEnum.API_CALL_STATUS.SUCCESS.name -> {
                response.data?.let {
                    productItemList.addAll(it)
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


    fun getReservationData(): QuickRentalResponse? {
        return repository.getReservationData()
    }

    fun removeReservationData() {
        repository.removeReservationData()
    }
}