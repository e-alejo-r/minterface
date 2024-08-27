package edg.android.minterface.system

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalView
import androidx.core.app.ComponentActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun rememberSystemBarController(): SystemBarController {
    val window = (LocalView.current.context as Activity).window
    return  remember<SystemBarController>(window) {
        AndroidSystemBarController(window)
    }
}

fun ComponentActivity.configSystemBars() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val systemBars = ViewCompat
            .getRootWindowInsets(window.decorView)
            ?.isVisible(WindowInsetsCompat.Type.systemBars()) == true
        if (!systemBars) {
            val windowInsetsController = WindowInsetsControllerCompat(window, window.decorView)
            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        }
    }
}
