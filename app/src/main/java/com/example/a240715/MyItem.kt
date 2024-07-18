package com.example.a240715

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MyItem(
    val aIcon1: Int,
    val aSeller: String,
    val aTitle: String,
    val aAddress: String,
    val aPrice: String,
    val aEtc1: String,
    val aEtc2: String,
    val aContext: String
) : Parcelable

object ProductManager {
    val products = mutableListOf<MyItem>()

    fun addProduct(
        aIcon1: Int,
        aSeller: String,
        aName: String,
        aAddress: String,
        aPrice: String,
        aEtc1: String,
        aEtc2: String,
        aContext: String
    ) {
        products.add(MyItem(aIcon1, aSeller, aName, aAddress, aPrice, aEtc1, aEtc2, aContext))
    }
}