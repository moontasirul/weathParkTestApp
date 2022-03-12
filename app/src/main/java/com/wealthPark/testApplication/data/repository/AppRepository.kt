package com.wealthPark.testApplication.data.repository

import com.wealthPark.testApplication.data.local.database.dao.CityDao
import com.wealthPark.testApplication.data.local.database.dao.FoodDao
import com.wealthPark.testApplication.data.local.prefs.AppPreferences
import com.wealthPark.testApplication.data.model.CityItem
import com.wealthPark.testApplication.data.model.FoodItem
import com.wealthPark.testApplication.data.remote.dataSource.RemoteDataSource
import com.wealthPark.testApplication.data.remote.dataSource.baseDataSource.BaseDataSource
import com.wealthPark.testApplication.utils.Resource
import com.wealthPark.testApplication.utils.performGetOperation
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class AppRepository @Inject constructor(
    private var dataSource: RemoteDataSource,
    private var preferences: AppPreferences,
    private var localDataSource: CityDao,
    private var localFoodDataSource: FoodDao

) : BaseDataSource() {

//    suspend fun getAllCity(): Flow<ArrayList<Resource<CityItem>>> {
//        return flow {
//            emit(dataSource.getAllCity())
//        }.flowOn(Dispatchers.IO)
//    }

    fun getAllCitys() = performGetOperation(
        databaseQuery = { localDataSource.getAllCity() },
        networkCall = { dataSource.getAllCity() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

    fun getAllFood() = performGetOperation(
        databaseQuery = { localFoodDataSource.getAllFood() },
        networkCall = { dataSource.getAllFood() },
        saveCallResult = { localFoodDataSource.insertAllFood(it) }
    )

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