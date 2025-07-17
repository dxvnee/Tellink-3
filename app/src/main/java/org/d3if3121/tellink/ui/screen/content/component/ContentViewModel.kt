package org.d3if3121.tellink.ui.screen.content.component

import kotlinx.coroutines.flow.MutableStateFlow


interface ContentLoadingViewModel<T>: ContentViewModel<T>, LoadingDialogHandler

interface ContentViewModel<T> {
    fun resetState()
    fun responseChange(data: T?)
}

interface GambarHandler {
    val gambarDialog: MutableStateFlow<Boolean>
    val gambarString: MutableStateFlow<String>

    fun gambarChange(active: Boolean)
    fun gambarStringChange(gambarBaru: String)
    fun onDialogGambar(active: Boolean, gambarBaru: String)
}

interface LoadingDialogHandler {
    var loading: Boolean
    fun loadingChange(state: Boolean)
    fun dialogChange(title: String, message: String)
}