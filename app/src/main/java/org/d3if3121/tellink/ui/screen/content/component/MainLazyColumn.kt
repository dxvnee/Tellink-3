package org.d3if3121.tellink.ui.screen.content.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import org.d3if3121.tellink.ui.animation.AnimationAppear
import org.d3if3121.tellink.ui.animation.AnimationFadeZIndex
import org.d3if3121.tellink.ui.component.BoxFillMaxSize
import org.d3if3121.tellink.ui.component.BoxMaxWidth
import org.d3if3121.tellink.ui.component.Space

@Composable
fun <T> MainLazyColumn(
    topContent: @Composable () -> Unit,
    topContentSecondary: @Composable () -> Unit = {},
    mainContent: @Composable (T?) -> Unit,
    list: List<T>?,
    showStickyPadding: Boolean = false,
    alphaDone: Boolean = false,
    lazyListState: LazyListState? = rememberLazyListState(),
){
    val mainLazyListState = lazyListState ?: rememberLazyListState()

    BoxFillMaxSize {
        AnimationFadeZIndex(alphaDone) { topContentSecondary() }

        LazyColumn(state = mainLazyListState){
            item { topContent() }
            item {
                if(showStickyPadding){
                    BoxMaxWidth { AnimationAppear(visible = !alphaDone) { topContentSecondary() } }
                }
            }

            when{
                list.isNullOrEmpty() -> {
                    item{
                        Space(200)
                        mainContent(null)
                    }
                }
                else -> {
                    items(list) { item ->
                        mainContent(item)
                    }
                }
            }
        }
    }
}
