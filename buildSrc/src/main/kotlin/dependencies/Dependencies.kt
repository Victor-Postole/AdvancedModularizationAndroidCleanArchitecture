package dependencies

object Dependencies {
    const val ANDROID_CORE = "androidx.core:core-ktx:${DependenciesVersions.CORE_KTX}"
    const val ANDROID_LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.LIFECYCLE_RUNTIME_KTX}"
    const val ANDROID_ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${DependenciesVersions.ACTIVITY_COMPOSE}"
    const val ANDROIDX_UI = "androidx.compose.ui:ui:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_GRAPHICS = "androidx.compose.ui:ui-graphics:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_MATERIAL3 = "androidx.compose.material3:material3:${DependenciesVersions.MATERIAL3}"

    const val hiltAndroid = "com.google.dagger:hilt-android:${DependenciesVersions.HILT}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${DependenciesVersions.HILT}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${DependenciesVersions.HILT}"
    const val hiltCompose = "androidx.hilt:hilt-work:${DependenciesVersions.HILT_COMPOSE}"
    const val hiltCompilerKapt = "androidx.hilt:hilt-compiler:${DependenciesVersions.HILT_COMPOSE}"
    const val hiltNavigation =
        "androidx.hilt:hilt-navigation-compose:${DependenciesVersions.HILT_COMPOSE}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT}"
    const val retrofitConverterGson =
        "com.squareup.retrofit2:converter-gson:${DependenciesVersions.RETROFIT}"
    const val retrofitKotlinCoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${DependenciesVersions.RETROFIT_COROUTINE_ADAPTER_VERSION}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${DependenciesVersions.OKHTTP}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.OKHTTP}"

    const val roomRuntime = "androidx.room:room-runtime:${DependenciesVersions.ROOM}"
    const val roomCompiler = "androidx.room:room-compiler:${DependenciesVersions.ROOM}"
    const val roomKtx = "androidx.room:room-ktx:${DependenciesVersions.ROOM}"
}