package org.d3if3121.tellink.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import org.d3if3121.tellink.navigation.GlobalNavGraph
import org.d3if3121.tellink.ui.theme.TellinkTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TellinkTheme {
                GlobalNavGraph()
//                EditPage(rememberNavController())
            }
        }
    }
}
