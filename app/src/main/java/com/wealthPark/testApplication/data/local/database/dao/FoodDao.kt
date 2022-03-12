package com.wealthPark.testApplication.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wealthPark.testApplication.data.local.model.FoodItem

@Dao
interface FoodDao {

    @Query("SELECT * FROM food_info ORDER BY name DESC")
    fun getAllFood(): LiveData<List<FoodItem>>

    @Insert
    fun insertAllFood(food: List<FoodItem>)

    @Query("DELETE FROM food_info")
    fun removeAll()
}