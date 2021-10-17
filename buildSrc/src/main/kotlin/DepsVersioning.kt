object DepsVersioning {

    const val SDK_MIN = 14
    const val SDK_TARGET = 30
    const val BUILD_TOOLS_VERSION = "30.0.3"

    object Google {
        private const val annotation_version = "1.2.0"
        private const val app_compat = "1.3.1"
        private const val view_pager = "1.0.0"

        const val ANNOTATIONS = "androidx.annotation:annotation:$annotation_version"
        const val APP_COMPAT = "androidx.appcompat:appcompat:$app_compat"
        const val APP_COMPAT_RESOURCES = "androidx.appcompat:appcompat-resources:$app_compat"
        const val VIEW_PAGER = "androidx.viewpager2:viewpager2:$view_pager"
    }

    object External {

    }


}