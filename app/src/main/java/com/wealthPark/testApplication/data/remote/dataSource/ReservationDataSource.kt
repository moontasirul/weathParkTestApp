package com.wealthPark.testApplication.data.remote.dataSource

import com.wealthPark.testApplication.data.model.QuickRentalRequest
import com.wealthPark.testApplication.data.remote.apiService.IReservationService
import com.wealthPark.testApplication.data.remote.dataSource.baseDataSource.BaseDataSource
import javax.inject.Inject

class ReservationDataSource @Inject constructor(
    private val reservationService: IReservationService
) : BaseDataSource() {
    suspend fun setQuickRental(quickRentalRequest: QuickRentalRequest) =
        getResult { reservationService.getQuickRental(quickRentalRequest) }
}