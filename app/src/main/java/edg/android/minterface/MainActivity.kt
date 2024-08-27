package edg.android.minterface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import edg.android.minterface.system.BarBehavior
import edg.android.minterface.system.BarType
import edg.android.minterface.system.configSystemBars
import edg.android.minterface.system.rememberSystemBarController
import edg.android.minterface.ui.theme.MinterfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        configSystemBars()
        setContent {
            MinterfaceTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    val systemBarController = rememberSystemBarController()
    systemBarController.setHiddenModeBehavior(BarBehavior.TRANSIENT)
    systemBarController.setStatusBarColor(
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
    )

    systemBarController.setNavigationBarColor(
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
    )

    val useDarkIcons = !isSystemInDarkTheme()

    var value by remember {
        mutableStateOf(systemBarController.isStatusBarVisible)
    }

    systemBarController.listenVisibilityChanges(
        callback = { state, type ->
            value = when (type) {
                BarType.SYSTEM -> state

                else -> value
            }
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surfaceBright)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
    ) {
        Button(
            onClick = {
                systemBarController.show(BarType.SYSTEM)
            },
            modifier = Modifier

        ) {
            Text(text = "SHOW")
        }
        Button(
            onClick = {
                systemBarController.hide(BarType.SYSTEM)
            },
            modifier = Modifier
        ) {
            Text(text = "HIDE")
        }
        Text(
            text = "STATUS: ${if (value) "visible" else "hidden"}",
        )
    }
}
