package com.example.fitnessapp.feature_app.presentation.StartWorkout.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.common.extensions.isNotNull
import com.example.fitnessapp.R
import com.example.fitnessapp.feature_app.presentation.ui.theme._C6C4D4
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat40010_A5A3B0
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50024_B6B4C2
import kotlin.math.abs

@Composable
fun CustomUserWorkoutRepeats(
    initialValue: Int,
    height: Dp,
    range: Int = 60,
    spaceBetweenItems: Dp = 15.dp,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
) {

    val context = LocalContext.current
    val state = rememberLazyListState(initialFirstVisibleItemIndex = initialValue)
    val caloriesList = remember {
        mutableStateOf(mutableListOf<String>().apply {
            (1..range).forEach { add("${it * 25} Калорий") }
        })
    }
    val minuteList = remember {
        mutableStateOf(mutableListOf<String>().apply {
            (1..range).forEach { add("$it") }
        })
    }
    val selectedValue = remember { mutableStateOf(initialValue) }
    val offset = remember { derivedStateOf { state.firstVisibleItemScrollOffset } }

    Box(
        modifier = Modifier
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = state,
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spaceBetweenItems),
            contentPadding = PaddingValues(horizontal = 25.dp)
        ) {
            itemsIndexed(caloriesList.value.zip(minuteList.value)) { index, value ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.calories_icon),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .graphicsLayer(
                                scaleY = calculateScaleY(index, state),
                                scaleX = calculateScaleX(index, state),
                                alpha = calculateAlpha(index, state)
                            )
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = value.first,
                        style = montserrat40010_A5A3B0,
                        modifier = Modifier
                            .graphicsLayer(
                                alpha = calculateAlpha(index, state),
                                scaleY = calculateScaleY(index, state),
                                scaleX = calculateScaleX(index, state)
                            )
                    )
                    Spacer(Modifier.width(30.dp))
                    Text(
                        text = value.second + if (calculateAlpha(
                                index,
                                state
                            ) >= 0.7f
                        ) " мин" else "",
                        style = montserrat50024_B6B4C2,
                        modifier = Modifier
                            .graphicsLayer(
                                alpha = calculateAlpha(index, state),
                                scaleY = calculateScaleY(index, state),
                                scaleX = calculateScaleX(index, state)
                            )
                    )
                }
            }
        }

        Border(height / 2)
    }

    LaunchedEffect(key1 = state.isScrollInProgress) {
        if (!state.isScrollInProgress && state.firstVisibleItemScrollOffset.pixelToDp(context) % spaceBetweenItems.value != 0f) {
            state.animateScrollToItem(state.scrollToItem(context))
        }
    }

    LaunchedEffect(key1 = offset.value) {
        val newValue = minuteList.value[(state.scrollToItem(context) + 3 / 2)].toIntOrNull()

        if (newValue.isNotNull() && newValue != selectedValue.value) {
            onValueChange(newValue.toString())
            selectedValue.value = newValue!!
        }
    }
}

private fun LazyListState.scrollToItem(context: Context): Int {
    val offset = firstVisibleItemScrollOffset.pixelToDp(context)
    return when {
        offset == 0f -> firstVisibleItemIndex
        offset % 15 >= 10f / 2 -> firstVisibleItemIndex + 1
        else -> firstVisibleItemIndex
    }
}

private fun Int.pixelToDp(context: Context): Float {
    val density = context.resources.displayMetrics.densityDpi
    return this / (density / 160f)
}

@Composable
private fun Border(height: Dp) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(_C6C4D4)
        )
        Spacer(Modifier.weight(1f))
        Box(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(_C6C4D4)
        )
    }
}

private fun calculateAlpha(index: Int, lazyListState: LazyListState): Float {
    val layoutInfo = lazyListState.layoutInfo

    val visibleItems = layoutInfo.visibleItemsInfo.map { it.index }
    if (visibleItems.isEmpty()) return 1f
    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f

    val abs = abs((itemInfo.offset + itemInfo.size / 2) - center)
    return 1f - (abs / (layoutInfo.viewportEndOffset / 2))
}

private fun calculateScaleY(index: Int, lazyListState: LazyListState): Float {
    val layoutInfo = lazyListState.layoutInfo

    val visibleItems = layoutInfo.visibleItemsInfo.map { it.index }
    if (!visibleItems.contains(index)) return 1f

    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f

    val abs = abs((itemInfo.offset + itemInfo.size / 2) - center)
    return 1f - (abs / (layoutInfo.viewportEndOffset / 2))
}

private fun calculateScaleX(index: Int, lazyListState: LazyListState): Float {
    val layoutInfo = lazyListState.layoutInfo

    val visibleItems = layoutInfo.visibleItemsInfo.map { it.index }
    if (!visibleItems.contains(index)) return 1f

    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f

    val abs = abs((itemInfo.offset + itemInfo.size / 2) - center)
    return 1f - (abs / (layoutInfo.viewportEndOffset / 2)) * 0.5f
}