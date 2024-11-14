package com.mobillor.scanbinmodule.data.models

data class SingleRFIDScanforBinModel(
    val `data`: List<Data>,
    val msg: String,
    val status: Boolean,
    val statusCode: Int
) {
    data class Data(
        val assetCode: String?,
        val assetName: String?,
        val binCode: String?,
        val binId: Int,
        val binName: String?,
        val binType: String?,
        val binTypeId: Int?,
        val isActive: Int?,
        val isEmpty: Int?
    )
}