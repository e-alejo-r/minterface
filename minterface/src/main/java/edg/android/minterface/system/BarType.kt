package edg.android.minterface.system

enum class BarType {
    STATUS,
    NAVIGATION,
    SYSTEM;

    fun toInt(): Int {
        return when (this) {
            STATUS -> 0
            NAVIGATION -> 1
            SYSTEM -> 2
        }
    }

    companion object {
        fun fromInt(value: Int): BarType {
            return when (value) {
                0 -> STATUS
                1 -> NAVIGATION
                2 -> SYSTEM
                else -> throw IllegalArgumentException("Invalid value for BarType: $value")
            }
        }
    }
}