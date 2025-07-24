package org.d3if3121.tellink.ui.screen.content.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.d3if3121.tellink.ui.animation.AnimationFade
import org.d3if3121.tellink.ui.component.BottomSheetNative
import org.d3if3121.tellink.ui.component.IconTombol
import org.d3if3121.tellink.ui.component.InputWithTombolBottomSheet
import org.d3if3121.tellink.ui.component.Space
import org.d3if3121.tellink.ui.component.SpaceWidth
import org.d3if3121.tellink.ui.component.TeksNormalAbu
import org.d3if3121.tellink.ui.screen.content.commentpage.CommentPage
import org.d3if3121.tellink.ui.screen.content.commentpage.CommentPageViewModel
import org.d3if3121.tellink.ui.screen.content.homepage.HomeViewModel
import org.d3if3121.tellink.ui.theme.Warna
import org.d3if3121.tellink.ui.viewmodel.MainViewModel


@Composable
fun CommentBottomSheet(mainViewModel: MainViewModel, homeViewModel: HomeViewModel, navController: NavController){

    val commentViewModel: CommentPageViewModel = hiltViewModel()

    val bottomSheetVisible by homeViewModel.commentDialog.collectAsState()
    val projectId by homeViewModel.projectId.collectAsState()
    val nameReply by commentViewModel.replyName.collectAsState()

    val currentUser by mainViewModel.currentUser.collectAsState()

    var isTextFieldFocused by remember { mutableStateOf(false) }
    var commentText by remember { mutableStateOf("") }


    BottomSheetNative(
        visible = bottomSheetVisible,
        collapse = isTextFieldFocused,
        onDismiss = { homeViewModel.commentDialogChange(false, "") },
        content = { CommentPage(navController, commentViewModel, projectId) },
        secondContent = {
            NameReplyColumn(nameReply.name) { commentViewModel.replyNameChange("", "") }

            InputWithTombolBottomSheet(
                commentText = commentText,
                onCommentChange = { commentText = it },
                onFocus = { isTextFieldFocused = it }
            ){
                commentViewModel.addComment(currentUser.nim, projectId, commentText, nameReply.commentId)
            }
        }
    )
}

@Composable
fun NameReplyColumn(name: String, onDismiss: () -> Unit){
    AnimationFade(name.isNotBlank()) {
        Box(
            modifier = Modifier.height(25.dp).padding(top = 4.dp).fillMaxHeight().background(Warna.PutihNormal)
        ){
            Row {
                IconTombol(
                    imageVector = Icons.Filled.Clear,
                    size = 15.dp,
                    onClick = { onDismiss() },
                    warna = Warna.AbuTua
                )
                SpaceWidth(5)
                TeksNormalAbu("Replying to @${name}..", color = Warna.AbuTua)
            }

        }
    }
}
