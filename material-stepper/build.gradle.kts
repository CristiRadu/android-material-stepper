plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}

android {
    compileSdk = DepsVersioning.SDK_TARGET

    defaultConfig {
        minSdk = DepsVersioning.SDK_MIN
        targetSdk = DepsVersioning.SDK_TARGET
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        debug {
        }
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
    }

}

dependencies {
    implementation(DepsVersioning.Google.ANNOTATIONS)
    implementation(DepsVersioning.Google.APP_COMPAT)
    implementation(DepsVersioning.Google.APP_COMPAT_RESOURCES)
    implementation(DepsVersioning.Google.VIEW_PAGER)

    testImplementation("org.robolectric:robolectric:4.6")
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.squareup.assertj:assertj-android:1.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.stepstone.stepper"
                artifactId = "material-stepper"
                version = "5.0.0"

                from(components["release"])

                pom {
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                }
            }
        }
    }
}