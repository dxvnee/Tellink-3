package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import org.d3if3121.tellink.components.LoadingIndicator
import org.d3if3121.tellink.ui.animation.AnimationFade
import org.d3if3121.tellink.ui.animation.AnimationFadeSpring
import org.d3if3121.tellink.ui.screen.content.homepage.HomeViewModel
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
                TeksBold(textJudul, Modifier.padding(bottom = 8.dp), TextAlign.Center)
                TeksNormal(textDialog,  Modifier.padding(bottom = 12.dp), TextAlign.Center)

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
           LoadingIndicatorBox()
        }
    }
}

@Composable
fun LoadingIndicatorBox(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LoadingIndicatorText()
    }
}

@Composable
fun LoadingIndicatorText(){
    LoadingIndicator()
    TeksNormal("Loading..", Modifier.offset(y = 32.dp), TextAlign.Center, size = 12.sp)
}

@Composable
fun DialogGambar(homeViewModel: HomeViewModel){

    val dialogGambar  by homeViewModel.gambarDialog.collectAsState()
    val gambar by homeViewModel.gambarString.collectAsState()

    ChangeNavColor(dialogGambar)

    BoxDepan {
        AnimationFade(visible = dialogGambar) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { homeViewModel.gambarChange(false) }

                    .background(Warna.HitamNormal.copy(alpha = 0.4f))
            )
        }

        AnimationFadeSpring(visible = dialogGambar) {
            ColumnCenter(Modifier.fillMaxSize()) {
                AsyncImage(
                    model = gambar,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { homeViewModel.gambarChange(false) }
                )
            }
        }
    }
}

