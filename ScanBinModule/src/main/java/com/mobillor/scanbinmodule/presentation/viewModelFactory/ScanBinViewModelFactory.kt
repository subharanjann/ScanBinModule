package com.mobillor.scanbinmodule.presentation.viewModelFactory

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobillor.scanbinmodule.domain.useCase.GetBinIDDetailsUseCase
import com.mobillor.scanbinmodule.domain.useCase.GetBinfromRFIDDetailsUseCase
import com.mobillor.scanbinmodule.domain.useCase.GetScanBinCodeUseCase
import com.mobillor.scanbinmodule.presentation.viewModel.ScanBinViewModel

class ScanBinViewModelFactory(
    private val getBinfromRFIDDetailsUseCase: GetBinfromRFIDDetailsUseCase,
    private val getBinIDDetailsUseCase: GetBinIDDetailsUseCase,
    private val getScanBinCodeUseCase: GetScanBinCodeUseCase
):  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ScanBinViewModel::class.java)){
            return ScanBinViewModel(getBinfromRFIDDetailsUseCase,getBinIDDetailsUseCase,getScanBinCodeUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}