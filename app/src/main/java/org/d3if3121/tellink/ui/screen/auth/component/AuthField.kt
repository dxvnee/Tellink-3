package org.d3if3121.tellink.ui.screen.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.theme.Warna


@Composable
fun AuthField(
    text: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(
        modifier = modifier
    ){
        Text(
            text = text,
            color = Warna.MerahNormal,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 6.dp)
        )

        content()
    }

}