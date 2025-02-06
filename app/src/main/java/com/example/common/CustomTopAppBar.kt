package com.example.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fitnessapp.ui.theme.montserrat70016_1D1617

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    moreInformationClick: () -> Unit,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    backClick: () -> Unit
) {

    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        title = {
            Text(
                text = title,
                style = montserrat70016_1D1617,
                fontWeight = FontWeight.Bold,
                color = textColor,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(
                onClick = backClick,
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(32.dp)
                    .background(backgroundColor, RoundedCornerShape(8.dp))
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "back",
                    tint = Color.Unspecified
                )
            }
        },
        actions = {
            IconButton(
                onClick = moreInformationClick,
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(32.dp)
                    .background(backgroundColor, RoundedCornerShape(8.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more",
                    modifier = Modifier.rotate(270f)
                )
            }
        }
    )
}