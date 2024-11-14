package com.mobillor.scanbinmodule.data.models


import com.google.gson.annotations.SerializedName

data class GetBinIDModel(
    @SerializedName("data")
    val `data`: List<BinIDInfoData>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("statusCode")
    val statusCode: Int
)
    data class BinIDInfoData(
        @SerializedName("binId")
        val binId: Int?,
        @SerializedName("itemCode")
        val itemCode: String?,
        @SerializedName("itemDescription")
        val itemDescription: String?,
        @SerializedName("itemId")
        val itemId: Int?,
        @SerializedName("locationId")
        val locationId: Int?,
        @SerializedName("palletId")
        val palletId: Int?,
        @SerializedName("qty")
        val qty: Double?,
        @SerializedName("sku")
        val sku: String?,
        @SerializedName("skuId")
        val skuId: Int?,
        @SerializedName("status")
        val status: Int?,
        @SerializedName("suid")
        val suid: String?,
        @SerializedName("suidId")
        val suidId: Int?,
        @SerializedName("uom")
        val uom: String?,
        @SerializedName("locationCode")
        val locationCode: String?
    )


