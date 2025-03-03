package com.example.fitnessapp.feature_app.presentation.OnBoard.componets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.OnBoard.OnBoardItem
import com.example.fitnessapp.feature_app.presentation.ui.theme._228F7D
import com.example.fitnessapp.feature_app.presentation.ui.theme._9CEEDF
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40014_B6B4C2
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat70024Bold_1D1617

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Prev() {
    OnBoardDefaultScreen(
        OnBoardItem(
            R.drawable.onboard_screen1,
            "title1",
            description = "descr2"
        )
    ){

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OnBoardDefaultScreen(
    onBoardItem: OnBoardItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val brush = Brush.horizontalGradient(
        listOf(
            _228F7D,
            _9CEEDF
        )
    )
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClick,
                modifier = Modifier
                    .background(brush, CircleShape)
                    .testTag("btnNext"),
                shape = CircleShape,
                containerColor = Color.Unspecified,
                elevation = FloatingActionButtonDefaults.elevation(0.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    ) {
        Column(
            modifier = modifier,
        ) {
            Image(
                painter = painterResource(onBoardItem.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = onBoardItem.title,
                    style = montserrat70024Bold_1D1617
                )
                Spacer(Modifier.height(15.dp))
                Text(
                    text = onBoardItem.description,
                    style = montserrat40014_B6B4C2
                )
            }
        }
    }
}