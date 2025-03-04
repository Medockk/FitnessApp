package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import co.yml.charts.common.extensions.isNotNull
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_B6B4C2

@Composable
fun CustomTimeTicker(
    initialValue: Int,
    onValueChange: (Int) -> Unit,
    range: Int,
    visibleItems: Int = 3,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val lazyListState = rememberLazyListState(initialValue)
    val selectedValue = remember { mutableStateOf(initialValue) }
    val offset = remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }


    val list = remember {
        mutableStateOf(
            mutableListOf<String>().apply {
                (1..range).forEach { add(it.toString()) }
            }
        )
    }
    LaunchedEffect(key1 = offset.value) {
        val newValue = list.value[lazyListState.scrollTo(context) + visibleItems / 2].toIntOrNull()

        if (newValue.isNotNull() && newValue != selectedValue.value) {
            selectedValue.value = newValue!!
            onValueChange(newValue)
        }
    }

    LaunchedEffect(key1 = lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress && lazyListState.firstVisibleItemScrollOffset.pixelToDp(
                context
            ) % 25 != 0f
        ) {
            lazyListState.animateScrollToItem(lazyListState.scrollTo(context))
        }
    }

    LazyColumn(
        state = lazyListState,
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(list.value) {
            Text(
                text = it,
                style = montserrat50014_B6B4C2
            )
        }
    }
}

private fun Int.pixelToDp(context: Context): Float {
    val density = context.resources.displayMetrics.densityDpi
    return this / (density / 160f)
}

private fun LazyListState.scrollTo(context: Context): Int {
    val offset = firstVisibleItemScrollOffset.pixelToDp(context)

    return when {
        offset == 0f -> firstVisibleItemIndex
        offset % 25 >= 25f / 2 -> firstVisibleItemIndex + 1
        else -> firstVisibleItemIndex
    }
}