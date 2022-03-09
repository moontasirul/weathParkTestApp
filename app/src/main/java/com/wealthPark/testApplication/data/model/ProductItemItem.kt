package com.wealthPark.testApplication.data.model

import com.google.gson.annotations.SerializedName

data class ProductItemItem(
    @SerializedName("name"        ) var productName        : String? = null,
    @SerializedName("image"       ) var productImage       : String? = null,
    @SerializedName("description" ) var productDescription : String? = null
)