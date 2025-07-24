package org.d3if3121.tellink.ui.screen.content.likepage

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.d3if3121.tellink.ui.component.ColumnPadding
import org.d3if3121.tellink.ui.component.DialogLoading
import org.d3if3121.tellink.ui.component.DialogMessage
import org.d3if3121.tellink.ui.component.EmptyList
import org.d3if3121.tellink.ui.component.KartuProfil
import org.d3if3121.tellink.ui.component.ScaffoldSurfacePutih
import org.d3if3121.tellink.ui.component.Space
import org.d3if3121.tellink.ui.component.TopBarContent
import org.d3if3121.tellink.ui.screen.content.component.StateHandler
import org.d3if3121.tellink.ui.screen.content.projectpage.projecteditpage.LikePageViewModel

@Composable
fun LikePage(navController: NavController, projectId: String){

    val likePageViewModel: LikePageViewModel = hiltViewModel()

    val dialogMessage by likePageViewModel.dialogMessage.collectAsState()
    var dialogActive by remember { mutableStateOf(false) }

    LaunchedEffect(dialogMessage){ dialogActive = dialogMessage.message.isNotEmpty() }

    LaunchedEffect(Unit){ likePageViewModel.getLike(projectId) }

    ScaffoldSurfacePutih(
        topbar = {
            TopBarContent(
                text = "Likes",
                icon = Icons.Filled.ArrowBackIosNew,
                navController = navController
            )
        },
        content = { ColumnPadding { LikePageStateContent(likePageViewModel) } }
    )

    DialogLoading(likePageViewModel.loading)

    DialogMessage(
        visible =  dialogActive,
        dialog = dialogMessage
    )
}


@Composable
fun LikePageStateContent(likePageViewModel: LikePageViewModel){
    val mahasiswaLikeResponse by likePageViewModel.mahasiswaLikeResponse.collectAsState()

    StateHandler(
        viewModel = likePageViewModel,
        listResponse = mahasiswaLikeResponse,
        isSuccess = { LikePageContent(likePageViewModel) },
    )
}

@Composable
fun LikePageContent(likePageViewModel: LikePageViewModel){

    val mahasiswaList by likePageViewModel.mahasiswaLike.collectAsState()

    if (mahasiswaList.isNullOrEmpty()) { EmptyList( "No one has liked this project yet!") } else {
        LazyColumn {
            items(mahasiswaList!!) { mahasiswa ->
                KartuProfil(mahasiswa){}; Space(20)
            }
        }
    }
}
