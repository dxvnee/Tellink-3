package org.d3if3121.tellink.ui.screen.content.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.ui.component.ButtonMerah
import org.d3if3121.tellink.ui.component.ButtonMerahDynamic
import org.d3if3121.tellink.ui.component.ButtonMerahProject
import org.d3if3121.tellink.ui.component.TeksBoldTombol


@Composable
fun ButtonMerahKartuType(buttonbehaviour: ButtonMerahBehaviour){
    when (buttonbehaviour) {
        is ButtonMerahBehaviour.Project -> {
            ButtonMerahProject(
                buttonText = buttonbehaviour.buttonText,
                onLikeClick = buttonbehaviour.onLikeClick,
                onCommentClick = buttonbehaviour.onCommentClick,
                onShareClick = buttonbehaviour.onShareClick,
                onButtonClick = buttonbehaviour.onButtonClick,
                onTextClick = buttonbehaviour.onTextClick
            )
        }
        is ButtonMerahBehaviour.Accept -> {
            ButtonMerah(
                onClick = { buttonbehaviour.onclick },
                modifier = Modifier.fillMaxWidth().size(46.dp),
                content = { TeksBoldTombol(stringResource(id = R.string.accepted)) },
            )
        }
        is ButtonMerahBehaviour.Dynamic -> {
            ButtonMerahDynamic(
                buttonText = buttonbehaviour.buttonText,
                onLikeClick = buttonbehaviour.onLikeClick,
                onCommentClick = buttonbehaviour.onCommentClick,
                onShareClick = buttonbehaviour.onShareClick,
                onButtonClick = buttonbehaviour.onButtonClick,
            )
        }
        is ButtonMerahBehaviour.None -> {}
    }
}