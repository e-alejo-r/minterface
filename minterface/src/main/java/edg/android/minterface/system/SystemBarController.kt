package edg.android.minterface.system

import android.os.Bundle
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.luminance

interface SystemBarController {
    var isStatusBarVisible: Boolean
    var isNavigationBarVisible: Boolean
    var isSystemBarsVisible: Boolean
        get() = isNavigationBarVisible && isStatusBarVisible
        set(value) {
            isStatusBarVisible = value
            isNavigationBarVisible = value
        }

    fun hide(barType: BarType)
    fun show(barType: BarType)
    fun setHiddenModeBehavior(barBehavior: BarBehavior)
    fun listenVisibilityChanges(callback: (state: Boolean, type: BarType) -> Unit)
    fun onSaveInstanceState(bundle: Bundle)
    fun onRestoreInstanceState(bundle: Bundle)

    var systemBarsBehavior: Int

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
}

private val BlackScrim = Color(0f, 0f, 0f, 0.3f) // 30% opaque black
private val BlackScrimmed: (Color) -> Color = { original ->
    BlackScrim.compositeOver(original)
}