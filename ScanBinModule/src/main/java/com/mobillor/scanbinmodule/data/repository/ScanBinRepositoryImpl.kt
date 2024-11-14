package com.mobillor.scanbinmodule.data.repository

import com.mobillor.scanbinmodule.data.apiInterface.ScanBinInterface
import com.mobillor.scanbinmodule.data.models.GetBinCodeModel
import com.mobillor.scanbinmodule.data.models.GetBinIDModel
import com.mobillor.scanbinmodule.data.models.SingleRFIDScanforBinModel
import com.mobillor.scanbinmodule.domain.ScanBinRepository
import com.mobillor.scanbinmodule.util.Resource
import retrofit2.HttpException

class ScanBinRepositoryImpl(private val apiService : ScanBinInterface): ScanBinRepository {
    override suspend fun getscanbin(token: String, bin_code: String): Resource<GetBinCodeModel> {
        return try {
            val api = apiService.getscanbin(token,bin_code)
            if(api.isSuccessful){
                val response = api.body()
                if(response != null){
                    Resource.Success(response)
                }
                else{
                    Resource.Error("api response is null")
                }
            }
            else{
                Resource.Error(api.message())
            }
        }
        catch (e : HttpException){
            return Resource.Error(e.message?:"Http Exception")
        }
        catch (e :Exception){
            return Resource.Error(e.message?:"other exception")
        }
    }

    override suspend fun getrfiddetailsbysinglebinscanning(
        token: String,
        rfid: String
    ): Resource<SingleRFIDScanforBinModel> {
        return try {
            val api = apiService.getrfiddetailsbysinglebinscanning(token,rfid)
            if(api.isSuccessful){
                val response = api.body()
                if(response != null){
                    Resource.Success(response)
                }
                else{
                    Resource.Error("api response is null")
                }
            }
            else{
                Resource.Error(api.message())
            }
        }
        catch (e : HttpException){
            return Resource.Error(e.message?:"Http Exception")
        }
        catch (e :Exception){
            return Resource.Error(e.message?:"other exception")
        }
    }

    override suspend fun getscanbindetails(token: String, bin_id: Int): Resource<GetBinIDModel> {
        return try {
            val api = apiService.getscanbindetails(token,bin_id)
            if(api.isSuccessful){
                val response = api.body()
                if(response != null){
                    Resource.Success(response)
                }
                else{
                    Resource.Error("api response is null")
                }
            }
            else{
                Resource.Error(api.message())
            }
        }
        catch (e : HttpException){
            return Resource.Error(e.message?:"Http Exception")
        }
        catch (e :Exception){
            return Resource.Error(e.message?:"other exception")
        }
    }
}