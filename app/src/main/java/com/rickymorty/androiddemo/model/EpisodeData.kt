package com.rickymorty.androiddemo.model

import kotlinx.android.parcel.Parcelize
import android.os.Parcelable


@Parcelize
data class EpisodeData (var id: Int,
var name : String,
var air_date : String,
var episode: String,
var url : String,
var created: String, var characters:List<String>
): Parcelable
