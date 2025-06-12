package org.d3if3121.tellink.ui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.ui.component.topbar.cekScroll
import org.d3if3121.tellink.ui.component.topbar.scrollDirectionDetector



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
