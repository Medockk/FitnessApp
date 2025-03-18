package com.example.fitnessapp.feature_app.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._95ADFE4D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70016White

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    CustomGreenButton("qweqwe") { }
}

@Composable
fun CustomGreenButton(
    text: String,
    isSignInScreen: Boolean = false,
    isNextButton: Boolean = false,
    enabled: Boolean = true,
    tag: String = "btn",
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val brush = Brush.linearGradient(
        listOf(
            _228F7D,
            _9CEEDF
        )
    )

    Button(
        onClick = onClick,
        modifier = modifier
            .background(brush, RoundedCornerShape(99.dp))
            .shadow(10.dp, RoundedCornerShape(99.dp), ambientColor = _95ADFE4D)
            .testTag(tag),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        enabled = enabled,
        shape = RoundedCornerShape(99.dp),

    ) {
        Row {
            if (isSignInScreen){
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.sign_in_icon),
                    contentDescription = "sign in",
                    tint = Color.White
                )
            }
            Text(
                text = text,
                style = montserrat70016White
            )
            if (isNextButton){
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "next",
                    tint = Color.White
                )
            }
        }
    }
}