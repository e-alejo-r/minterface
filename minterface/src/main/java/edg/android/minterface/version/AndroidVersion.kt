package edg.android.minterface.version

import android.os.Build

class AndroidVersion {
    companion object {
        /**
         * Checks if the current Android version matches the specified condition.
         *
         * @param versionCode The API level to check against.
         * @param orHigher If true, checks if the Android version is greater than or equal to the specified version; otherwise, checks if it is exactly the specified version.
         * @return True if the current Android version matches the specified condition, false otherwise.
         */
        private fun checkVersion(versionCode: Int, orHigher: Boolean = false): Boolean {
            return if (orHigher) {
                Build.VERSION.SDK_INT >= versionCode
            } else {
                Build.VERSION.SDK_INT == versionCode
            }
        }

        /**
         * Checks if the current Android version is 14 (Upside Down Cake) or higher.
         *
         * @param orHigher If true, checks if the Android version is 14 or higher; otherwise, checks if it is exactly 14.
         * @return True if the current Android version matches the specified condition, false otherwise.
         */
        fun is14(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.UPSIDE_DOWN_CAKE, orHigher)
        }

        fun is13(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.TIRAMISU, orHigher)
        }

        fun is12L(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.S_V2, orHigher)
        }

        fun is12(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.S, orHigher)
        }

        fun is11(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.R, orHigher)
        }

        fun is10(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.Q, orHigher)
        }

        fun is9(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.P, orHigher)
        }

        fun is81(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.O_MR1, orHigher)
        }

        fun is8(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.O, orHigher)
        }

        fun is71(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.N_MR1, orHigher)
        }

        fun is7(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.N, orHigher)
        }

        fun is6(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.M, orHigher)
        }

        fun is51(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.LOLLIPOP_MR1, orHigher)
        }

        fun is5(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.LOLLIPOP, orHigher)
        }
    }
}