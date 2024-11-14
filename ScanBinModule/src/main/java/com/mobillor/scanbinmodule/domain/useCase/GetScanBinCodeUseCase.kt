package com.mobillor.scanbinmodule.domain.useCase

import com.mobillor.scanbinmodule.data.models.GetBinCodeModel
import com.mobillor.scanbinmodule.domain.ScanBinRepository
import com.mobillor.scanbinmodule.util.Resource


class GetScanBinCodeUseCase(private val repository: ScanBinRepository) {
    suspend operator fun invoke(token : String , binCode : String): Resource<GetBinCodeModel> {
        return try {
            repository.getscanbin(token,binCode)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}