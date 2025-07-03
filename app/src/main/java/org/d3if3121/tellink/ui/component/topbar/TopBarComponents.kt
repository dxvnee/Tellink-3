package org.d3if3121.tellink.ui.component.topbar

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged


@OptIn(FlowPreview::class)
@Composable
fun observeScrollState(lazyListState: LazyListState) : Boolean{
    var isScrolledFar by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemScrollOffset > 28 }
            .distinctUntilChanged()
            .debounce(160)
            .collect { newIsScrolledFar ->
                isScrolledFar = newIsScrolledFar
            }
    }
    return isScrolledFar
}

@OptIn(FlowPreview::class)
@Composable
fun cekScroll(lazyListState: LazyListState) : Boolean{
    var isScrolled by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex > 0 }
            .distinctUntilChanged()
            .debounce(80)
            .collect {
                Log.d("Hehe5", "Scroll state: $it (Index: ${lazyListState.firstVisibleItemIndex})")

                isScrolled = it
            }
    }
    Log.d("Hehe5", isScrolled.toString())

    return isScrolled
}

@OptIn(FlowPreview::class)
@Composable
fun cekScrollProject(lazyListState: LazyListState): Boolean {
    var isScrolled by remember { mutableStateOf(false) }

    LaunchedEffect(lazyListState) {
        Log.d("Hehe4", "LaunchedEffect started")

        snapshotFlow { lazyListState.firstVisibleItemIndex > 0 }
            .distinctUntilChanged()
            .debounce(80)
            .collect {
                Log.d("Hehe4", "Scroll state: $it (Index: ${lazyListState.firstVisibleItemIndex})")
                isScrolled = it
            }
    }

    return isScrolled
}



@Composable
fun scrollDirectionDetector(lazyListState: LazyListState): String {
    var previousIndex by remember { mutableIntStateOf(lazyListState.firstVisibleItemIndex) }
    var scrollDirection by remember { mutableStateOf("Idle") }

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .collect { currentIndex ->
                scrollDirection = when {
                    currentIndex > previousIndex -> "Down"
                    currentIndex < previousIndex -> "Up"
                    else -> "Idle"
                }
                previousIndex = currentIndex
            }
    }
    return scrollDirection
}
