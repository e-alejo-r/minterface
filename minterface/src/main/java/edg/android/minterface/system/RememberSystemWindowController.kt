package edg.android.minterface.system

import android.app.Activity
import android.view.Window
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
internal fun getWindow(): Window {
    val window = (LocalContext.current as Activity).window
    return remember(window) {
        window
    }
}

@Composable
fun rememberSystemWindowController(window: Window = getWindow()): SystemWindowController {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    return remember(window, windowSizeClass) {
        AndroidSystemWindowController(window, windowSizeClass)
    }
}