package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.data.model.comment.Comment
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.ui.formula.formatRelativeTime
import org.d3if3121.tellink.ui.screen.content.commentpage.CommentPageViewModel
import org.d3if3121.tellink.ui.theme.Warna


@Composable
fun KartuProfilProject(mahasiswa: Mahasiswa, project: Project){
    Row {
        Gambar(painterResource(id = R.drawable.photo), 53.dp, Modifier.padding(end = 10.dp))

        Column {
            TeksBoldMerah(mahasiswa.nama, Modifier.offset(y = (-2).dp))
            TeksBoldAbu(mahasiswa.jurusan, Modifier.offset(y = (-7).dp))
            TeksNormalAbu(formatRelativeTime(project.date), Modifier.offset(y = (-13).dp))
        }
    }
}


@Composable
fun KartuProfil(mahasiswa: Mahasiswa, onClick: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.clickable { onClick() }) {
        Gambar(painterResource(id = R.drawable.photo), 53.dp, Modifier.padding(end = 10.dp))

        Column {
            TeksBold(mahasiswa.nama, size = 15.sp)
            TeksNormal(mahasiswa.nim, size = 14.sp)
        }
        ColumnEnd{ ButtonRequest(text = "+ Add"){ } }
    }
}


@Composable
fun KartuComment(
    comment: Comment,
    replyActive: Boolean = false,
    onClick: () -> Unit,
    onReplyClick: (CommentPageViewModel.ReplyName) -> Unit = {},
    onReplyActive: (Boolean) -> Unit = {},
    content: @Composable () -> Unit = {}
){

    var dropdownActive by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.Top, modifier = Modifier.clickable { onClick() }) {
        Gambar(painterResource(id = R.drawable.photo), 48.dp, Modifier.padding(end = 10.dp))

        Column {
            BoxAbuMuda(Modifier.clickable { dropdownActive = true }) {
                Column(Modifier.padding(10.dp)) {
                    TeksBold(comment.mahasiswa.nama, size = 15.sp)
                    Space(2)
                    TeksNormal(text = comment.comment, size = 15.sp)
                }
            }

            Row {
                TeksBoldAbu( "Reply", size = 13.sp, modifier = Modifier.clickable { onReplyClick(
                    CommentPageViewModel.ReplyName(
                        comment.mahasiswa.nama,
                        if (comment.commentId.isNullOrBlank()) comment.id else comment.commentId
                    )
                ) })
                SpaceWidth(6)
                TeksNormalAbu(comment.date, size = 13.sp)
            }

            if(comment.replyCount != 0) {
                Row(verticalAlignment = Alignment.CenterVertically){
                    GarisWidth();
                    SpaceWidth(6)
                    TeksBoldAbu( text = if (!replyActive) "View ${comment.replyCount} more reply" else "Hide replies", size = 13.sp, modifier = Modifier.clickable { onReplyActive(!replyActive) })
                }
            }
            content()

        }
    }
}

//
//@Composable
//fun KartuProfil(
//    judul: String,
//    konten: String,
//){
//
//    Text(
//        text = judul,
//        color = Warna.MerahNormal,
//        fontSize = 21.sp,
//        fontWeight = FontWeight.ExtraBold,
//        modifier = Modifier.padding(bottom = 8.dp)
//
//    )
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight()
//            .padding(bottom = 20.dp),
//
//        colors = CardDefaults.cardColors(containerColor = Warna.PutihNormal),
//        shape = RoundedCornerShape(10.dp),
//        elevation = CardDefaults.cardElevation(6.dp)
//    ){
//        Column (
//            horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.Top,
//            modifier = Modifier.padding(17.dp).fillMaxSize()
//        ){
//            Text(
//                text = konten,
//                color = Warna.HitamNormal,
//                fontSize = 15.sp,
//                fontWeight = FontWeight.Normal,
//                textAlign = TextAlign.Justify
//            )
//        }
//
//
//
//
//
//    }
//}


@Composable
fun KartuProfilGambar(
    fotoprofil: Int,
    nama: String,
    jurusan: String,
    hari: String,

    judul: String,
    gambar: Int,
    konten: String,
){

    Text(
        text = judul,
        color = Warna.MerahNormal,
        fontSize = 21.sp,
        fontWeight = FontWeight.ExtraBold,
        modifier = Modifier.padding(bottom = 8.dp)

    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 20.dp),

        colors = CardDefaults.cardColors(containerColor = Warna.PutihNormal),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ){
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(17.dp).fillMaxSize()
        ){

            Image(
                painter = painterResource(gambar),
                contentDescription = "Chat logo",
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))

            )

            Text(
                text = konten,
                color = Warna.HitamNormal,
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(top = 12.dp)
            )
        }





    }
}
