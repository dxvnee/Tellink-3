package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.ui.theme.Warna


@Composable
fun GarisHeight(){
    Box (Modifier.width(3.dp).fillMaxHeight().background(Warna.AbuMuda, RoundedCornerShape(20.dp)))
}

@Composable
fun GarisWidth(){
    Box (Modifier.width(20.dp).height(1.dp).background(Warna.AbuMuda, RoundedCornerShape(20.dp)))
}