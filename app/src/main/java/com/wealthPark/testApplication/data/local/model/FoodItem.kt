package com.wealthPark.testApplication.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "food_info")
data class FoodItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var foodName: String? = null,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    var foodImage: String? = null
)