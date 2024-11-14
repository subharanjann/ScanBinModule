package com.mobillor.scanbinmodule.data.models

import com.google.gson.annotations.SerializedName

data class GetBinCodeResponse(
    @SerializedName("bin_code")
    val bin_code: String
)
