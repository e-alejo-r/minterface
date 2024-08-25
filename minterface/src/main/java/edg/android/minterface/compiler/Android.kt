package edg.android.minterface.compiler

import android.os.Build

/**
 * A class that provides constants for various Android version codes and SDK levels.
 */
class Android() {
    companion object {
        /**
         * Android 5.0 Lollipop version code.
         */
        const val V5 = Build.VERSION_CODES.LOLLIPOP

        /**
         * Android 5.1 Lollipop MR1 version code.
         */
        const val V5MR1 = Build.VERSION_CODES.LOLLIPOP_MR1

        /**
         * Android 6.0 Marshmallow version code.
         */
        const val V6 = Build.VERSION_CODES.M

        /**
         * Android 7.0 Nougat version code.
         */
        const val V7 = Build.VERSION_CODES.N

        /**
         * Android 7.1 Nougat MR1 version code.
         */
        const val V7MR1 = Build.VERSION_CODES.N_MR1

        /**
         * Android 8.0 Oreo version code.
         */
        const val V8 = Build.VERSION_CODES.O

        /**
         * Android 8.1 Oreo MR1 version code.
         */
        const val V8MR1 = Build.VERSION_CODES.O_MR1

        /**
         * Android 9.0 Pie version code.
         */
        const val V9 = Build.VERSION_CODES.P

        /**
         * Android 10.0 Q version code.
         */
        const val V10 = Build.VERSION_CODES.Q

        /**
         * Android 11.0 R version code.
         */
        const val V11 = Build.VERSION_CODES.R

        /**
         * Android 12.0 S version code.
         */
        const val V12 = Build.VERSION_CODES.S

        /**
         * Android 12L S_V2 version code.
         */
        const val V12L = Build.VERSION_CODES.S_V2

        /**
         * Android 13.0 Tiramisu version code.
         */
        const val V13 = Build.VERSION_CODES.TIRAMISU

        /**
         * Android 14.0 Upside Down Cake version code.
         */
        const val V14 = Build.VERSION_CODES.UPSIDE_DOWN_CAKE

        /**
         * Android SDK level 21 (Lollipop).
         */
        const val SDK21 = Build.VERSION_CODES.LOLLIPOP

        /**
         * Android SDK level 22 (Lollipop MR1).
         */
        const val SDK22 = Build.VERSION_CODES.LOLLIPOP_MR1

        /**
         * Android SDK level 23 (Marshmallow).
         */
        const val SDK23 = Build.VERSION_CODES.M

        /**
         * Android SDK level 24 (Nougat).
         */
        const val SDK24 = Build.VERSION_CODES.N

        /**
         * Android SDK level 25 (Nougat MR1).
         */
        const val SDK25 = Build.VERSION_CODES.N_MR1

        /**
         * Android SDK level 26 (Oreo).
         */
        const val SDK26 = Build.VERSION_CODES.O

        /**
         * Android SDK level 27 (Oreo MR1).
         */
        const val SDK27 = Build.VERSION_CODES.O_MR1

        /**
         * Android SDK level 28 (Pie).
         */
        const val SDK28 = Build.VERSION_CODES.P

        /**
         * Android SDK level 29 (Q).
         */
        const val SDK29 = Build.VERSION_CODES.Q

        /**
         * Android SDK level 30 (R).
         */
        const val SDK30 = Build.VERSION_CODES.R

        /**
         * Android SDK level 31 (S).
         */
        const val SDK31 = Build.VERSION_CODES.S

        /**
         * Android SDK level 32 (S_V2).
         */
        const val SDK32 = Build.VERSION_CODES.S_V2

        /**
         * Android SDK level 33 (Tiramisu).
         */
        const val SDK33 = Build.VERSION_CODES.TIRAMISU

        /**
         * Android SDK level 34 (Upside Down Cake).
         */
        const val SDK34 = Build.VERSION_CODES.UPSIDE_DOWN_CAKE

        /**
         * Returns the current SDK level of the device.
         *
         * @return The current SDK level.
         */
        fun sdk(): Int {
            return Build.VERSION.SDK_INT
        }

        /**
         * Returns the current SDK level of the device.
         *
         * @return The current SDK level.
         */
        fun version(): Int {
            return Build.VERSION.SDK_INT
        }
    }
}


