package com.mobillor.scanbinmodule.data.models

import com.google.gson.annotations.SerializedName

data class GetBinIDResponse(
    @SerializedName("bin_id")
    val bin_id: Int
)
