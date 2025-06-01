package org.d3if3121.tellink.ui.screen.auth.component.template

interface AuthViewModel {
    var loading: Boolean
    fun loadingChange(state: Boolean) {loading = state}

    fun resetState()
}