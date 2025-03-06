package com.example.fitnessapp.feature_app.presentation.AddWorkoutSchedule.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.yml.charts.common.extensions.isNotNull
import com.example.fitnessapp.feature_app.presentation.ui.theme._F7F8F8
import com.example.fitnessapp.feature_app.presentation.ui.theme.montserrat50014_B6B4C2
import kotlin.math.abs

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

    Box(
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            state = lazyListState,
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            itemsIndexed(list.value) { index, value ->
                Text(
                    text = value,
                    style = montserrat50014_B6B4C2,
                    modifier = Modifier
                        .graphicsLayer(
                            scaleX = calculateScaleX(index, lazyListState),
                            scaleY = calculateScaleY(index, lazyListState),
                            alpha = calculateAlpha(index, lazyListState)
                        )
                )
            }
        }
    }
}

@Composable
fun Border(modifier: Modifier = Modifier, height: Dp = 30.dp) {
    Column(
        modifier = modifier
            .height(height)
    ) {
        Box(modifier.height(1.dp).background(_F7F8F8))
        Spacer(Modifier.weight(1f))
        Box(modifier.height(1.dp).background(_F7F8F8))
    }
}

/**
 * Convert integer to dp based on pixel density
 * @see Int
 * @see dp
 */
private fun Int.pixelToDp(context: Context): Float {
    val density = context.resources.displayMetrics.densityDpi
    return this / (density / 160f)
}

/**
 * if the first visible item's offset greater,
 * than the offset between the elements, than return the current element + 1
 */
private fun LazyListState.scrollTo(context: Context): Int {
    val offset = firstVisibleItemScrollOffset.pixelToDp(context)

    return when {
        offset == 0f -> firstVisibleItemIndex
        offset % 25 >= 25f / 2 -> firstVisibleItemIndex + 1
        else -> firstVisibleItemIndex
    }
}

private fun calculateAlpha(index: Int, lazyListState: LazyListState) : Float{

    val layoutInfo = lazyListState.layoutInfo
    val indexList = layoutInfo.visibleItemsInfo.map { it.index }
    if (indexList.isEmpty()) return 1f

    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f

    val abs = abs((itemInfo.offset + itemInfo.size/2) - center)
    return 1f - (abs / (layoutInfo.viewportEndOffset/2)) * 0.5f
}

private fun calculateScaleY(index: Int, lazyListState: LazyListState) : Float{

    val layoutInfo = lazyListState.layoutInfo
    val indexList = layoutInfo.visibleItemsInfo.map { it.index }
    if (!indexList.contains(index)) return 1f

    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f

    val abs = abs((itemInfo.offset + itemInfo.size/2) - center)
    return 1f - (abs / (layoutInfo.viewportEndOffset/2))
}

private fun calculateScaleX(index: Int, lazyListState: LazyListState) : Float{

    val layoutInfo = lazyListState.layoutInfo
    val indexList = layoutInfo.visibleItemsInfo.map { it.index }
    if (!indexList.contains(index)) return 1f

    val center = (layoutInfo.viewportEndOffset + layoutInfo.viewportStartOffset) / 2f
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return 1f

    val abs = abs((itemInfo.offset + itemInfo.size/2) - center)
    return 1f - (abs / (layoutInfo.viewportEndOffset/2)) * 0.5f
}