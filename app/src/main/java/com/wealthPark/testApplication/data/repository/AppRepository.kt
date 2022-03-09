package com.wealthPark.testApplication.data.repository

import com.example.example.QuickRentalResponse
import com.wealthPark.testApplication.data.local.prefs.AppPreferences
import com.wealthPark.testApplication.data.model.Car
import com.wealthPark.testApplication.data.model.FoodItem
import com.wealthPark.testApplication.data.model.QuickRentalRequest
import com.wealthPark.testApplication.data.remote.dataSource.ProductRemoteDataSource
import com.wealthPark.testApplication.data.remote.dataSource.ReservationDataSource
import com.wealthPark.testApplication.data.remote.dataSource.baseDataSource.BaseDataSource
import com.wealthPark.testApplication.utils.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class AppRepository @Inject constructor(
    private var productDataSource: ProductRemoteDataSource,
    private var reservationDataSource: ReservationDataSource,
    private var preferences: AppPreferences
) : BaseDataSource() {

    suspend fun getCar(): Flow<Resource<Car>> {
        return flow {
            emit(productDataSource.getAllCars())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCarDetails(id: Int): Flow<Resource<FoodItem>> {
        return flow {
            emit(productDataSource.getCarInfo(id))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun setQuickRentalReservation(quickRentalRequest: QuickRentalRequest): Flow<Resource<QuickRentalResponse>> {
        return flow {
            emit(reservationDataSource.setQuickRental(quickRentalRequest))
        }.flowOn(Dispatchers.IO)
    }

    fun getReservationData(): QuickRentalResponse? {
        return preferences.quickRentalResponse
    }

    fun setReservationData(quickRentalResponse: QuickRentalResponse) {
        preferences.quickRentalResponse = quickRentalResponse
    }

    fun removeReservationData() {
        preferences.quickRentalResponse = null
    }
}