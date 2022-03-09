package com.wealthPark.testApplication.data.remote.dataSource

import com.wealthPark.testApplication.data.remote.apiService.IProductService
import com.wealthPark.testApplication.data.remote.dataSource.baseDataSource.BaseDataSource
import javax.inject.Inject

class ProductRemoteDataSource  @Inject constructor(
    private val productService: IProductService
): BaseDataSource() {
    suspend fun getAllCars() = getResult { productService.getAllCars() }
    suspend fun getCarInfo(id: Int) = getResult { productService.getCarInfo(id) }
}