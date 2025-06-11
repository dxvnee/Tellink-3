package org.d3if3121.tellink.ui.screen.content.component

sealed class ButtonMerahBehaviour {
    data class Project(
        val onclickbutton: () -> Unit,
        val onclicktext: () -> Unit,
    ): ButtonMerahBehaviour()

    data class Accept(
        val onclick: () -> Unit,
    ): ButtonMerahBehaviour()

    data class Dynamic(
        val active: Boolean,
        val onclick: () -> Unit,
        val onclickcancel: () -> Unit,
        val onrequestchange: (Boolean) -> Unit,
    ): ButtonMerahBehaviour()

    object None : ButtonMerahBehaviour()
}