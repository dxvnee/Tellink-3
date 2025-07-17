package org.d3if3121.tellink.ui.animation

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.d3if3121.tellink.ui.component.topbar.cekScroll
import org.d3if3121.tellink.ui.component.topbar.cekScrollProject
import org.d3if3121.tellink.ui.component.topbar.scrollDirectionDetector
import org.d3if3121.tellink.ui.theme.Warna
import org.d3if3121.tellink.ui.theme.activeColors
import org.d3if3121.tellink.ui.theme.inactiveColors


@Composable
fun AnimationFadeSpring(
    visible: Boolean,
    content: @Composable () -> Unit
){
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(100)) + scaleIn(animationSpec = spring(
            dampingRatio = DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium
        )),
        exit = fadeOut(tween(200))
    ){ content() }
}


@Composable
fun AnimationFade(
    visible: Boolean,
    content: @Composable () -> Unit
){
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300))
    ){ content() }
}


@Composable
fun AnimationFadeZIndex(
    visible: Boolean,
    content: @Composable () -> Unit
){
    AnimatedVisibility(
        modifier = Modifier
            .zIndex(Float.MAX_VALUE)
            .fillMaxWidth()
            .height(55.dp)
            .background(Warna.PutihNormal),
        visible = visible,
        enter = fadeIn(tween(300)),
        exit = fadeOut(tween(300))
    ){ content() }
}

@Composable
fun AnimationPairButtonColor2(condition: Boolean): Pair<Color, Float>{
    val backgroundColor2 by animateColorAsState(
        targetValue = if (condition) activeColors.containerColor
        else inactiveColors.containerColor,
        animationSpec = tween(durationMillis = 300),
        label = "btn2Color"
    )
    val fontWeight2 by animateFloatAsState(
        targetValue = if (condition) FontWeight.Bold.weight.toFloat() else FontWeight.Normal.weight.toFloat(),
        animationSpec = tween(300),
        label = "font2"
    )

    return Pair(backgroundColor2, fontWeight2)
}

@Composable
fun AnimationPairButtonColor1(condition: Boolean): Pair<Color, Float>{
    val backgroundColor1 by animateColorAsState(
        targetValue = if (!condition) activeColors.containerColor
        else inactiveColors.containerColor,
        animationSpec = tween(durationMillis = 300),
        label = "btn1Color"
    )
    val fontWeight1 by animateFloatAsState(
        targetValue = if (!condition) FontWeight.Bold.weight.toFloat() else FontWeight.Normal.weight.toFloat(),
        animationSpec = tween(300),
        label = "font1"
    )

    return Pair(backgroundColor1, fontWeight1)
}


@Composable
fun AnimationAppear(
    visible: Boolean,
    content: @Composable () -> Unit
){
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(1)),
        exit = fadeOut(tween(1))
    ){ content() }
}

fun animationFadeScaleIn(): EnterTransition {
    return fadeIn(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + scaleIn(
        initialScale = 0.985f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )
}

fun animationFadeScaleOut(): ExitTransition {
    return fadeOut(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + scaleOut(
        targetScale = 1.025f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = FastOutSlowInEasing
        )
    )
}

@Composable
fun scaleAnimation(
    initialScale: Float = 1f,
    targetScale: Float = 1.04f,
    durationMillis: Int = 2000,
): Float {
    val scale by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = initialScale,
        targetValue = targetScale,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = FastOutSlowInEasing ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scaleAnim"
    )
    return scale
}

@Composable
fun heightAnimation(
    initialScale: Float = 0f,
    targetScale: Float = -3f,
    durationMillis: Int = 2000,
): Float {

    val offset by rememberInfiniteTransition().animateFloat(
        initialValue = initialScale,
        targetValue = targetScale,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    return offset
}

@Composable
fun topBarHeightAnimation(
    lazyListState: LazyListState,
    isAlpha: Boolean,
): State<Dp> {
    val isVisible = !cekScroll(lazyListState) || scrollDirectionDetector(lazyListState) == "Up"

    val targetHeight = if (isVisible) 64.dp else if(!isAlpha) 53.dp else 0.dp
    return animateDpAsState(
        targetValue = targetHeight,
        animationSpec = tween(
            durationMillis = 70,
            easing = FastOutSlowInEasing
        ),
        label = "TopBarHeight"
    )
}

@Composable
fun contentHeightAnimation(
    lazyListState: LazyListState,
    isAlpha: Boolean,
): State<Dp> {
    val isVisible = !cekScrollProject(lazyListState)
    Log.d("Hehe3", isVisible.toString())

    val targetHeight = if (isVisible) 164.dp else if(!isAlpha) 53.dp else 0.dp
    return animateDpAsState(
        targetValue = targetHeight,
        animationSpec = tween(
            durationMillis = 70,
            easing = FastOutSlowInEasing
        ),
        label = "TopBarHeight"
    )
}

@Composable
fun rememberAlphaDone(
    alpha: Float,
    topBarHeight: Dp
): Boolean{
    var alphaDone by remember { mutableStateOf(false) }

    LaunchedEffect(alpha){
        alphaDone = alpha == 0f
    }
    return alphaDone
}

@Composable
fun topBarAlphaAnimation(
    topBarHeight: Dp,
): State<Float>{

    val alpha = if(topBarHeight > 54.dp) 1f else 0f
    return animateFloatAsState(
        targetValue = alpha,
        animationSpec = tween(
            durationMillis = 130,
            easing = FastOutSlowInEasing
        ),
        label = "AnimatedTopBarAlpha"
    )
}
