package org.d3if3121.tellink.ui.screen.content.commentpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import org.d3if3121.tellink.data.model.comment.Comment
import org.d3if3121.tellink.ui.component.DialogLoading
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.component.EmptyList
import org.d3if3121.tellink.ui.component.GarisHeight
import org.d3if3121.tellink.ui.component.KartuComment
import org.d3if3121.tellink.ui.component.LoadingIndicatorBox
import org.d3if3121.tellink.ui.component.LoadingIndicatorMini
import org.d3if3121.tellink.ui.component.ScaffoldSurfacePutih
import org.d3if3121.tellink.ui.component.Space
import org.d3if3121.tellink.ui.component.SpaceWidth
import org.d3if3121.tellink.ui.screen.content.component.StateHandler
import org.d3if3121.tellink.ui.screen.content.component.StateHandlerSecond

@Composable
fun CommentPage(navController: NavController, commentPageViewModel: CommentPageViewModel, projectId: String){

    val dialogMessage by commentPageViewModel.dialogMessage.collectAsState()
    var dialogActive by remember { mutableStateOf(false) }

    LaunchedEffect(dialogMessage){ dialogActive = dialogMessage.message.isNotEmpty() }

    LaunchedEffect(Unit){ if(projectId != "") commentPageViewModel.getComment(projectId) }

    ScaffoldSurfacePutih(
        topbar = {},
        content = { CommentPageStateContent(commentPageViewModel)  }
    )

    DialogLoading(commentPageViewModel.loading)

    DialogMessage(
        visible =  dialogActive,
        dialog = dialogMessage
    )
}


@Composable
fun CommentPageStateContent(commentPageViewModel: CommentPageViewModel){
    val mahasiswaCommentResponse by commentPageViewModel.commentListResponse.collectAsState()

    StateHandler(
        viewModel = commentPageViewModel,
        listResponse = mahasiswaCommentResponse,
        isSuccess = { CommentPageContent(commentPageViewModel) },
        isLoading = { LoadingIndicatorBox() }
    )
}

@Composable
fun CommentPageContent(commentPageViewModel: CommentPageViewModel){

    val commentList by commentPageViewModel.commentList.collectAsState()

    if (commentList.isNullOrEmpty()) { EmptyList( "No one has commented this project yet!") } else {
        LazyColumn {
            items(commentList!!) { comment ->
                var replyActive by remember { mutableStateOf(false) }

                KartuComment(
                    comment = comment,
                    replyActive = replyActive,
                    onClick = {},
                    onReplyClick = { commentPageViewModel.replyNameChange(it) },
                    onReplyActive = { replyActive = it },
                    content = {
                        if (replyActive) ReplySection(commentPageViewModel, comment) else {
                            commentPageViewModel.resetSecondState()
                        }
                    }
                )

                Space(15)
            }
        }
    }
}

@Composable
fun ReplySection(commentPageViewModel: CommentPageViewModel, comment: Comment){

    val replyResponse by commentPageViewModel.replyListResponse.collectAsState()

    LaunchedEffect(Unit){
        commentPageViewModel.getReply(comment.id)
    }

    StateHandlerSecond(
        viewModel = commentPageViewModel,
        listResponse = replyResponse,
        isSuccess = { Space(10); ReplyContent(commentPageViewModel) },
        isLoading = { Column(Modifier.height(70.dp), verticalArrangement = Arrangement.Center) { LoadingIndicatorMini() } }
    )
}


@Composable
fun ReplyContent(commentPageViewModel: CommentPageViewModel){
    val replyList by commentPageViewModel.replyList.collectAsState()

    Row(Modifier.height(IntrinsicSize.Min)){
        GarisHeight(); SpaceWidth(10);

        Column {
            if (replyList.isNullOrEmpty()) { EmptyList( "No one has commented this project yet!") } else {
                replyList!!.forEach { reply ->
                    Space(10)
                    KartuComment(
                        comment = reply,
                        onClick = {},
                        onReplyClick = { commentPageViewModel.replyNameChange(it) }
                    );
                }
            }
        }
    }
}




