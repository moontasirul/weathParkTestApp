package com.wealthPark.testApplication.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wealthPark.testApplication.data.local.model.CityItem

@Dao
interface CityDao {

    @Query("SELECT * FROM city_info ORDER BY name DESC")
    fun getAllCity(): LiveData<List<CityItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(city: List<CityItem>)

    @Query("DELETE FROM city_info")
    fun removeAll()
}
