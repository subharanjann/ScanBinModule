package com.mobillor.scanbinmodule.domain.useCase

import com.mobillor.scanbinmodule.data.models.SingleRFIDScanforBinModel

import com.mobillor.scanbinmodule.domain.ScanBinRepository
import com.mobillor.scanbinmodule.util.Resource


class GetBinfromRFIDDetailsUseCase(private val repository: ScanBinRepository) {
    suspend operator fun invoke(token : String , rfid : String): Resource<SingleRFIDScanforBinModel> {
        return try { repository.getrfiddetailsbysinglebinscanning(token,rfid)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}