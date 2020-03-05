package net.orgiu.tests.nightmode

enum class ImageContext(val type: Int) {
    APPLICATION(0),
    ACTIVITY(1);

    companion object {
        fun fromType(type: Int) =
            if (type == 0) {
                APPLICATION
            } else {
                ACTIVITY
            }
    }
}