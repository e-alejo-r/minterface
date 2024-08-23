package edg.android.minterface.rsize

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

/**
 * Remembers the window size based on the provided measure.
 *
 * @param measure The measure to determine the window size (WIDTH or HEIGHT).
 * @return The window type based on the current window size class.
 */
@Composable
fun rememberWindowSize(measure: Measure): WindowSize {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    val windowSize = remember {
        { measure: Measure ->
            when (measure) {
                Measure.WIDTH -> {
                    when (windowSizeClass.windowWidthSizeClass) {
                        WindowWidthSizeClass.COMPACT -> WindowSize.COMPACT
                        WindowWidthSizeClass.MEDIUM -> WindowSize.MEDIUM
                        WindowWidthSizeClass.EXPANDED -> WindowSize.EXPANDED
                        else -> WindowSize.MEDIUM
                    }
                }

                Measure.HEIGHT -> {
                    when (windowSizeClass.windowHeightSizeClass) {
                        WindowHeightSizeClass.COMPACT -> WindowSize.COMPACT
                        WindowHeightSizeClass.MEDIUM -> WindowSize.MEDIUM
                        WindowHeightSizeClass.EXPANDED -> WindowSize.EXPANDED
                        else -> WindowSize.MEDIUM
                    }
                }
            }
        }
    }
    return windowSize(measure)
}
