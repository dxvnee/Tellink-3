package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.components.LoadingIndicator
import org.d3if3121.tellink.ui.animation.AnimationFade
import org.d3if3121.tellink.ui.animation.AnimationFadeSpring
import org.d3if3121.tellink.ui.theme.ChangeNavColor
import org.d3if3121.tellink.ui.theme.Warna

@Composable
fun DialogMessage(
    visible: Boolean,
    textJudul: String,
    textDialog: String,
    textTombol: String,
    onClick: () -> Unit,
){
    ChangeNavColor(visible)

    AnimationFade(visible = visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Warna.HitamNormal.copy(alpha = 0.4f))
        )
    }

    AnimationFadeSpring(visible = visible){
        ColumnCenter {
            CardPutih(
                height = 155.dp,
                center = true,
                modifier = Modifier.fillMaxWidth()
            ){
                TeksBold(text = textJudul, modifier = Modifier.padding(bottom = 8.dp))
                TeksNormal(text = textDialog,  modifier = Modifier.padding(bottom = 12.dp))

                ButtonMerah(
                    onClick = onClick,
                    content = { TeksBoldTombol(textTombol) },
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp).size(46.dp),
                )
            }
        }
    }
}

@Composable
fun DialogLoading(visible: Boolean){
    AnimationFade (visible = visible){
        CardPutih(
            height = 10.dp,
            center = true,
            modifier = Modifier.padding(top = 330.dp, bottom = 330.dp, start = 124.dp, end = 124.dp)
        ){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                LoadingIndicator()
                TeksNormal(text = "Loading..", modifier = Modifier.offset(y = 32.dp), size = 12.sp)
            }
        }
    }
}

