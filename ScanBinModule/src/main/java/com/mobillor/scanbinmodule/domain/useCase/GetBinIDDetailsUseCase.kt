package com.mobillor.scanbinmodule.domain.useCase

import com.mobillor.scanbinmodule.data.models.GetBinIDModel
import com.mobillor.scanbinmodule.domain.ScanBinRepository
import com.mobillor.scanbinmodule.util.Resource


class GetBinIDDetailsUseCase(private val repository: ScanBinRepository) {
    suspend operator fun invoke(token : String, binId : Int): Resource<GetBinIDModel> {
        return try {
            repository.getscanbindetails(token, binId)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}