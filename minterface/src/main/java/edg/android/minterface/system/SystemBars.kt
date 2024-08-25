package edg.android.minterface.system

import android.os.Build
import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

/**
 * A class that manages the visibility and behavior of system bars (status bar, navigation bar, etc.) for a given window.
 *
 * @property window The window for which the system bars are being managed.
 */
class SystemBars(
    private val window: Window
) {
    private val insetsController = WindowCompat.getInsetsController(window, window.decorView)

    /**
     * Sets the behavior of the system bars when they are hidden.
     *
     * @param showBar The behavior to set when the system bars are hidden.
     */
    fun setHiddenModeBehavior(showBar: ShowBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            when (showBar) {
                ShowBar.TRANSIENT -> {
                    insetsController.systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }

                ShowBar.SWIPE -> {
                    insetsController.systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                }
            }
        } else {
            when (showBar) {
                ShowBar.TRANSIENT -> {
                    window.decorView.systemUiVisibility =
                        View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                }

                ShowBar.SWIPE -> {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                }
            }
        }
    }

    /**
     * Hides the specified type of system bar.
     *
     * @param barType The type of system bar to hide.
     */
    fun hide(barType: BarType) {
        when (barType) {
            BarType.STATUS -> {
                insetsController.hide(WindowInsetsCompat.Type.statusBars())
            }

            BarType.NAVIGATION -> {
                insetsController.hide(WindowInsetsCompat.Type.navigationBars())
            }

            BarType.SYSTEM -> {
                insetsController.hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    /**
     * Shows the specified type of system bar.
     *
     * @param barType The type of system bar to show.
     */
    fun show(barType: BarType) {
        when (barType) {
            BarType.STATUS -> {
                insetsController.show(WindowInsetsCompat.Type.statusBars())
            }

            BarType.NAVIGATION -> {
                insetsController.show(WindowInsetsCompat.Type.navigationBars())
            }

            BarType.SYSTEM -> {
                insetsController.show(WindowInsetsCompat.Type.systemBars())
            }
        }
    }
}
