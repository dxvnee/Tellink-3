package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun MainScaffold(
    content: @Composable () -> Unit,
    topbar: @Composable () -> Unit,
    bottombar: @Composable () -> Unit,
){
    Scaffold(
        topBar = { topbar() },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)){
                content()
            }
        },
        bottomBar = { bottombar() },
        contentColor = Warna.PutihNormal
    )
}
