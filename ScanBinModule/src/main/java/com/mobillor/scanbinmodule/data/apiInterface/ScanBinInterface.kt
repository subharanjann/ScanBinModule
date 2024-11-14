package com.mobillor.scanbinmodule.data.apiInterface

import com.mobillor.scanbinmodule.data.models.GetBinCodeModel
import com.mobillor.scanbinmodule.data.models.GetBinIDModel
import com.mobillor.scanbinmodule.data.models.SingleRFIDScanforBinModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ScanBinInterface {

    @GET("info/validate_bin")
    suspend fun getscanbin(@Header("Authorization") token: String, @Query("bin_code") bin_code: String): Response<GetBinCodeModel>

    @GET("rfid_mapping/get_bin_by_rfid")
    suspend fun getrfiddetailsbysinglebinscanning(@Header("Authorization") token: String, @Query("rfid") rfid: String): Response<SingleRFIDScanforBinModel>

    @GET("info/scan_bin")
    suspend fun getscanbindetails(@Header("Authorization") token: String, @Query("bin_id") bin_id: Int): Response<GetBinIDModel>

}