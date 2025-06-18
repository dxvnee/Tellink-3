package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.theme.CustomButtonColors
import org.d3if3121.tellink.ui.theme.Warna

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
fun ButtonRequest(
    active: Boolean,
    text: String
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(7.dp))
            .width(110.dp).height(33.dp)
            .background(Warna.MerahNormal)
            .clickable {

            }
    ){
        TeksBoldTombol(text, size = 14)
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
fun IconWithText(
    imageVector: ImageVector,
    text: String,
    size: Dp = 32.dp,
    offset: Dp = 0.dp,
    paddingStart: Dp = 2.dp,
){
    Row(verticalAlignment = Alignment.CenterVertically){
        Box(
            modifier = Modifier.size(size).offset(y = offset)
                .clickable {  }
        ){
            Icon(
                imageVector = imageVector,
                contentDescription = "Star",
                tint = Warna.HitamNormal,
                modifier = Modifier.size(size).clickable {  }
            )
        }
        TeksNormal(text, Modifier.fillMaxHeight().padding(start = paddingStart), size = 14.sp)
    }
}
@Composable
fun ButtonMerahDynamic(
    active: Boolean,
    onclick: () -> Unit,
    onclickcancel: () -> Unit,
    onrequestchange: (Boolean) -> Unit,
){
    RowStartCenter {
        IconWithText(
            imageVector = Icons.Filled.FavoriteBorder,
            text = "2.3k",
            size = 30.dp,
            paddingStart = 2.dp
        )

        SpaceWidth(15)

        IconWithText(
            imageVector = Icons.Outlined.ModeComment,
            text = "56",
            size = 27.dp,
            offset = 1.dp,
            paddingStart = 2.dp
        )

        SpaceWidth(15)

        IconWithText(
            imageVector = Icons.Filled.Repeat,
            text = "435",
            size = 30.dp,
            offset = (-0.6).dp,
            paddingStart = 0.dp
        )

        RowEnd(modifier = Modifier.fillMaxWidth()) {
            ButtonRequest(
                active = active,
                text = "Request!"
            )
        }
    }



//    ButtonMerah(
//        onClick = {
//            if (active) { onclickcancel(); onrequestchange(false) }
//            else { onclick(); onrequestchange(true) }
//        },
//        modifier = Modifier.width(100.dp).height(30.dp),
//        content = {
//            TeksBoldTombol(
//                text = if (active) stringResource(id = R.string.cancel) else stringResource(id = R.string.request),
//                color = if (active) Warna.MerahNormal else Warna.PutihNormal
//            )
//        },
//        colors = if (active) { whiteButtonColor() } else { redButtonColor() }
//    )

}
