package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.jetbrains.annotations.Async

@Composable
fun Gambar(
    painter: Painter,
    size: Dp = 52.dp
){
    Image(
        painter = painter,
        contentDescription = "App logo",
        modifier = Modifier.size(size)
    )
}

@Composable
fun AsyncGambar(
    gambar: String
){
    AsyncImage(
        model = gambar,
        contentDescription = "Project Image",
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)),
        contentScale = ContentScale.Crop,
    )
}