package org.d3if3121.tellink.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3121.tellink.ui.theme.Warna

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DisplayTag(
    selectedTag: List<String> = emptyList(),
    modifier: Modifier = Modifier,
    onTagRemove: (String) -> Unit = {}
){
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        maxItemsInEachRow = 5,
        overflow = FlowRowOverflow.Clip
    ){
        selectedTag.forEach { tag ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .padding(end = 6.dp)
                    .wrapContentWidth()
                    .height(28.dp).border(
                        width = 1.dp,
                        color = Warna.MerahNormal,
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable { onTagRemove(tag) }
            ){
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, top = 2.dp)
                ){
                    TeksNormalMerah(tag, size = 14.sp)
                }
            }
        }
    }
}
