package com.mobillor.scanbinmodule.presentation.activity

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.mobillor.scanbinmodule.data.apiClient.NewApiClient
import com.mobillor.scanbinmodule.data.repository.ScanBinRepositoryImpl
import com.mobillor.scanbinmodule.domain.useCase.GetBinIDDetailsUseCase
import com.mobillor.scanbinmodule.domain.useCase.GetBinfromRFIDDetailsUseCase
import com.mobillor.scanbinmodule.domain.useCase.GetScanBinCodeUseCase
import com.mobillor.scanbinmodule.presentation.commonComposables.ScanHintCard
import com.mobillor.scanbinmodule.presentation.commonComposables.StatusAndScanButton
import com.mobillor.scanbinmodule.presentation.commonComposables.TopNavigationBar
import com.mobillor.scanbinmodule.presentation.viewModel.ScanBinViewModel
import com.mobillor.scanbinmodule.presentation.viewModelFactory.ScanBinViewModelFactory
import com.mobillor.scanbinmodule.util.BaseActivity
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.SWMSTheme

class ScanBinStarterActivity : BaseActivity() {
    var status = ""
    private var baseURL : String =""
    private var userToken : String =""
    private lateinit var viewModel : ScanBinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        initProgressDialog()
        setUp()

        setContent {

            SWMSTheme {
                ScanBinStarter()
            }
        }

    }
    @Preview
    @Composable
    private fun ScanBinStarter(){
        var currentStatus by remember { mutableStateOf("QR Code") }
        Scaffold(

            topBar = {
                Column {
                    Spacer(modifier = Modifier.height(32.dp))
                    TopNavigationBar("Scan Bin"){onBackPressed()}
                }
            },
            floatingActionButton = {
                StatusAndScanButton(
                    currentStatus = currentStatus,
                    onStatusChange = { status ->
                        currentStatus = status
                        Log.e("checkingstatus2", currentStatus)
                    },
                    onScanButtonClick = { status ->
                        handleScanButtonClick(status)
                    },
                    this)
            }
        ) { innerPadding ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .background(Color.White)) {
                ScanHintCard(hint = "Scan your Bin!")
                Spacer(modifier = Modifier.weight(1f))

            }

        }
    }
    private fun setUp(){
        baseURL = intent.getStringExtra("baseURL")?:""
        userToken = intent.getStringExtra("userToken")?:""
        BASE_URL = baseURL
        USER_TOKEN = userToken

        val repository = ScanBinRepositoryImpl(NewApiClient.getScanBinService())
        val factory = ScanBinViewModelFactory(GetBinfromRFIDDetailsUseCase(repository), GetBinIDDetailsUseCase(repository), GetScanBinCodeUseCase(repository))
        viewModel = ViewModelProvider(this, factory)[ScanBinViewModel::class.java]

        observer()
    }
private fun observer(){

}
    private fun handleScanButtonClick(currentStatus : String){
        if (false) {
            if (currentStatus == "RFID") {

                status = currentStatus
            } else if (currentStatus == "QR Code") {

                status = currentStatus

            }
        } else {

            status = currentStatus

            Log.e("checkingqmob", currentStatus)

            QRCodeInputFromMobileCamera()
        }
    }
    private fun QRCodeInputFromMobileCamera() {

        if (ContextCompat.checkSelfPermission(
                this@ScanBinStarterActivity,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            showCamera()
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.CAMERA)) {
            Toast.makeText(
                this@ScanBinStarterActivity,
                "Camera permission required",
                Toast.LENGTH_LONG
            ).show()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    private fun showCamera() {
        val options = ScanOptions()
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
        options.setPrompt("Scan QR code")
        options.setCameraId(0)
        options.setBeepEnabled(true)
        options.setBarcodeImageEnabled(true)
        options.setOrientationLocked(true)

        scanLauncher.launch(options)
    }


    @SuppressLint("SuspiciousIndentation")
    private val scanLauncher = registerForActivityResult(ScanContract()) { result : ScanIntentResult ->
        run {
            if (result.contents != null) {
                val scannedId = result.contents.toString()
                viewModel.getBinId(USER_TOKEN,scannedId)
            } else {
                Toast.makeText(this@ScanBinStarterActivity, "Cancelled", Toast.LENGTH_LONG).show()
            }
        }
    }
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted : Boolean ->
        if (isGranted) {
            showCamera()
        } else {
            ActivityCompat.requestPermissions(
                this@ScanBinStarterActivity, arrayOf(
                    android.Manifest.permission.CAMERA,
                ), 101
            )
        }
    }


    companion object{
        var BASE_URL = ""
        var USER_TOKEN = ""
    }
}