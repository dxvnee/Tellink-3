package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun ColumnPadding(content: @Composable () -> Unit){
    Column(modifier = Modifier.padding(16.dp)){ content() }
}

@Composable
fun ColumnPaddingKiriKanan(content: @Composable () -> Unit){
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)){ content() }
}



@Composable
fun BoxDepan(
    content: @Composable () -> Unit
){
    Box(modifier = Modifier.zIndex(1f)) {
        content()
    }
}
@Composable
fun ColumnCenter(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally,){ content() }
}

@Composable
fun RowStartCenter(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        content()
    }
}

@Composable
fun RowEnd(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = modifier
    ){
        content()
    }
}

@Composable
fun BorderBox(content: @Composable () -> Unit){
    Box(modifier = Modifier.border(1.dp, Warna.HitamNormal)){
        content()
    }
}
