package com.rickymorty.androiddemo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LocationData (
    var id: Int,
    var name : String,
    var url : String,
    var dimension: String,
    var type : String,
    var created: String
): Parcelable