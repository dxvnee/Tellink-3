package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.load.model.ModelLoaderFactory
import org.d3if3121.tellink.R
import org.d3if3121.tellink.ui.theme.CustomButtonColors
import org.d3if3121.tellink.ui.theme.Warna
import org.d3if3121.tellink.ui.theme.redButtonColor
import org.d3if3121.tellink.ui.theme.whiteButtonColor

@Composable
fun TombolGambar(
    painter: Painter,
    size: Int,
    onClick: () -> Unit,
){
    IconButton(onClick = onClick) {
        Image(
            painter = painter,
            contentDescription = "Chat logo",
            modifier = Modifier.size(size.dp)
        )
    }
}

@Composable
fun ButtonCommon(
    modifier: Modifier = Modifier.fillMaxWidth(),
    text: String,
    warna: Color = Warna.MerahNormal,
    onClick: () -> Unit,
){
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = warna),
        shape = RoundedCornerShape(7.dp),
        modifier = modifier
    ) {
        Text(text = text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun TombolTambah(
    onClick: () -> Unit
){
    Button(
        modifier = Modifier.size(49.dp).fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CustomButtonColors.PutihMerah,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.offset(-5.dp),
            text = "+",
            color = Warna.PutihNormal,
            fontSize = 26.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}


@Composable
fun ButtonMerah(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor =  Warna.MerahNormal,
        contentColor =  Warna.PutihNormal
    )
){
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        content = {
            content()
        },
        colors = colors
    )
}

@Composable
fun ButtonMerahDynamic(
    active: Boolean,
    onclick: () -> Unit,
    onclickcancel: () -> Unit,
    onrequestchange: (Boolean) -> Unit,
){
    ButtonMerah(
        onClick = {
            if (active) { onclickcancel(); onrequestchange(false) }
            else { onclick(); onrequestchange(true) }
        },
        modifier = Modifier.fillMaxWidth().size(46.dp),
        content = {
            TeksBoldTombol(
                text = if (active) stringResource(id = R.string.cancel) else stringResource(id = R.string.request),
                color = if (active) Warna.MerahNormal else Warna.PutihNormal
            )
        },
        colors = if (active) { whiteButtonColor() } else { redButtonColor() }
    )
}
