package edg.android.minterface.system

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.Dp
import edg.android.minterface.rsize.WindowSize

interface SystemWindowController {
    var isStatusBarVisible: Boolean

    var isNavigationBarVisible: Boolean

    var isSystemBarsVisible: Boolean
        get() = isNavigationBarVisible && isStatusBarVisible
        set(value) {
            isStatusBarVisible = value
            isNavigationBarVisible = value
        }

    fun listenVisibilityChanges(
        onChangeVisibilityStatusBar: (isVisibility: Boolean) -> Unit,
        onChangeVisibilityNavigationBar: (isVisibility: Boolean) -> Unit
    )

    var systemBarBehavior: BarBehavior

    fun setStatusBarColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        transformColorForLightContent: (Color) -> Color = BlackScrimmed
    )

    fun setNavigationBarColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        navigationBarContrastEnforced: Boolean = true,
        transformColorForLightContent: (Color) -> Color = BlackScrimmed
    )

    fun setSystemBarsColor(
        color: Color,
        darkIcons: Boolean = color.luminance() > 0.5f,
        isNavigationBarContrastEnforced: Boolean = true,
        transformColorForLightContent: (Color) -> Color = BlackScrimmed
    ) {
        setStatusBarColor(color, darkIcons, transformColorForLightContent)
        setNavigationBarColor(
            color,
            darkIcons,
            isNavigationBarContrastEnforced,
            transformColorForLightContent
        )
    }

    var statusBarDarkContentEnabled: Boolean

    var navigationBarDarkContentEnabled: Boolean

    var systemBarsDarkContentEnabled: Boolean
        get() = statusBarDarkContentEnabled && navigationBarDarkContentEnabled
        set(value) {
            statusBarDarkContentEnabled = value
            navigationBarDarkContentEnabled = value
        }

    var isNavigationBarContrastEnforced: Boolean

    val hasDisplayCutout: Boolean

    val getDisplayCutoutLeft: Dp

    val getDisplayCutoutRight: Dp

    val getDisplayCutoutTop: Dp

    val getDisplayCutoutBottom: Dp

    val isPortrait: Boolean

    val isLandscape: Boolean

    val getWindowWidth: WindowSize

    val getWindowHeight: WindowSize

}

private val BlackScrim = Color(0f, 0f, 0f, 0.3f) // 30% opaque black

private val BlackScrimmed: (Color) -> Color = { original ->
    BlackScrim.compositeOver(original)
}