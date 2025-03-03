package com.example.fitnessapp.feature_app.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.feature_app.presentation.ui.theme._A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme._B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40012_B6B4C2

@Composable
fun CustomAlertCard(
    icon: ImageVector,
    title: String,
    description: String,
    isSwitch: Boolean = false,
    switchState: Boolean = false,
    onSwitchChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {

    Card(
        modifier = modifier,
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = _F7F8F8),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(15.dp),
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = _B6B4C2
            )
            Spacer(Modifier.width(10.dp))
            Text(
                text = title,
                style = montserrat40012_B6B4C2
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = description,
                style = montserrat40012_B6B4C2
            )
            Spacer(Modifier.width(2.dp))
             if (isSwitch){
                 CustomSwitch(
                     checked = switchState,
                     onCheckedChange = onSwitchChange
                 )
             }else{
                 Icon(
                     imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                     contentDescription = null,
                     tint = _A5A3B0
                 )
             }
        }
    }
}