package edg.android.minterface.version

import android.os.Build

/**
 * Utility class for checking the Android version.
 */
class AndroidVersion {
    companion object {
        /**
         * Checks if the current Android version matches the specified version code.
         *
         * @param versionCode The version code to check against.
         * @param orHigher If true, checks if the current version is equal to or higher than the specified version code.
         * @return True if the current version matches the criteria, false otherwise.
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
         * @param orHigher If true, checks if the current version is equal to or higher than 14.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is14(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.UPSIDE_DOWN_CAKE, orHigher)
        }

        /**
         * Checks if the current Android version is 13 (Tiramisu) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 13.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is13(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.TIRAMISU, orHigher)
        }

        /**
         * Checks if the current Android version is 12L (S_V2) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 12L.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is12L(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.S_V2, orHigher)
        }

        /**
         * Checks if the current Android version is 12 (S) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 12.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is12(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.S, orHigher)
        }

        /**
         * Checks if the current Android version is 11 (R) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 11.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is11(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.R, orHigher)
        }

        /**
         * Checks if the current Android version is 10 (Q) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 10.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is10(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.Q, orHigher)
        }

        /**
         * Checks if the current Android version is 9 (P) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 9.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is9(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.P, orHigher)
        }

        /**
         * Checks if the current Android version is 8.1 (O_MR1) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 8.1.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is81(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.O_MR1, orHigher)
        }

        /**
         * Checks if the current Android version is 8 (O) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 8.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is8(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.O, orHigher)
        }

        /**
         * Checks if the current Android version is 7.1 (N_MR1) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 7.1.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is71(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.N_MR1, orHigher)
        }

        /**
         * Checks if the current Android version is 7 (N) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 7.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is7(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.N, orHigher)
        }

        /**
         * Checks if the current Android version is 6 (M) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 6.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is6(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.M, orHigher)
        }

        /**
         * Checks if the current Android version is 5.1 (LOLLIPOP_MR1) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 5.1.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is51(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.LOLLIPOP_MR1, orHigher)
        }

        /**
         * Checks if the current Android version is 5 (LOLLIPOP) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 5.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is5(orHigher: Boolean = false): Boolean {
            return checkVersion(Build.VERSION_CODES.LOLLIPOP, orHigher)
        }
    }
}