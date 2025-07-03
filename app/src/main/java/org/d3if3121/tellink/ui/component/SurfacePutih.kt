package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun SurfacePutih(content: @Composable () -> Unit){
    Surface(modifier = Modifier.fillMaxSize(), color = Warna.PutihNormal){ content() }
}