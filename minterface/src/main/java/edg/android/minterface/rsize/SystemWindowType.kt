package edg.android.minterface.rsize

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

/**
 * Remembers the system window size class and provides a function to get the window type based on the measure (width or height).
 *
 * @return A function that takes a Measure (WIDTH or HEIGHT) and returns the corresponding WindowType (COMPACT, MEDIUM, or EXPANDED).
 */
@Composable
fun rememberSystemWindowType(): (measure: Measure) -> WindowType {
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    return remember {
        { measure: Measure ->
            when (measure) {
                Measure.WIDTH -> {
                    when (windowSizeClass.windowWidthSizeClass) {
                        WindowWidthSizeClass.COMPACT -> WindowType.COMPACT
                        WindowWidthSizeClass.MEDIUM -> WindowType.MEDIUM
                        WindowWidthSizeClass.EXPANDED -> WindowType.EXPANDED
                        else -> WindowType.MEDIUM
                    }
                }

                Measure.HEIGHT -> {
                    when (windowSizeClass.windowHeightSizeClass) {
                        WindowHeightSizeClass.COMPACT -> WindowType.COMPACT
                        WindowHeightSizeClass.MEDIUM -> WindowType.MEDIUM
                        WindowHeightSizeClass.EXPANDED -> WindowType.EXPANDED
                        else -> WindowType.MEDIUM
                    }
                }
            }
        }
    }
}
