package com.example.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.ui.theme._F7F8F8
import com.example.fitnessapp.ui.theme.montserrat40012_ADA4A5

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    CustomTextField(
        "", {}, ImageVector.vectorResource(R.drawable.email_icon),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp, start = 30.dp, end = 30.dp)
    )
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    hint: String = "",
    isPassword: Boolean = false,
    showHidePasswordState: Boolean = true,
    showHidePasswordClick: () -> Unit = {},
    enabled: Boolean = true,
    tag: String = "tag",
    modifier: Modifier = Modifier
) {

    TextField(
        modifier = modifier
            .border(1.dp, _F7F8F8, RoundedCornerShape(100.dp))
            .testTag(tag),
        value = value,
        enabled = enabled,
        onValueChange = onValueChange,
        singleLine = true,
        label = {
            Text(
                text = hint,
                style = montserrat40012_ADA4A5
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(100.dp),
        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text),
        visualTransformation = if (isPassword && !showHidePasswordState) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(
                    onClick = showHidePasswordClick,
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Transparent, CircleShape)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.hide_password_icon),
                        contentDescription = "show or hide password"
                    )
                }
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = _F7F8F8,
            unfocusedContainerColor = _F7F8F8,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledContainerColor = _F7F8F8,
            disabledIndicatorColor = Color.Transparent
        )
    )
}