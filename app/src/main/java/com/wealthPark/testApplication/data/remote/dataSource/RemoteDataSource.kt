package com.wealthPark.testApplication.data.remote.dataSource

import com.wealthPark.testApplication.data.remote.apiService.ICityAndFoodService
import com.wealthPark.testApplication.data.remote.dataSource.baseDataSource.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cityAndFoodService: ICityAndFoodService
) : BaseDataSource() {
    suspend fun getAllCity() = getResult { cityAndFoodService.getAllCity() }
    suspend fun getAllFood() = getResult { cityAndFoodService.getAllFoods() }
}