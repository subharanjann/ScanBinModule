package com.mobillor.scanbinmodule.presentation.commonComposables

import android.content.Context
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button

import androidx.compose.material.Switch
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Text
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mobillor.scanbinmodule.R

import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.SWMSTheme
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.darkPurpleStuff
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.lightPurpleStuff
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.lightWhite
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.primaryColor

class BasicElements  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SWMSTheme {
                TopNavigationBar(name = "") {
                    
                }
            }
        }
    }
}

//top nav
    @Composable
    fun TopNavigationBar(name : String, onBackClick : ()->Unit) {

        Card(
            colors = CardDefaults.cardColors(containerColor = primaryColor),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth()
                .height(60.dp)
        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            )
            {

                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "",

                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 16.dp)
                        .clickable(onClick = onBackClick)
                )
                Spacer(modifier = Modifier
                    .height(20.dp)
                    .weight(1f))

                Text(
                    text = name,
                    style = TextStyles.whiteTitleTextStyle
                )

                Spacer(modifier = Modifier
                    .height(20.dp)
                    .weight(1f))
                Spacer(modifier = Modifier
                    .width(40.dp))


            }

        }

    }

//hint card
@Composable
fun ScanHintCard(hint : String){
    CurvedBoxWithDottedBorder(280.dp,20.dp) {
        Column(Modifier.padding(0.dp,0.dp,0.dp,0.dp).background(lightWhite)) {
            AnimatedPreloader()
            Text(
                text = hint,
                style = TextStyles.wildcardTextStyle,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
            )
        }

    }
}

//scanners
@Composable
fun StatusAndScanButton(
    currentStatus : String,
    onStatusChange : (String) -> Unit,
    onScanButtonClick : (String) -> Unit,
    context : Context
) {
    var switchState by remember { mutableStateOf(currentStatus == "RFID") }
    val status = if (switchState) "RFID" else "QR Code"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 10.dp,
                bottom = 56.dp,
                end = 10.dp
            ),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Status Switch
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Mode: $status",
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Switch(
                    checked = switchState,
                    onCheckedChange = { isChecked ->
                        switchState = isChecked
                        onStatusChange(if (isChecked) "RFID" else "QR Code")
                    }
                )
            }

            FloatingActionButton(
                onClick = { onScanButtonClick(status) },
                shape = MaterialTheme.shapes.large,
                backgroundColor = lightPurpleStuff,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 2.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.binss),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(darkPurpleStuff),
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

//lottie
@Composable
fun AnimatedPreloader(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.scani))

    val preloaderProgress by animateLottieCompositionAsState(preloaderLottieComposition, iterations = LottieConstants.IterateForever, isPlaying = true)


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
            .height(192.dp)
            .width(240.dp)
    )
}
@Composable
fun scanPalletHintCard(scanned_info:String) {

    var data=scanned_info

    val hint = if (data.isNotEmpty()&&!data.isNullOrBlank()) {
        data
    } else {
        "Scan Pallet!"
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pallet),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier.size(18.dp),
                contentScale = ContentScale.Fit
            )
            androidx.compose.material3.Text(
                text = hint,
                style = TextStyles.smallWildcardTextStyle,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
    }
}

