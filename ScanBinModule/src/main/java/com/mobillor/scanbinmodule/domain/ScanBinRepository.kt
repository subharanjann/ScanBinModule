package com.mobillor.scanbinmodule.domain

import com.mobillor.scanbinmodule.data.models.GetBinCodeModel
import com.mobillor.scanbinmodule.data.models.GetBinIDModel
import com.mobillor.scanbinmodule.data.models.SingleRFIDScanforBinModel
import com.mobillor.scanbinmodule.util.Resource

interface ScanBinRepository {

    suspend fun getscanbin(token: String,bin_code: String ): Resource<GetBinCodeModel>

    suspend fun getrfiddetailsbysinglebinscanning(token: String,rfid: String): Resource<SingleRFIDScanforBinModel>

    suspend fun getscanbindetails( token: String,bin_id: Int): Resource<GetBinIDModel>
}