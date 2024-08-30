package edg.android.minterface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edg.android.minterface.rsize.WindowSize
import edg.android.minterface.system.BarBehavior
import edg.android.minterface.system.SystemWindowController
import edg.android.minterface.system.rememberSystemWindowController
import edg.android.minterface.ui.theme.MinterfaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinterfaceTheme {
                val systemBarsController = rememberSystemWindowController(window)
                Greeting(swc = systemBarsController)
            }
        }
    }
}

@Composable
fun Greeting(swc: SystemWindowController) {
    swc.setStatusBarColor(
        color = MaterialTheme.colorScheme.surfaceContainerHighest,
    )

    swc.setNavigationBarColor(
        color = MaterialTheme.colorScheme.surfaceContainerHigh,
    )

    swc.systemBarBehavior = BarBehavior.TRANSIENT
    var resultStatusBar by remember {
        mutableStateOf(swc.isStatusBarVisible)
    }

    var resultNavigationBar by remember {
        mutableStateOf(swc.isNavigationBarVisible)
    }
    swc.listenVisibilityChanges(
        onChangeVisibilityStatusBar = { isVisible ->
            resultStatusBar = isVisible
        },
        onChangeVisibilityNavigationBar = { isVisible ->
            resultNavigationBar = isVisible
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                /*.statusBarsPadding()
                .navigationBarsPadding()*/
                .windowInsetsPadding(WindowInsets.systemBars)
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(
                        start = if (swc.isLandscape and (swc.getDisplayCutoutLeft > 0.dp)) swc.getDisplayCutoutLeft else 16.dp,
                        end = if (swc.isLandscape and (swc.getDisplayCutoutRight > 0.dp)) swc.getDisplayCutoutRight else 16.dp,
                    )
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Status Bar: ${if (resultStatusBar) "true" else "false"}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "Navigation Bar: ${if (resultNavigationBar) "true" else "false"}",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Button(
                    onClick = {
                        swc.isStatusBarVisible = true
                    },
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(text = "SHOW STATUS BAR")
                }
                OutlinedButton(
                    onClick = {
                        swc.isStatusBarVisible = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "HIDE STATUS BAR")
                }
                Button(
                    onClick = {
                        swc.isNavigationBarVisible = true
                    },
                    modifier = Modifier.fillMaxWidth()

                ) {
                    Text(text = "SHOW NAVIGATION BAR")
                }
                OutlinedButton(
                    onClick = {
                        swc.isNavigationBarVisible = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "HIDE NAVIGATION BAR")
                }

                var text by remember {
                    mutableStateOf("")
                }
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Input") },
                    modifier = Modifier.fillMaxWidth()
                )

                when (swc.getWindowWidth) {
                    WindowSize.COMPACT -> {
                        Text(text = "COMPACT")
                    }

                    WindowSize.MEDIUM -> {
                        Text(text = "MEDIUM")
                    }

                    WindowSize.EXPANDED -> {
                        Text(text = "EXPANDED")
                    }
                }
            }
        }
    }
}
