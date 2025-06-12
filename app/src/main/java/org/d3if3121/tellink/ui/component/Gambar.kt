package org.d3if3121.tellink.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import org.d3if3121.tellink.components.LoadingIndicator
import org.d3if3121.tellink.ui.screen.content.homepage.HomeViewModel
import org.jetbrains.annotations.Async

@Composable
fun Gambar(
    painter: Painter,
    size: Dp = 52.dp,
    modifier: Modifier = Modifier
){
    Image(
        painter = painter,
        contentDescription = "App logo",
        modifier = modifier.size(size)
    )
}

@Composable
fun AsyncGambar(
    gambar: String?,
    homeViewModel: HomeViewModel
){
   gambar?.let {
        if (gambar.isNotEmpty()){
            AsyncGambarValue(gambar, homeViewModel)
        }
    }
}

@Composable
fun AsyncGambarValue(gambar: String, homeViewModel: HomeViewModel) {
    var state by remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f/9f)
            .clip(RoundedCornerShape(10.dp))
    ){
        AsyncImage(
            model = gambar,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onState = { state = it },
            modifier = Modifier
                .matchParentSize()
                .clickable {
                   homeViewModel.onDialogGambar(true, gambar)
                },
        )

        when(state){
            is AsyncImagePainter.State.Error -> {
                Icon(
                    imageVector = Icons.Default.BrokenImage,
                    contentDescription = "Error",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is AsyncImagePainter.State.Loading -> {
                ColumnCenter(modifier = Modifier.fillMaxSize()) {
                    LoadingIndicator()
                }

            }
            else -> Unit
        }
    }
}
