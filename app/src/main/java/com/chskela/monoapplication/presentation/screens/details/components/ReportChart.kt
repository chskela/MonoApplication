package com.chskela.monoapplication.presentation.screens.details.components

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chskela.monoapplication.presentation.ui.theme.Expense
import com.chskela.monoapplication.presentation.ui.theme.Income
import com.chskela.monoapplication.presentation.ui.theme.MonoApplicationTheme
import android.graphics.Color as AGColor
import android.graphics.Paint as AGPaint
import android.graphics.Path as AGPath

@Composable
fun ReportChart(
    modifier: Modifier = Modifier,
    reportsList: List<ReportUi> = emptyList(),
    graphColor: Color = Expense,
    textColor: Color = MaterialTheme.colorScheme.secondary,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge
) {
    val spacing = 50f
    val transparentGraphColor = remember {
        graphColor.copy(alpha = 0.5f)
    }
    val upperValue = remember(reportsList) {
        reportsList.maxOfOrNull { it.amount } ?: 0
    }
    val lowerValue = remember(reportsList) {
        reportsList.minOfOrNull { it.amount } ?: 0
    }

    val density = LocalDensity.current
    val textPaint = remember(density) {
        AGPaint().apply {
            color = AGColor.rgb(
                textColor.red,
                textColor.green,
                textColor.blue
            )
            textAlign = AGPaint.Align.CENTER
            textSize = density.run { textStyle.fontSize.toPx() }
        }
    }

    Canvas(modifier = modifier.fillMaxWidth()) {
        val height = size.height - spacing
        val spacePerX = size.width / (reportsList.size - 1)
        val ratioY = height / (upperValue - lowerValue)

        reportsList.forEachIndexed { i, report ->
            if (i == 0 || i == reportsList.lastIndex) return@forEachIndexed
            drawContext.canvas.nativeCanvas.apply {
                drawText(
                    report.signatures,
                    i * spacePerX,
                    size.height - 10,
                    textPaint
                )
            }
        }

        var lastX = 0f
        val strokePath = Path().apply {
            reportsList.forEachIndexed { i, report ->
                val x1 = spacePerX * i
                val y1 = height - (report.amount - lowerValue) * ratioY - spacing

                val nextReport = reportsList.getOrNull(i + 1) ?: reportsList.last()
                val x2 = spacePerX * (i + 1)
                val y2 = height - (nextReport.amount - lowerValue) * ratioY - spacing

                if (i == 0) {
                    moveTo(x1, y1)
                }
                lastX = (x1 + x2) / 2f
                quadraticBezierTo(
                    x1, y1, (x1 + x2) / 2f, (y1 + y2) / 2f
                )
            }
        }

        val fillPath = AGPath(strokePath.asAndroidPath())
            .asComposePath()
            .apply {
                lineTo(lastX, size.height - spacing)
                lineTo(spacing, size.height - spacing)
                close()
            }

        if (reportsList.isNotEmpty()) {
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        transparentGraphColor,
                        Color.Transparent
                    ),
                    endY = size.height - spacing
                )
            )

            drawPath(
                path = strokePath,
                color = graphColor,
                style = Stroke(
                    width = 2.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
    }
}

data class ReportUi(
    val signatures: String,
    val amount: Long,
)

@Preview(showBackground = true, name = "Light ReportChart", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewReportChart() {
    MonoApplicationTheme {
        ReportChart(
            Modifier.size(width = 300.dp, height = 150.dp),
            reportsList = listOf(
                ReportUi(signatures = "Oct", amount = 2),
                ReportUi(signatures = "Nov", amount = 10),
                ReportUi(signatures = "Dec", amount = 4),
                ReportUi(signatures = "Oct", amount = 1),
                ReportUi(signatures = "Nov", amount = 14),
                ReportUi(signatures = "Dec", amount = 7)
            )
        )
    }
}

@Preview(showBackground = true, name = "Light ReportChartIncome", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewReportChartIncome() {
    MonoApplicationTheme {
        ReportChart(
            Modifier
                .fillMaxWidth()
                .height(124.dp),
            reportsList = listOf(
                ReportUi(signatures = "Oct", amount = 2),
                ReportUi(signatures = "Nov", amount = 10),
                ReportUi(signatures = "Dec", amount = 4),
                ReportUi(signatures = "Oct", amount = 1),
                ReportUi(signatures = "Nov", amount = 14),
                ReportUi(signatures = "Dec", amount = 7)
            ),
            graphColor = Income
        )
    }
}

@Preview(showBackground = true, name = "Light ReportChartIncome", showSystemUi = false)
@Preview(showBackground = true, showSystemUi = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewReportChartIncomeEmpty() {
    MonoApplicationTheme {
        ReportChart(
            Modifier
                .fillMaxWidth()
                .height(124.dp),
            reportsList = listOf(),
            graphColor = Income
        )
    }
}
