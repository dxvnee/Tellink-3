package org.d3if3121.tellink.ui.screen.content.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.data.model.Project
import org.d3if3121.tellink.ui.component.UpToDateScreen
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun <T> MainLazyColumn(
    topContent: @Composable () -> Unit,
    mainContent: @Composable (T) -> Unit,
    list: List<T>?,
    lazyListState: LazyListState,
){
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Warna.PutihNormal),
        state = lazyListState
    ) {
        item {
            topContent()
        }
        list?.let {
            items(list) { item ->
                if(list.isEmpty()){ UpToDateScreen() } else { mainContent(item) }
            }
        }
    }
}
