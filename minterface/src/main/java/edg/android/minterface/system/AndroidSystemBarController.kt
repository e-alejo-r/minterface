package edg.android.minterface.system

import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

internal class AndroidSystemBarController(
    private val window: Window
) : SystemBarController {
    private val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
    private val callbacks = mutableListOf<(Boolean, BarType) -> Unit>()

    override fun setHiddenModeBehavior(barBehavior: BarBehavior) {
        windowInsetsController.systemBarsBehavior = when (barBehavior) {
            BarBehavior.TRANSIENT -> {
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }

            BarBehavior.SWIPE -> {
                WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            }
        }
    }

    override fun hide(barType: BarType) {
        windowInsetsController.hide(
            when (barType) {
                BarType.STATUS -> WindowInsetsCompat.Type.statusBars()
                BarType.NAVIGATION -> WindowInsetsCompat.Type.navigationBars()
                BarType.SYSTEM -> WindowInsetsCompat.Type.systemBars()
            }
        )
        extracted(false, barType)
    }

    override fun show(barType: BarType) {
        windowInsetsController.show(
            when (barType) {
                BarType.STATUS -> WindowInsetsCompat.Type.statusBars()
                BarType.NAVIGATION -> WindowInsetsCompat.Type.navigationBars()
                BarType.SYSTEM -> WindowInsetsCompat.Type.systemBars()
            }
        )
        extracted(true, barType)
    }

    override var isNavigationBarVisible: Boolean
        get() {
            return ViewCompat.getRootWindowInsets(window.decorView)
                ?.isVisible(WindowInsetsCompat.Type.navigationBars()) == true
        }
        set(value) {
            if (value) {
                windowInsetsController.show(WindowInsetsCompat.Type.navigationBars())
            } else {
                windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
            }
        }

    override var isStatusBarVisible: Boolean
        get() {
            return ViewCompat.getRootWindowInsets(window.decorView)
                ?.isVisible(WindowInsetsCompat.Type.statusBars()) == true
        }
        set(value) {
            if (value) {
                windowInsetsController.show(WindowInsetsCompat.Type.statusBars())
            } else {
                windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
            }
        }

    override fun listenVisibilityChanges(callback: (state: Boolean, type: BarType) -> Unit) {
        this.callbacks.add(callback)
    }

    override fun onSaveInstanceState(bundle: Bundle) {

    }

    override fun onRestoreInstanceState(bundle: Bundle) {

    }

    override var systemBarsBehavior: Int
        get() = windowInsetsController.systemBarsBehavior
        set(value) {
            windowInsetsController.systemBarsBehavior = value
        }

    override fun setStatusBarColor(
        color: Color,
        darkIcons: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) {
        statusBarDarkContentEnabled = darkIcons

        window.statusBarColor = when {
            darkIcons && !windowInsetsController.isAppearanceLightStatusBars -> {
                // If we're set to use dark icons, but our windowInsetsController call didn't
                // succeed (usually due to API level), we instead transform the color to maintain
                // contrast
                transformColorForLightContent(color)
            }

            else -> color
        }.toArgb()
    }

    override fun setNavigationBarColor(
        color: Color,
        darkIcons: Boolean,
        navigationBarContrastEnforced: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) {
        navigationBarDarkContentEnabled = darkIcons
        isNavigationBarContrastEnforced = navigationBarContrastEnforced

        window.navigationBarColor = when {
            darkIcons && !windowInsetsController.isAppearanceLightNavigationBars -> {
                // If we're set to use dark icons, but our windowInsetsController call didn't
                // succeed (usually due to API level), we instead transform the color to maintain
                // contrast
                transformColorForLightContent(color)
            }

            else -> color
        }.toArgb()
    }

    override var statusBarDarkContentEnabled: Boolean
        get() = windowInsetsController.isAppearanceLightStatusBars
        set(value) {
            windowInsetsController.isAppearanceLightStatusBars = value
        }

    override var navigationBarDarkContentEnabled: Boolean
        get() = windowInsetsController.isAppearanceLightNavigationBars
        set(value) {
            windowInsetsController.isAppearanceLightNavigationBars = value
        }

    override var isNavigationBarContrastEnforced: Boolean
        get() = Build.VERSION.SDK_INT >= 29 && window.isNavigationBarContrastEnforced
        set(value) {
            if (Build.VERSION.SDK_INT >= 29) {
                window.isNavigationBarContrastEnforced = value
            }
        }

    private fun extracted(state: Boolean, barType: BarType) {
        when (barType) {
            BarType.STATUS -> {
                for (function in callbacks) {
                    function.invoke(state, BarType.STATUS)
                }
            }

            BarType.NAVIGATION -> {
                for (function in callbacks) {
                    function.invoke(state, BarType.NAVIGATION)
                }
            }

            BarType.SYSTEM -> {
                for (function in callbacks) {
                    function.invoke(state, BarType.SYSTEM)
                }
            }
        }
    }
}