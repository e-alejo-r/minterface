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
fun rememberSystemController(window: Window = getWindow()): SystemController {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    return remember(window, windowSizeClass) {
        AndroidSystemController(window, windowSizeClass)
    }
}