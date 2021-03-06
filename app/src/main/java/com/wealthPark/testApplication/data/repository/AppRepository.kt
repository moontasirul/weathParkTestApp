package com.wealthPark.testApplication.data.repository

import com.wealthPark.testApplication.data.local.database.dao.CityDao
import com.wealthPark.testApplication.data.local.database.dao.FoodDao
import com.wealthPark.testApplication.data.local.prefs.AppPreferences
import com.wealthPark.testApplication.data.remote.dataSource.RemoteDataSource
import com.wealthPark.testApplication.data.remote.dataSource.baseDataSource.BaseDataSource
import com.wealthPark.testApplication.utils.performGetOperation
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class AppRepository @Inject constructor(
    private var dataSource: RemoteDataSource,
    private var preferences: AppPreferences,
    private var localDataSource: CityDao,
    private var localFoodDataSource: FoodDao

) : BaseDataSource() {

    fun getAllCitys() = performGetOperation(
        databaseQuery = { localDataSource.getAllCity() },
        networkCall = { dataSource.getAllCity() },
        saveCallResult = {
            // due to currently in api response there have no unique id or parameter
            // we remove data from local db then insert new
            localDataSource.removeAll()
            localDataSource.insertAll(it)

        }
    )

    fun getAllFood() = performGetOperation(
        databaseQuery = { localFoodDataSource.getAllFood() },
        networkCall = { dataSource.getAllFood() },
        saveCallResult = {
            localFoodDataSource.removeAll()
            localFoodDataSource.insertAllFood(it)
        }
    )
}