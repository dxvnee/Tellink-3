package org.d3if3121.tellink.ui.screen.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthContent(
    content: @Composable () -> Unit
){
    Column(modifier = Modifier.fillMaxSize()){
        content()
    }
}