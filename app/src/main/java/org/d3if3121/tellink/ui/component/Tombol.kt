package org.d3if3121.tellink.ui.component

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.ui.animation.AnimatedContent
import org.d3if3121.tellink.ui.screen.auth.component.TeksSwitchPageWithIcon
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
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .size(38.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Warna.MerahNormal)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ){
        TeksBoldTombol("+")
    }

}

@Composable
fun ButtonRequest(
    text: String,
    width: Dp = 110.dp,
    height: Dp = 33.dp,
    roundedCornerShape: Int = 7,
    onClick: () -> Unit,
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(roundedCornerShape.dp))
            .width(width).height(height)
            .background(Warna.MerahNormal)
            .clickable { onClick() }
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
    warna: Color = Warna.HitamNormal,
    onClickIcon: () -> Unit,
    onClickText: () -> Unit = onClickIcon
){
    Row(verticalAlignment = Alignment.CenterVertically){
        IconTombol(onClick = onClickIcon, imageVector = imageVector, size = size, offset = offset, warna = warna)
        TeksNormal(text, Modifier.fillMaxHeight().padding(start = paddingStart), size = 14.sp, onClick = onClickText)
    }
}

@Composable
fun IconTombol(
    imageVector: ImageVector,
    size: Dp,
    onClick: () -> Unit,
    offset: Dp = 0.dp,
    warna: Color = Warna.HitamNormal
){
    ColumnCenter(modifier = Modifier.fillMaxHeight()){
        Box(modifier = Modifier.size(size).offset(y = offset).clickable { onClick() }){
            Icon(
                imageVector = imageVector,
                contentDescription = "Star",
                tint = warna,
                modifier = Modifier.size(size)
            )
        }
    }
}

@Composable
fun IconNormalWithBox(
    imageVector: ImageVector,
    size: Dp,
    color: Color,
    colorIcon: Color,
    offset: Dp = 0.dp
){
    Box(
        modifier = Modifier.size(size + 2.dp).clip(RoundedCornerShape(4.dp)).background(color),
        contentAlignment = Alignment.Center
    ){
        IconNormal(
            imageVector = imageVector,
            size = size,
            color = colorIcon,
            offset = offset
        )
    }
}
@Composable
fun IconNormal(
    imageVector: ImageVector,
    color: Color,
    size: Dp,
    offset: Dp = 0.dp
){
    ColumnCenter(modifier = Modifier.fillMaxHeight()){
        Box(modifier = Modifier.size(size).offset(y = offset)){
            Icon(
                imageVector = imageVector,
                contentDescription = "Star",
                tint = color,
                modifier = Modifier.size(size)
            )
        }
    }
}

@Composable
fun FeedBottomComponent(
    project: Project,
    onLikeClick: () -> Unit,
    onLikeTextClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,

    content: @Composable () -> Unit = {}
){
    var isLiked by remember { mutableStateOf(project.isLiked) }
    var likeCount by remember { mutableStateOf(project.likes) }

    RowStartCenter {
        AnimatedContent(targetState = isLiked, label = "Like Animation") { liked ->
            IconWithText(
                imageVector = if (liked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                text = likeCount.toString(),
                size = 30.dp,
                paddingStart = 2.dp,
                warna = if (liked) Warna.MerahNormal else Warna.HitamNormal,
                onClickIcon = {
                    isLiked = !isLiked
                    likeCount += if (isLiked) 1 else -1
                    onLikeClick()
                },
                onClickText = { onLikeTextClick() }
            )
        }


        SpaceWidth(15)

        IconWithText(
            imageVector = Icons.Outlined.ModeComment,
            text = project.commentCount.toString(),
            size = 27.dp,
            offset = 1.dp,
            paddingStart = 2.dp,
            onClickIcon = { onCommentClick() }
        )

        SpaceWidth(15)

        IconWithText(
            imageVector = Icons.Filled.Repeat,
            text = "435",
            size = 30.dp,
            offset = (-0.6).dp,
            paddingStart = 0.dp,
            onClickIcon = { onShareClick() }
        )

        RowEnd(modifier = Modifier.fillMaxWidth()) {
            content()
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

@Composable
fun ButtonMerahDynamic(
    project: Project,
    buttonText: String,
    onLikeClick: () -> Unit,
    onLikeTextClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    onButtonClick: () -> Unit,
){
    FeedBottomComponent(
        project = project,
        onLikeClick = onLikeClick,
        onCommentClick = onCommentClick,
        onShareClick = onShareClick,
        onLikeTextClick = onLikeTextClick
    ){  ButtonRequest(text = buttonText){ onButtonClick() } }
}

@Composable
fun ButtonMerahProject(
    project: Project,
    buttonText: String,
    onLikeClick: () -> Unit,
    onLikeTextClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    onButtonClick: () -> Unit,
    onTextClick: () -> Unit,
){
    Column {
        FeedBottomComponent(
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick,
            project = project,
            onLikeTextClick = onLikeTextClick
        )

        Space(20)

        RowBottom {
            Column {
                TeksSwitchPageWithIcon(
                    text1 = "3",
                    text2 = " accepted to join project",

                    size = 13.dp,
                    imageVector = Icons.Filled.Check,
                    color = Warna.MerahTua,
                    colorIcon = Warna.PutihNormal,
                    text1Color = Warna.MerahTua,
                    text2Color = Warna.MerahTua
                ){ }

                Space(3)

                TeksSwitchPageWithIcon(
                    text1 = "1323",
                    text2 = " requested to join project",

                    size = 13.dp,
                    imageVector = Icons.Filled.AccessTime,
                    color = Warna.AbuTua,
                    colorIcon = Warna.PutihNormal,
                    text1Color = Warna.AbuTua,
                    text2Color = Warna.AbuTua
                ){ }
            }
            ColumnEnd{ ButtonRequest(text = buttonText){ onButtonClick()} }
        }
    }
}
