package org.d3if3121.tellink.ui.screen.content.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.data.model.project.Project
import org.d3if3121.tellink.ui.component.ButtonMerah
import org.d3if3121.tellink.ui.component.ButtonMerahDynamic
import org.d3if3121.tellink.ui.component.ButtonMerahProject
import org.d3if3121.tellink.ui.component.TeksBoldTombol


@Composable
fun ButtonMerahKartuType(project: Project, buttonbehaviour: ButtonMerahBehaviour){
    when (buttonbehaviour) {
        is ButtonMerahBehaviour.Project -> {
            ButtonMerahProject(
                project = project,
                buttonText = buttonbehaviour.buttonText,
                onLikeClick = buttonbehaviour.onLikeClick,
                onLikeTextClick = buttonbehaviour.onLikeTextClick,
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
                project = project,
                buttonText = buttonbehaviour.buttonText,
                onLikeClick = buttonbehaviour.onLikeClick,
                onLikeTextClick = buttonbehaviour.onLikeTextClick,
                onCommentClick = buttonbehaviour.onCommentClick,
                onShareClick = buttonbehaviour.onShareClick,
                onButtonClick = buttonbehaviour.onButtonClick,
            )
        }
        is ButtonMerahBehaviour.None -> {}
    }
}