package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Space(
    int: Int
){
    Spacer(modifier = Modifier.height(int.dp))
}

@Composable
fun SpaceWidth(
    int: Int
){
    Spacer(modifier = Modifier.width(int.dp))
}