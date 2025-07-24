package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.ui.theme.Warna
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import org.d3if3121.tellink.ui.theme.ChangeNavColorWhiteBottom


@Composable
fun BottomSheetNative(
    visible: Boolean,
    onDismiss: () -> Unit,
    collapse: Boolean = false,
    content: @Composable () -> Unit,
    secondContent: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val focusManager = LocalFocusManager.current


    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }
    val collapsedHeightPx = screenHeightPx * 0.67f
    val expandedHeightPx = screenHeightPx * 0.97f

    val offsetY = remember { Animatable(collapsedHeightPx) }
    var isExpanded by remember { mutableStateOf(false) }


    ChangeNavColorWhiteBottom(visible)

    if(visible) {
        Box(
            modifier = Modifier.fillMaxSize().background(Warna.HitamNormal.copy(alpha = 0.40f))
                .pointerInput(Unit) {
                    detectVerticalDragGestures(
                        onVerticalDrag = { change, dragAmount ->
                            change.consume()
                            scope.launch {
                                val newOffset =
                                    (offsetY.value - dragAmount).coerceIn(0f, screenHeightPx)
                                offsetY.snapTo(newOffset)
                                isExpanded = newOffset < collapsedHeightPx
                            }
                        },
                        onDragEnd = {
                            scope.launch {
                                if (offsetY.value > (collapsedHeightPx + expandedHeightPx) / 2f) {
                                    offsetY.animateTo(screenHeightPx)
                                    isExpanded = true
                                } else if (offsetY.value > (collapsedHeightPx) / 1.2f) {
                                    offsetY.animateTo(collapsedHeightPx, animationSpec = tween(220))
                                    isExpanded = false
                                } else if (offsetY.value < collapsedHeightPx) {
                                    offsetY.animateTo(0f, animationSpec = tween(220))
                                    onDismiss()
                                }
                            }
                        }
                    )
                }
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(with(density){ offsetY.value.toDp()})
                    .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
                    .background(Color.White)
                    .align(Alignment.BottomCenter)
                    .pointerInput(Unit) {}
            ){
                BottomSheetBox(
                    content = content,
                    secondContent = secondContent
                )
            }
        }
    }

    LaunchedEffect(isExpanded) { if (isExpanded) { focusManager.clearFocus() } }

    LaunchedEffect(visible) {
        if(visible){
            offsetY.snapTo(0f)
            offsetY.animateTo(collapsedHeightPx, animationSpec = tween(350, easing = FastOutSlowInEasing))
        }
    }

    LaunchedEffect(collapse) {
        if (collapse) {
            scope.launch {
                offsetY.animateTo(collapsedHeightPx, animationSpec = tween(220)) // expand ke atas penuh
                isExpanded = false
            }
        }
    }
}


@Composable
fun BottomSheetBox(
    content: @Composable () -> Unit,
    secondContent: @Composable () -> Unit
){
    Column(Modifier.padding(horizontal = 16.dp, vertical = 10.dp).imePadding()) {

        TopBottomSheet(Modifier.align(Alignment.CenterHorizontally))

        Space(10)

        Column(Modifier.fillMaxWidth().weight(1f)) { content() }

        secondContent()
    }
}


@Composable
fun TopBottomSheet(
    modifier: Modifier
){
    Box(
        modifier
            .padding(vertical = 8.dp)
            .size(width = 40.dp, height = 4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.LightGray)
    )
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) { TeksBold("Comments") }
    Space(10)
}







