package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopBarContent(
    navController: NavController,
    text: String,
    icon: ImageVector
){
    RowStartCenter(modifier = Modifier.height(30.dp)) {
        IconTombol(
            imageVector = icon,
            size = 16.dp,
            onClick = { navController.popBackStack() },
        )
        SpaceWidth(15)
        TeksJudulHitamKecil(text = text)
    }
}