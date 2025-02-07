package com.example.fitnessapp.feature_app.presentation.Comparison.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme._228F7D
import com.example.fitnessapp.ui.theme._9CEEDF
import com.example.fitnessapp.ui.theme.montserrat40012White

@Composable
fun CustomDropDownMenu(
    expanded: Boolean,
    modifier: Modifier = Modifier,
    onDismissClick: () -> Unit,
    onClick: (String) -> Unit
) {
    val monthList = listOf(
        "Январь",
        "Февраль",
        "Март",
        "Апрель",
        "Май",
        "Июнь",
        "Июль",
        "Август",
        "Сентябрь",
        "Октябрь",
        "Ноябрь",
        "Декабрь",
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissClick,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Brush.linearGradient(listOf(_228F7D, _9CEEDF)), RoundedCornerShape(8.dp)),
    ) {
        Column {
            repeat(monthList.size){
                DropdownMenuItem(
                    text = {
                        Text(
                            text = monthList[it],
                            style = montserrat40012White
                        )
                    },
                    onClick = {
                        onClick(monthList[it])
                    },
                    contentPadding = PaddingValues(vertical = 5.dp)
                )
            }
        }
    }
}