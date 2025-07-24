package org.d3if3121.tellink.ui.screen.content.component

sealed class ButtonMerahBehaviour {
    data class Project(
        val buttonText: String,

        val onLikeClick: () -> Unit,
        val onLikeTextClick: () -> Unit,
        val onCommentClick: () -> Unit,
        val onShareClick: () -> Unit,
        val onButtonClick: () -> Unit,
        val onTextClick: () -> Unit,
    ): ButtonMerahBehaviour()

    data class Accept(
        val onclick: () -> Unit,
    ): ButtonMerahBehaviour()

    data class Dynamic(
        val active: Boolean,
        val onclick: () -> Unit,
        val onclickcancel: () -> Unit,
        val onrequestchange: (Boolean) -> Unit,

        val buttonText: String,
        val onLikeClick: () -> Unit,
        val onLikeTextClick: () -> Unit,
        val onCommentClick: () -> Unit,
        val onShareClick: () -> Unit,
        val onButtonClick: () -> Unit
    ): ButtonMerahBehaviour()

    object None : ButtonMerahBehaviour()
}