@Composable
fun scanLocationHintCard(scanned_location:String) {

    var data=scanned_location

    val hint = if (data.isNotEmpty()&&!data.isNullOrBlank()) {
        data
    } else {
        "Scan Location!"
    }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(3.dp),
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.locationlogo),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier.size(18.dp),
                contentScale = ContentScale.Fit
            )
            androidx.compose.material3.Text(
                text = hint,
                style = TextStyles.smallWildcardTextStyle,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
    }
}
@Composable
fun CardView2(itemcode:String,suid:String,sku:String,qty:String,description:String,status:String,uom:String) {

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier
            .width(390.dp)
            .padding(8.dp, 0.dp)
            .clip(MaterialTheme.shapes.medium)

    ) {
        Column(
            modifier = Modifier
                .padding(0.dp,12.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .height(24.dp)
            ){
                androidx.compose.material3.Text(
                    text = "Item Code",
                    style = TextStyles.smallMediumNormalTextStyle,
                    modifier = Modifier
                        .width(100.dp)
                )
                androidx.compose.material3.Text(
                    text = ":",
                    style = TextStyles.smallMediumNormalTextStyle
                )
                androidx.compose.material3.Text(
                    text = " $itemcode",
                    style = TextStyles.smallMediumNormalTextStyle
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .height(24.dp)
            ){
                androidx.compose.material3.Text(
                    text = "SUID",
                    style = TextStyles.smallMediumNormalTextStyle,
                    modifier = Modifier
                        .width(100.dp)
                )
                androidx.compose.material3.Text(
                    text = ":",
                    style = TextStyles.smallMediumNormalTextStyle
                )
                androidx.compose.material3.Text(
                    text = " $suid",
                    style = TextStyles.smallMediumNormalTextStyle
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .height(24.dp)
            ){
                androidx.compose.material3.Text(
                    text = "SKU",
                    style = TextStyles.smallMediumNormalTextStyle,
                    modifier = Modifier
                        .width(100.dp)
                )
                androidx.compose.material3.Text(
                    text = ":",
                    style = TextStyles.smallMediumNormalTextStyle
                )
                androidx.compose.material3.Text(
                    text = " $sku",
                    style = TextStyles.smallMediumNormalTextStyle
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .height(24.dp)
            ){
                androidx.compose.material3.Text(
                    text = "Qty",
                    style = TextStyles.smallMediumNormalTextStyle,
                    modifier = Modifier
                        .width(100.dp)
                )
                androidx.compose.material3.Text(
                    text = ":",
                    style = TextStyles.smallMediumNormalTextStyle
                )
                androidx.compose.material3.Text(
                    text = " $qty $uom",
                    style = TextStyles.smallMediumNormalTextStyle
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .height(24.dp)
            ){
                androidx.compose.material3.Text(
                    text = "Status",
                    style = TextStyles.smallMediumNormalTextStyle,
                    modifier = Modifier
                        .width(100.dp)
                )
                androidx.compose.material3.Text(
                    text = ":",
                    style = TextStyles.smallMediumNormalTextStyle
                )
//                Text(
//                    text = " $status",
//                    style = TextStyles.smallMediumNormalTextStyle
//                )
                androidx.compose.material3.Text(
                    text = when (status.toIntOrNull()) {
                        0 -> " Inactive"
                        1 -> " Active"
                        2 -> " Quarantine Pending"
                        3 -> " Quarantined"
                        4 -> " Rejection Pending"
                        5 -> " Rejected"
                        6 -> " Consumed"
                        else -> " Unknown"
                    },
                    style = TextStyles.smallMediumNormalTextStyle
                )
            }


            Row(
                modifier = Modifier
                    .padding(16.dp,8.dp)
            ){
                DashedLine()
            }

            Row(
                modifier = Modifier
                    .padding(16.dp, 0.dp)
                    .height(24.dp)
            ){
                androidx.compose.material3.Text(
                    text = "Desc",
                    style = TextStyles.smallLightColoredNormalTextStyle,
                    modifier = Modifier
                        .width(100.dp)
                )
                androidx.compose.material3.Text(
                    text = ":",
                    style = TextStyles.smallLightColoredNormalTextStyle,
                )
                androidx.compose.material3.Text(
                    text = " $description",
                    style = TextStyles.smallLightColoredNormalTextStyle,
                    modifier = Modifier
                        .width(200.dp)
                )
            }


//
//                Row(
//                    modifier = Modifier
//                        .padding(16.dp,0.dp)
//                        .height(24.dp)
//                ){
//                BasicText(
//                    text = "Suid:",
//                    modifier = Modifier.width(80.dp),
//                    style = TextStyles.smallMediumNormalTextStyle.copy(fontSize = 14.sp)
//                )
//                BasicText(
//                    text = suid,
//                    style = TextStyles.smallMediumNormalTextStyle.copy(fontSize = 14.sp)
//                )
//            }

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp)
//            ) {
//                BasicText(
//                    text = "SKU:",
//                    modifier = Modifier.width(80.dp),
//                    style = TextStyles.smallMediumNormalTextStyle.copy(fontSize = 14.sp)
//                )
//                BasicText(
//                    text = sku,
//                    style = TextStyles.smallMediumNormalTextStyle.copy(fontSize = 14.sp)
//                )
//            }

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp)
//            ) {
//                BasicText(
//                    text = "Qty:",
//                    modifier = Modifier.width(80.dp),
//                    style = TextStyles.smallMediumNormalTextStyle.copy(fontSize = 14.sp)
//                )
//                BasicText(
//                    text = qty + " NOS",
//                    style = TextStyles.smallMediumNormalTextStyle.copy(fontSize = 14.sp)
//                )
//            }

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp)
//            ) {
//                BasicText(
//                    text = "Status:",
//                    modifier = Modifier.width(80.dp),
//                    style = TextStyles.smallMediumNormalTextStyle.copy(fontSize = 14.sp)
//                )
//                BasicText(
//                    text = when (status.toIntOrNull()) {
//                        0 -> "Inactive SUID"
//                        1 -> "Active SUID"
//                        2 -> "Quarantined"
//                        3 -> "Rejected"
//                        4 -> "Consumed"
//                        else -> "Unknown"
//                    },
//                    style = TextStyle(fontSize = 14.sp)
//                )
//            }

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp)
//            ) {
//                DashedLine()
//            }
//
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp)
//            ) {
//                Text(
//                    text = description,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 5.dp),
//                    style = TextStyles.smallMediumNormalTextStyle.copy(color = Color.Red, fontSize = 14.sp),
//                    textAlign = TextAlign.Left
//                )
//            }

        }
    }
}

@Composable
fun SearchView(
    query: String,
    hint: String,
    onQueryChanged: (String) -> Unit,
    onClearQuery: () -> Unit
) {
    var isTyping by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        color = Color.White,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                BasicTextField(
                    value = query,
                    onValueChange = {
                        onQueryChanged(it)
                        isTyping = it.isNotEmpty()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.Transparent),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (query.isEmpty()) {
                            androidx.compose.material3.Text(
                                text = hint,
                                style = TextStyles.smallNormalGreyTextStyle,
                                modifier = Modifier
                                    .size(20.dp)
                                    .padding(top = 3.dp)
                                    .align(alignment = Alignment.CenterVertically),
                                color = Color.Gray
                            )
                        }
                        innerTextField()
                    }
                )
                if (isTyping) {
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = {
                        onClearQuery()
                        onQueryChanged("") // Clears the text
                        isTyping = false
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Clear",
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}
//buttons
@Composable
fun BasicBottomButton(title : String,onClick: () -> Unit){
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(1f)
            .padding(horizontal = 12.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(primaryColor)
    ) {
        Text(
            text = title,
            color = Color.White
        )
    }
}