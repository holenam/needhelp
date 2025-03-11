package com.example.assignment2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Instrument(
    val name: String,
    val imageResId: Int,
    val rating: Float,
    val pricePerMonth: Int,
    val genres: List<String>
) : Parcelable
