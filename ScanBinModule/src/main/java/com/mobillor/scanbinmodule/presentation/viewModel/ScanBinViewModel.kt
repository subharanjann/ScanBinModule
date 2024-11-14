package com.mobillor.scanbinmodule.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobillor.scanbinmodule.data.models.GetBinCodeModel
import com.mobillor.scanbinmodule.data.models.GetBinIDModel
import com.mobillor.scanbinmodule.data.models.SingleRFIDScanforBinModel
import com.mobillor.scanbinmodule.domain.useCase.GetBinIDDetailsUseCase
import com.mobillor.scanbinmodule.domain.useCase.GetBinfromRFIDDetailsUseCase
import com.mobillor.scanbinmodule.domain.useCase.GetScanBinCodeUseCase
import com.mobillor.scanbinmodule.util.Resource
import kotlinx.coroutines.launch

class ScanBinViewModel(
    private val getBinfromRFIDDetailsUseCase: GetBinfromRFIDDetailsUseCase,
    private val getBinIDDetailsUseCase: GetBinIDDetailsUseCase,
    private val getScanBinCodeUseCase: GetScanBinCodeUseCase
): ViewModel() {

    private val _getBinIDDetails = MutableLiveData<Resource<GetBinIDModel>>()
    val getBinIDDetails: LiveData<Resource<GetBinIDModel>> = _getBinIDDetails

    private val _getBinRFIDDetails = MutableLiveData<Resource<SingleRFIDScanforBinModel>>()
    val getBinRFIDDetails: LiveData<Resource<SingleRFIDScanforBinModel>> = _getBinRFIDDetails

    private val _getBincodeData = MutableLiveData<Resource<GetBinCodeModel>>()
    val getBincodeData: LiveData<Resource<GetBinCodeModel>> = _getBincodeData

    fun  getBinId(token:String, binCode : String){
        viewModelScope.launch {
            val result  = getScanBinCodeUseCase(token,binCode)
            _getBincodeData.postValue(result)
        }
    }
    fun  getRfidData(token:String, rfid : String){
        viewModelScope.launch {
            val result = getBinfromRFIDDetailsUseCase(token,rfid)
            _getBinRFIDDetails.postValue(result)
        }
    }
    fun  getBinData(token:String, binId : Int){
        viewModelScope.launch {
            val result = getBinIDDetailsUseCase(token,binId)
            _getBinIDDetails.postValue(result)
        }
    }
}