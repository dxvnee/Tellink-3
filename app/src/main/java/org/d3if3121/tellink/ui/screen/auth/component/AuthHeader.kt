package org.d3if3121.tellink.ui.screen.auth.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.ui.animation.heightAnimation
import org.d3if3121.tellink.ui.animation.scaleAnimation
import org.d3if3121.tellink.ui.component.TeksBold
import org.d3if3121.tellink.ui.component.TeksNormal
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun AuthHeader(
    text: String,
){
    val scale = scaleAnimation()
    val offsetY = heightAnimation()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 95.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App logo",
            modifier = Modifier
                .width(170.dp)
                .height(170.dp)
                .offset(y = offsetY.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
                .shadow(elevation = 45.dp, shape = CircleShape, ambientColor = Color.Red)
        )

        TeksBold(text = "Tellink", size = 26.sp, color = Warna.MerahNormal, modifier = Modifier.padding(bottom = 4.dp))
        TeksNormal(text = text, size = 18.sp, modifier = Modifier.padding(bottom = 12.dp))
    }
}