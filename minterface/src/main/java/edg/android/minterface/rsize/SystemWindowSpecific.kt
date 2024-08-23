package edg.android.minterface.rsize

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

/**
 * Remembers the system window size class and provides functions to check if the window size is compact, medium, or expanded.
 *
 * @return A Triple containing three functions:
 * - The first function checks if the window size is compact.
 * - The second function checks if the window size is medium.
 * - The third function checks if the window size is expanded.
 */
@Composable
fun rememberSystemWindowSpecifics(): Triple<(Measure) -> Boolean, (Measure) -> Boolean, (Measure) -> Boolean> {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    val isCompact = remember {
        { measure: Measure ->
            when (measure) {
                Measure.WIDTH -> {
                    windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
                }

                Measure.HEIGHT -> {
                    windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.COMPACT
                }
            }
        }
    }
    val isMedium = remember {
        { measure: Measure ->
            when (measure) {
                Measure.WIDTH -> {
                    windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM
                }

                Measure.HEIGHT -> {
                    windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.MEDIUM
                }
            }
        }
    }
    val isExpanded = remember {
        { measure: Measure ->
            when (measure) {
                Measure.WIDTH -> {
                    windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED
                }

                Measure.HEIGHT -> {
                    windowSizeClass.windowHeightSizeClass == WindowHeightSizeClass.EXPANDED
                }
            }
        }
    }
    return Triple(isCompact, isMedium, isExpanded)
}
