package edg.android.minterface.system

import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowInsets
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import edg.android.minterface.rsize.WindowSize

internal class AndroidSystemController(
    private val window: Window,
    private val windowSizeClass: WindowSizeClass
) : SystemController {
    private val view = window.decorView
    private val windowInsetsControllerCompat = WindowCompat.getInsetsController(window, view)
    private val windowInsetsCompat = ViewCompat.getRootWindowInsets(view)
    private val listenersStatusBar = mutableListOf<(Boolean) -> Unit>()
    private val listenersNavigationBar = mutableListOf<(Boolean) -> Unit>()

    private var previousStateStatusBar = false
    private var previousStateNavigationBar = false

    override var isNavigationBarVisible: Boolean
        get() {
            return windowInsetsCompat?.isVisible(WindowInsetsCompat.Type.navigationBars()) == true
        }
        set(value) {
            if (value) {
                windowInsetsControllerCompat.show(WindowInsetsCompat.Type.navigationBars())
            } else {
                windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.navigationBars())
            }
        }

    override var isStatusBarVisible: Boolean
        get() {
            return windowInsetsCompat?.isVisible(WindowInsetsCompat.Type.statusBars()) == true
        }
        set(value) {
            if (value) {
                windowInsetsControllerCompat.show(WindowInsetsCompat.Type.statusBars())
            } else {
                windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.statusBars())
            }
        }

    override var isSystemBarsVisible: Boolean
        get() = windowInsetsCompat?.isVisible(WindowInsetsCompat.Type.systemBars()) == true
        set(value) {
            if (value) {
                windowInsetsControllerCompat.show(WindowInsetsCompat.Type.systemBars())
            } else {
                windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars())
            }
        }

    override fun listenVisibilityChangesOfBars(
        onChangeVisibilityStatusBar: (Boolean) -> Unit,
        onChangeVisibilityNavigationBar: (Boolean) -> Unit
    ) {
        listenersStatusBar.add(onChangeVisibilityStatusBar)
        listenersNavigationBar.add(onChangeVisibilityNavigationBar)
    }

    override var systemBarBehavior: BarBehavior
        get() = when (windowInsetsControllerCompat.systemBarsBehavior) {
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE -> BarBehavior.TRANSIENT
            else -> BarBehavior.SWIPE
        }
        set(value) {
            windowInsetsControllerCompat.systemBarsBehavior = when (value) {
                BarBehavior.TRANSIENT -> WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                BarBehavior.SWIPE -> WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            }
        }

    override fun setStatusBarColor(
        color: Color,
        darkIcons: Boolean,
        transformColorForLightContent: (Color) -> Color
    ) {
        statusBarDarkContentEnabled = darkIcons

        window.statusBarColor = when {
            darkIcons && !windowInsetsControllerCompat.isAppearanceLightStatusBars -> {
                // If we're set to use dark icons, but our windowInsetsController call didn't
                // succeed (usually due to API level), we instead transform the color to maintain
                // contrast
                transformColorForLightContent(color)
            }

            else -> {
                color
            }
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
            darkIcons && !windowInsetsControllerCompat.isAppearanceLightNavigationBars -> {
                // If we're set to use dark icons, but our windowInsetsController call didn't
                // succeed (usually due to API level), we instead transform the color to maintain
                // contrast
                transformColorForLightContent(color)
            }

            else -> color
        }.toArgb()
    }

    override var statusBarDarkContentEnabled: Boolean
        get() = windowInsetsControllerCompat.isAppearanceLightStatusBars
        set(value) {
            windowInsetsControllerCompat.isAppearanceLightStatusBars = value
        }

    override var navigationBarDarkContentEnabled: Boolean
        get() = windowInsetsControllerCompat.isAppearanceLightNavigationBars
        set(value) {
            windowInsetsControllerCompat.isAppearanceLightNavigationBars = value
        }

    override var isNavigationBarContrastEnforced: Boolean
        get() = Build.VERSION.SDK_INT >= 29 && window.isNavigationBarContrastEnforced
        set(value) {
            if (Build.VERSION.SDK_INT >= 29) {
                window.isNavigationBarContrastEnforced = value
            }
        }

    init {
        previousStateStatusBar = isStatusBarVisible
        previousStateNavigationBar = isNavigationBarVisible
        listenersStatusBar.clear()
        listenersNavigationBar.clear()
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT < 30) {
            view.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

            view.systemUiVisibility = window.decorView.systemUiVisibility and
                    View.SYSTEM_UI_FLAG_FULLSCREEN.inv()

            view.systemUiVisibility = window.decorView.systemUiVisibility and
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION.inv()
        }

        window.addSystemUIVisibilityListener { statusBarVisible, navigationBarVisible ->
            if (statusBarVisible != previousStateStatusBar) {
                listenersStatusBar.forEach {
                    it.invoke(statusBarVisible)
                }
                previousStateStatusBar = statusBarVisible
            }

            if (navigationBarVisible != previousStateNavigationBar) {
                listenersNavigationBar.forEach {
                    it.invoke(navigationBarVisible)
                }
                previousStateNavigationBar = navigationBarVisible
            }
        }
    }

    private fun Window.addSystemUIVisibilityListener(visibilityListener: (statusBar: Boolean, navigationBar: Boolean) -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            decorView.setOnApplyWindowInsetsListener { v, insets ->
                val suppliedInsets = v.onApplyWindowInsets(insets)
                visibilityListener(
                    suppliedInsets.isVisible(WindowInsets.Type.statusBars()),
                    suppliedInsets.isVisible(WindowInsets.Type.navigationBars())
                )
                suppliedInsets
            }
        } else {
            @Suppress("DEPRECATION")
            decorView.setOnSystemUiVisibilityChangeListener {
                val statusBarVisible = (it and View.SYSTEM_UI_FLAG_FULLSCREEN) == 0
                val navigationBarVisible = (it and View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0
                visibilityListener(
                    statusBarVisible,
                    navigationBarVisible
                )
            }
        }
    }

    private fun intToDp(value: Int): Dp {
        val density = window.context.resources.displayMetrics.density
        return (value / density).dp
    }

    override val hasDisplayCutout: Boolean
        get() = windowInsetsCompat?.displayCutout != null

    override val getDisplayCutoutLeft: Dp
        get() = intToDp(windowInsetsCompat?.displayCutout?.safeInsetLeft ?: 0)

    override val getDisplayCutoutRight: Dp
        get() = intToDp(windowInsetsCompat?.displayCutout?.safeInsetRight ?: 0)

    override val getDisplayCutoutTop: Dp
        get() = intToDp(windowInsetsCompat?.displayCutout?.safeInsetTop ?: 0)

    override val getDisplayCutoutBottom: Dp
        get() = intToDp(windowInsetsCompat?.displayCutout?.safeInsetBottom ?: 0)

    override val isPortrait: Boolean
        get() = window.context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT

    override val isLandscape: Boolean
        get() = window.context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    override val getWindowWidth: WindowSize
        get() = when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.COMPACT -> WindowSize.COMPACT
            WindowWidthSizeClass.MEDIUM -> WindowSize.MEDIUM
            WindowWidthSizeClass.EXPANDED -> WindowSize.EXPANDED
            else -> WindowSize.MEDIUM
        }

    override val getWindowHeight: WindowSize
        get() = when (windowSizeClass.windowHeightSizeClass) {
            WindowHeightSizeClass.COMPACT -> WindowSize.COMPACT
            WindowHeightSizeClass.MEDIUM -> WindowSize.MEDIUM
            WindowHeightSizeClass.EXPANDED -> WindowSize.EXPANDED
            else -> WindowSize.MEDIUM
        }

    override var headerBackground: Color = Color.Transparent
    override var sideBackground: Color = Color.Transparent
    override var bodyBackground: Color = Color.Transparent
    override var drawerBackground: Color = Color.Transparent
    override var footerBackground: Color = Color.Transparent
    override var surfaceBackground: Color = Color.Transparent
}