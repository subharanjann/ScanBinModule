package com.mobillor.scanbinmodule.data.models


import com.google.gson.annotations.SerializedName

data class GetBinCodeModel(
    @SerializedName("data")
    val `data`: List<BinCodeData>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("statusCode")
    val statusCode: Int
)

data class BinCodeData(
    @SerializedName("binCode")
    val binCode: String,
    @SerializedName("binId")
    val binId: Int
)