package com.mobillor.scanbinmodule.presentation.commonComposables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.SWMSTheme
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.lightWhite
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.primaryColorAccent
import com.mobillor.scanpalletmodule.presentation.commonComposables.theme.primaryColorLightAccent

class BasicShapes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SWMSTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SWMSTheme {
        Greeting3("Android")
    }
}

//box
@Composable
fun CurvedBoxWithDottedBorder(height: Dp,cornerRadius : Dp, content: @Composable () -> Unit) {



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .background(lightWhite)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val borderColor = primaryColorLightAccent
            val dotRadius = 6f
            val dotSpacing = 10f

            // Draw the dotted border
            drawDottedBorder(
                borderColor = borderColor,
                dotRadius = dotRadius,
                dotSpacing = dotSpacing,
                cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx()),
                topLeft = Offset(0f, 0f),
                size = Size(size.width, size.height)
            )
        }

        // Content inside the box
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            content()
        }
    }
}

private fun DrawScope.drawDottedBorder(
    borderColor: Color,
    dotRadius: Float,
    dotSpacing: Float,
    cornerRadius: CornerRadius,
    topLeft: Offset,
    size: Size
) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(dotSpacing, dotSpacing), 0f)

    drawRoundRect(
        color = borderColor,
        cornerRadius = cornerRadius,
        style = Stroke(width = 1.dp.toPx(), pathEffect = pathEffect),
        topLeft = topLeft,
        size = size
    )
}

//horivontal dotted line
@Composable
fun DashedLine() {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        Modifier
            .fillMaxWidth()
            .height(2.dp)) {

        drawLine(
            color = primaryColorAccent,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect
        )
    }

}

//vertical dotted line

fun Modifier.dottedBorder(
    color: Color,
    strokeWidth: Float = 1f,
    dotSize: Float = 5f,
    gapSize: Float = 5f,
    cornerRadius: Dp = 8.dp
) = this.then(
    Modifier.drawWithContent {
        drawContent()
        drawDottedBorder(color, strokeWidth, dotSize, gapSize)
    }
)
private fun DrawScope.drawDottedBorder(
    color: Color,
    strokeWidth: Float,
    dotSize: Float,
    gapSize: Float
) {
    val paint = Stroke(
        width = strokeWidth,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(dotSize, gapSize)),
        cap = StrokeCap.Round
    )

    val path = Path().apply {
        // Draw top border
        moveTo(0f, 0f)
        lineTo(size.width, 0f)
        // Draw right border
        lineTo(size.width, size.height)
        // Draw bottom border
        lineTo(0f, size.height)
        // Draw left border
        lineTo(0f, 0f)
    }

    drawPath(path, color, style = paint)
}
//vertical dotted line
@Composable
fun VerticalDashedLine(modifier: Modifier = Modifier) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Canvas(
        modifier
            .fillMaxHeight()
            .width(2.dp)
    ) {
        drawLine(
            color = primaryColorLightAccent,
            start = Offset(0f, 0f),
            end = Offset(0f, size.height),
            pathEffect = pathEffect
        )
    }
}
@Composable
fun VerticalDottedLine(
    lineHeight: Dp,
    dotRadius: Float = 4f,
    dotSpacing: Float = 8f,
    dotColor: Color = Color.Black
) {
    Canvas(modifier = Modifier
        .height(lineHeight)
        .width(2.dp)

    ) {
        val totalHeight = size.height
        var currentY = 0f

        while (currentY < totalHeight) {
            drawCircle(
                color = dotColor,
                radius = dotRadius,
                center = Offset(x = size.width / 2, y = currentY + dotRadius)
            )
            currentY += dotRadius * 2 + dotSpacing
        }
    }
}