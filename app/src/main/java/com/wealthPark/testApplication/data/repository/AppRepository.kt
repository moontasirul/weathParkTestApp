package com.wealthPark.testApplication.data.repository

import com.wealthPark.testApplication.data.local.prefs.AppPreferences
import com.wealthPark.testApplication.data.model.City
import com.wealthPark.testApplication.data.model.CityItem
import com.wealthPark.testApplication.data.model.Food
import com.wealthPark.testApplication.data.model.FoodItem
import com.wealthPark.testApplication.data.remote.dataSource.RemoteDataSource
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
    private var dataSource: RemoteDataSource,
    private var preferences: AppPreferences
) : BaseDataSource() {

    suspend fun getAllCity(): Flow<Resource<City>> {
        return flow {
            emit(dataSource.getAllCity())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllFood(): Flow<Resource<Food>> {
        return flow {
            emit(dataSource.getAllFood())
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCityDetails(id: Int): Flow<Resource<CityItem>> {
        return flow {
            emit(dataSource.getCityInfo(id))
        }.flowOn(Dispatchers.IO)
    }


    suspend fun getFoodDetails(id: Int): Flow<Resource<FoodItem>> {
        return flow {
            emit(dataSource.getFoodInfo(id))
        }.flowOn(Dispatchers.IO)
    }
}