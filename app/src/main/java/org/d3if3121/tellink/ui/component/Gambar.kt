package org.d3if3121.tellink.ui.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberAsyncImagePainter
import org.d3if3121.tellink.R
import org.d3if3121.tellink.components.LoadingIndicator
import org.d3if3121.tellink.ui.animation.AnimationFade
import org.d3if3121.tellink.ui.screen.content.component.ContentViewModel

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
fun <T> AsyncGambar(
    gambar: String?,
    viewModel: ContentViewModel<T>
){
   gambar?.let {
        if (gambar.isNotEmpty()){
            AsyncGambarValue(gambar, viewModel)
        }
   }
}

@Composable
fun <T> AsyncGambarValue(gambar: String, viewModel: ContentViewModel<T>) {
    SubcomposeImage(gambar = gambar) {
        viewModel.onDialogGambar(true, gambar)
    }
}

@Composable
fun SubcomposeImage(
    gambar: String,
    onClick: () -> Unit
){
    var success by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(10.dp))
    ){
        SubcomposeAsyncImage(
            model = gambar,
            contentDescription = "Gambar Content",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize().clickable { onClick() },
            loading = { ColumnCenter(modifier = Modifier.fillMaxSize()) { LoadingIndicator() } },
            error = {
                Icon(
                    imageVector = Icons.Default.BrokenImage,
                    contentDescription = "Error",
                    modifier = Modifier.align(Alignment.Center)
                )
            },
            success = {
                LaunchedEffect(Unit){ success = true }
                AnimationFade(success) { SubcomposeAsyncImageContent() }
            }
        )
    }
}

@Composable
fun AddProjectImage(
    imageUri: Uri?,
    onImageUri: (Uri?) -> Unit
){
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {
        uri: Uri? -> onImageUri(uri)
    }

    val painter = if(imageUri != null){ rememberAsyncImagePainter(imageUri) } else { painterResource(id = R.drawable.add_image_background) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = painter,
            contentDescription = "AddProjectImage",
            modifier = Modifier.matchParentSize().clickable { launcher.launch("image/*") },
            contentScale = ContentScale.Crop,
        )

        if(imageUri == null){
            ColumnCenter{
                Image(
                    painter = painterResource(id = R.drawable.add_image),
                    contentDescription = "AddProjectImage",
                    modifier = Modifier.size(85.dp)
                )
                TeksNormal("Add image...")
            }
        }
    }
}

