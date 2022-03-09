package com.wealthPark.testApplication.data.remote.apiService

import com.example.example.QuickRentalResponse
import com.wealthPark.testApplication.data.model.QuickRentalRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface IReservationService {
    @POST("default/wunderfleet-recruiting-mobile-dev-quick-rental")
    suspend fun getQuickRental(@Body quickRentalRequest: QuickRentalRequest): Response<QuickRentalResponse>
}