package org.d3if3121.tellink.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun LoadingIndicator() {
    Box(contentAlignment = Alignment.Center){
        CircularProgressIndicator(
            color = Warna.MerahTua,
            strokeWidth = 6.dp,
            modifier = Modifier.offset(y = -10.dp).size(50.dp)
        )
    }
}