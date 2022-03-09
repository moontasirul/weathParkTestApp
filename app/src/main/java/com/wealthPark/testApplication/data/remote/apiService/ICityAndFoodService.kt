package com.wealthPark.testApplication.data.remote.apiService

import com.wealthPark.testApplication.data.model.City
import com.wealthPark.testApplication.data.model.CityItem
import com.wealthPark.testApplication.data.model.Food
import com.wealthPark.testApplication.data.model.FoodItem
import com.wealthPark.testApplication.data.remote.ApiEndPoint.Companion.CITY_LIST_API
import com.wealthPark.testApplication.data.remote.ApiEndPoint.Companion.Food_LIST_API
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ICityAndFoodService {

    @GET(CITY_LIST_API)
    suspend fun getAllCity(): Response<City>

    @GET(Food_LIST_API)
    suspend fun getAllFoods(): Response<Food>

    @GET("city/{id}")
    suspend fun getCityInfo(@Path("id") id: Int): Response<CityItem>

    @GET("foods/{id}")
    suspend fun getFoodInfo(@Path("id") id: Int): Response<FoodItem>
}