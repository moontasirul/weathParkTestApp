package com.wealthPark.testApplication.data.remote.apiService

import com.wealthPark.testApplication.data.local.model.City
import com.wealthPark.testApplication.data.local.model.Food
import com.wealthPark.testApplication.data.remote.ApiEndPoint.Companion.CITY_LIST_API
import com.wealthPark.testApplication.data.remote.ApiEndPoint.Companion.Food_LIST_API
import retrofit2.Response
import retrofit2.http.GET

interface ICityAndFoodService {

    @GET(CITY_LIST_API)
    suspend fun getAllCity(): Response<City>

    @GET(Food_LIST_API)
    suspend fun getAllFoods(): Response<Food>
}