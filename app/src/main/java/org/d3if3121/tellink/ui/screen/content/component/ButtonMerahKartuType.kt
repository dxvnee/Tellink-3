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
import org.d3if3121.tellink.ui.component.TeksBoldTombol
import org.d3if3121.tellink.ui.screen.auth.component.TombolTeksBawah
import org.d3if3121.tellink.ui.theme.Warna


@Composable
fun ButtonMerahKartuType(
    buttonbehaviour: ButtonMerahBehaviour,
){
    when (buttonbehaviour) {
        is ButtonMerahBehaviour.Project -> {
            TombolTeksBawah(
                onClickButton = { buttonbehaviour.onclickbutton },
                onClickText = { },
                textTombol = stringResource(id = R.string.edit),
                textTeks2 = stringResource(id = R.string.requested),
                text2Color = Warna.AbuTua
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
                active = buttonbehaviour.active,
                onclick = { buttonbehaviour.onclick },
                onclickcancel = { buttonbehaviour.onclickcancel },
                onrequestchange = { buttonbehaviour.onrequestchange },
            )
        }
        is ButtonMerahBehaviour.None -> {}
    }
}