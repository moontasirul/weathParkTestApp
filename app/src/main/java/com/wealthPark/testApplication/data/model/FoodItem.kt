package com.wealthPark.testApplication.data.model

import com.google.gson.annotations.SerializedName

data class FoodItem(
    @SerializedName("name"  ) var foodName  : String? = null,
    @SerializedName("image" ) var foodImage : String? = null
)