package org.d3if3121.tellink.ui.screen.content.homepage.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.d3if3121.tellink.R
import org.d3if3121.tellink.ui.component.InputPutihSearch
import org.d3if3121.tellink.ui.component.TeksJudulMerah

@Composable
fun HomeTopContent(){
    var search by remember { mutableStateOf("") }

    TeksJudulMerah(text = "Welcome to Tellink", Modifier.padding(top = 20.dp))
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 17.dp)
    ){
        InputPutihSearch(
            input = search,
            placeholder = stringResource(id = R.string.search),
            onInputChange = { search = it },
            keyboardType = KeyboardType.Number,
            modifier = Modifier.fillMaxWidth()
        )
    }
}