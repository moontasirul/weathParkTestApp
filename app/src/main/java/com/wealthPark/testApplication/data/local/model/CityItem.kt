package com.wealthPark.testApplication.data.local.model

import android.os.Parcel
import android.os.Parcelable
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(cityName)
        parcel.writeString(cityImage)
        parcel.writeString(cityDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityItem> {
        override fun createFromParcel(parcel: Parcel): CityItem {
            return CityItem(parcel)
        }

        override fun newArray(size: Int): Array<CityItem?> {
            return arrayOfNulls(size)
        }
    }
}