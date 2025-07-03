package org.d3if3121.tellink.ui.screen.content.component

interface ContentViewModel<T> {
    var loading: Boolean
    fun resetState()
    fun loadingChange(state: Boolean)

    fun responseChange(data: T?)
    fun onDialogGambar(active: Boolean, gambarBaru: String)
}

interface AddViewModel {
    var loading: Boolean
    fun loadingChange(state: Boolean)
    fun dialogChange(title: String, message: String)
}