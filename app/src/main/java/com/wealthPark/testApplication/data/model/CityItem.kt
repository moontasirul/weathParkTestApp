package com.wealthPark.testApplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "city_info")
data class CityItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var cityName: String? = null,

    @ColumnInfo(name = "image")
    @SerializedName("image")
    var cityImage: String? = null,


    @ColumnInfo(name = "description")
    @SerializedName("description")
    var cityDescription: String? = null
)