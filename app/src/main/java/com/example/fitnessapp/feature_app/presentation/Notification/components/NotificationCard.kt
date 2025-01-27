package com.example.fitnessapp.feature_app.presentation.Notification.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.fitnessapp.ui.theme._00FF66
import com.example.fitnessapp.ui.theme._C6C4D4
import com.example.fitnessapp.ui.theme.montserrat40010_B6B4C2
import com.example.fitnessapp.ui.theme.montserrat50012_1D1617

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    NotificationCard(
        "https://www.researchgate.net/profile/Toh-Ban-Hock/publication/306312876/figure/fig1/AS:396912160198660@1471642641241/Autoimmune-gastritis-affects-fundus-spares-antrum.png",
        "qweqwe", "as"
    ) { }
}

@Composable
fun NotificationCard(
    image: String,
    title: String,
    data: String,
    modifier: Modifier = Modifier,
    moreClick: () -> Unit
) {

    Column(modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(_00FF66, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Text(
                    text = title,
                    style = montserrat50012_1D1617
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = data,
                    style = montserrat40010_B6B4C2
                )
            }
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = moreClick,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Transparent, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more info"
                )
            }
        }
        Spacer(Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(_C6C4D4)
        )
        Spacer(Modifier.height(15.dp))
    }
}