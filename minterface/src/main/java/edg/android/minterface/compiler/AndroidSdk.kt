package edg.android.minterface.compiler

class AndroidSdk {
    companion object {
        /**
         * Checks if the current Android version is 14 (Upside Down Cake) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 14.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is34(orHigher: Boolean): Boolean {
            return AndroidVersion.is14(orHigher)
        }

        /**
         * Checks if the current Android version is 13 (Tiramisu) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 13.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is33(orHigher: Boolean): Boolean {
            return AndroidVersion.is13(orHigher)
        }

        /**
         * Checks if the current Android version is 12L (S\_V2) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 12L.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is32(orHigher: Boolean): Boolean {
            return AndroidVersion.is12L(orHigher)
        }

        /**
         * Checks if the current Android version is 12 (S) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 12.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is31(orHigher: Boolean): Boolean {
            return AndroidVersion.is12(orHigher)
        }

        /**
         * Checks if the current Android version is 11 (R) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 11.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is30(orHigher: Boolean): Boolean {
            return AndroidVersion.is11(orHigher)
        }

        /**
         * Checks if the current Android version is 10 (Q) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 10.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is29(orHigher: Boolean): Boolean {
            return AndroidVersion.is10(orHigher)
        }

        /**
         * Checks if the current Android version is 9 (P) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 9.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is28(orHigher: Boolean): Boolean {
            return AndroidVersion.is9(orHigher)
        }

        /**
         * Checks if the current Android version is 8.1 (O\_MR1) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 8.1.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is27(orHigher: Boolean): Boolean {
            return AndroidVersion.is81(orHigher)
        }

        /**
         * Checks if the current Android version is 8 (O) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 8.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is26(orHigher: Boolean): Boolean {
            return AndroidVersion.is8(orHigher)
        }

        /**
         * Checks if the current Android version is 7.1 (N\_MR1) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 7.1.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is25(orHigher: Boolean): Boolean {
            return AndroidVersion.is71(orHigher)
        }

        /**
         * Checks if the current Android version is 7 (N) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 7.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is24(orHigher: Boolean): Boolean {
            return AndroidVersion.is7(orHigher)
        }

        /**
         * Checks if the current Android version is 6 (M) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 6.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is23(orHigher: Boolean): Boolean {
            return AndroidVersion.is6(orHigher)
        }

        /**
         * Checks if the current Android version is 5.1 (LOLLIPOP\_MR1) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 5.1.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is22(orHigher: Boolean): Boolean {
            return AndroidVersion.is51(orHigher)
        }

        /**
         * Checks if the current Android version is 5 (LOLLIPOP) or higher.
         *
         * @param orHigher If true, checks if the current version is equal to or higher than 5.
         * @return True if the current version matches the criteria, false otherwise.
         */
        fun is21(orHigher: Boolean): Boolean {
            return AndroidVersion.is5(orHigher)
        }
    }
}