package com.example.musicrental

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instrument(
    val name: String,
    val imageResId: Int,
    val rating: Float,
    val attributes: List<String>,
    val price: Int,
    var stock: Int,
    var rentedBy: String?
) : Parcelable
