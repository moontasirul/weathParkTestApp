package com.wealthPark.testApplication.data.remote.apiService

import com.wealthPark.testApplication.data.model.Car
import com.wealthPark.testApplication.data.model.FoodItem
import com.wealthPark.testApplication.data.remote.ApiEndPoint.Companion.CAR_LIST_API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IProductService {

    @GET(CAR_LIST_API)
    suspend fun getAllCars(): Response<Car>

    @GET("cars/{id}")
    suspend fun getCarInfo(@Path("id") id: Int): Response<FoodItem>
}