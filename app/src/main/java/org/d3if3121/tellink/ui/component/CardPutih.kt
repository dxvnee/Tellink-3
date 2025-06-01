package org.d3if3121.tellink.ui.component

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun CardPutih(
    height: Dp,
    modifier: Modifier = Modifier,
    center: Boolean = false,
    content: @Composable () -> Unit
){
    Card(
        modifier = modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            .height(height),
        colors = CardDefaults.cardColors(containerColor = Warna.PutihNormal),
        elevation = CardDefaults.cardElevation(20.dp),
        shape = RoundedCornerShape(15.dp)
    ){
        Column(
            verticalArrangement = if(center) Arrangement.Center else Arrangement.Top,
            horizontalAlignment = if(center) Alignment.CenterHorizontally else Alignment.Start,
            modifier = Modifier
                .padding(start = 24.dp, top = 23.dp, end = 24.dp, bottom = 23.dp)
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            content()
        }
    }
}