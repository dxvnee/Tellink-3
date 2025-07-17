package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.d3if3121.tellink.ui.screen.content.projectpage.projectaddpage.ProjectAddPageContent
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun ColumnPadding(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Column(modifier = modifier.padding(16.dp)){ content() }
}

@Composable
fun ColumnPaddingLazy(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Column(modifier = modifier.padding(16.dp)){ LazyColumn { item { content() } } }
}

@Composable
fun ColumnPaddingKiriKanan(content: @Composable () -> Unit){
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)){ content() }
}

@Composable
fun BoxMaxWidth(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth().height(55.dp)) { content() }
}

@Composable
fun BoxFillMaxSize(content: @Composable () -> Unit){
    Box(modifier = Modifier.fillMaxSize().background(color = Warna.PutihNormal)){ content() }
}

@Composable
fun BoxWarna(color: Color, content: @Composable () -> Unit){
    Box(modifier = Modifier.background(color).fillMaxSize()){
        content()
    }
}

@Composable
fun BoxDepan(content: @Composable () -> Unit){
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
fun RowBottom(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = modifier
    ){
        content()
    }
}

@Composable
fun ColumnEnd(modifier: Modifier = Modifier, content: @Composable () -> Unit){
    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier.fillMaxWidth()
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
