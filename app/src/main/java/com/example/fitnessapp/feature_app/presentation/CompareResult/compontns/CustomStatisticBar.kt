package com.example.fitnessapp.feature_app.presentation.CompareResult.compontns

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.feature_app.domain.model.StatisticData
import com.example.fitnessapp.ui.theme._00F0FF
import com.example.fitnessapp.ui.theme._00FF66
import com.example.fitnessapp.ui.theme._FF0000
import com.example.fitnessapp.ui.theme.montserrat40012_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat40014_1D1617

@Composable
fun CustomStatisticBar(
    statisticData: StatisticData,
    modifier: Modifier = Modifier
) {
    val percent = statisticData.value.toFloat() / 100

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = statisticData.type,
            style = montserrat40014_1D1617
        )
        Spacer(Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = (percent * 100).toString()[0].toString()+(percent * 100).toString()[1].toString(),
                style = montserrat40012_B6B4C2
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
            ) {
                Box(
                    Modifier
                        .fillMaxWidth(percent)
                        .height(15.dp)
                        .alpha(0.7f)
                        .background(
                            _FF0000,
                            RoundedCornerShape(topStart = 99.dp, bottomStart = 99.dp)
                        )
                )
                Box(
                    Modifier
                        .weight(1f)
                        .height(15.dp)
                        .background(
                            Brush.linearGradient(listOf(_00F0FF, _00FF66)),
                            RoundedCornerShape(topEnd = 99.dp, bottomEnd = 99.dp),

                        )
                )
            }
            Text(
                text = ((1f-percent) * 100).toString()[0].toString()+((1f-percent) * 100).toString()[1].toString(),
                style = montserrat40012_B6B4C2
            )
        }
    }
}