package com.mobillor.scanbinmodule.data.models

import com.google.gson.annotations.SerializedName

data class SingleRFIDScanforBinResponse(
    @SerializedName("rfid")
    val rfid: String
)
