package org.d3if3121.tellink.ui.component.topbar

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import org.d3if3121.tellink.R
import org.d3if3121.tellink.data.model.mahasiswa.Mahasiswa
import org.d3if3121.tellink.navigation.component.Screen
import org.d3if3121.tellink.ui.animation.topBarAlphaAnimation
import org.d3if3121.tellink.ui.animation.topBarHeightAnimation
import org.d3if3121.tellink.ui.component.Gambar
import org.d3if3121.tellink.ui.component.InputPutihSearchProfile
import org.d3if3121.tellink.ui.component.RowEnd
import org.d3if3121.tellink.ui.component.RowStartCenter
import org.d3if3121.tellink.ui.component.TeksBold
import org.d3if3121.tellink.ui.component.TeksNormal
import org.d3if3121.tellink.ui.component.TeksNormalMerah
import org.d3if3121.tellink.ui.component.TombolGambar
import org.d3if3121.tellink.ui.theme.Warna
import org.d3if3121.tellink.ui.theme.topAppBarColors
import org.d3if3121.tellink.ui.viewmodel.MainViewModel


@Composable
fun TopBar(
    lazyListState: LazyListState,
    topbarType: TopbarType,
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController,
){
    val currentUser by mainViewModel.currentUser.collectAsState()

    when(topbarType){
        TopbarType.HOME -> { TopBarNormal(currentUser, lazyListState, navController, TopbarType.HOME) }
        TopbarType.PROJECT -> { TopBarNormal(currentUser, lazyListState, navController, TopbarType.PROJECT) }
        TopbarType.PROFILE -> { TopBarSearch() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearch(){

    var search by remember { mutableStateOf("") }

    TopAppBar(
        title = {
            RowStartCenter {
                IconButton(
                    onClick = {},
                    modifier = Modifier.offset(x = (-12).dp)
                ){
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "eheh",
                        tint = Warna.AbuTua
                    )
                }
                InputPutihSearchProfile(
                    input = search,
                    placeholder = stringResource(id = R.string.search),
                    onInputChange = { search = it},
                    keyboardType = KeyboardType.Text,
                    modifier = Modifier.fillMaxWidth().padding( end = 17.dp).height(40.dp),
                    fontSize = 15
                )
            }
        },
        colors = topAppBarColors(),
        modifier = Modifier.background(color = Warna.PutihGelap)
    )
}

@Composable
fun TopBarNormal(
    currentUser: Mahasiswa,
    lazyListState: LazyListState,
    navController: NavHostController,
    topbarType: TopbarType
){
    AnimatedTopBar(lazyListState = lazyListState){
        Gambar(painterResource(id = R.drawable.photo), 50.dp)

        Column(modifier = Modifier.padding(start = 10.dp)) {
            if (topbarType == TopbarType.HOME) {
                TeksNormalMerah("Hello,", modifier = Modifier.offset(y = 3.dp))
                TeksBold(currentUser.nama, modifier = Modifier.offset(y = (-3).dp))
            } else {
                TeksBold(currentUser.nama, modifier = Modifier.offset(y = 3.dp))
                TeksNormal(text = currentUser.nim, color = Warna.AbuTua, modifier = Modifier.offset(y = (-3).dp))
            }
        }

        RowEnd(Modifier.fillMaxWidth()){
            TombolGambar(painterResource(id = R.drawable.notifications), 26){}
            TombolGambar(painterResource(id = R.drawable.chat), 26){
                navController.navigate(Screen.Login.route)
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedTopBar(
    lazyListState: LazyListState,
    content: @Composable () -> Unit
){
    var alphaDone by remember { mutableStateOf(false) }
    val topBarHeight by topBarHeightAnimation(lazyListState = lazyListState, isAlpha = alphaDone)
    val alpha by topBarAlphaAnimation(topBarHeight)

    LaunchedEffect(alpha){ alphaDone = alpha == 0f }

    TopAppBar(
        title = { RowStartCenter(Modifier.fillMaxWidth().padding(end = 5.dp).alpha(alpha)){ content() } },
        colors = topAppBarColors(),
        modifier = Modifier.background(color = Warna.PutihNormal)
            .animateContentSize(animationSpec = tween(durationMillis = 500))
            .height(height = topBarHeight)

    )
}


