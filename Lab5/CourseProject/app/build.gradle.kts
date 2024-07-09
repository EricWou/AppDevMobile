import java.io.FileInputStream
import java.util.Properties

val localProperties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "com.example.courseproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.courseproject"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        
    }

    buildTypes {
        //for deployment
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            buildConfigField("String", "GOOGLE_API_KEY", "\"AIzaSyC3Z0OYU8cmc980OemFLNkiFCBbVV6mnzk\"")
        }
        //for testing (ex: if wants a separate API_KEY for testing purposes)
        debug {
            buildConfigField("String", "GOOGLE_API_KEY", "\"AIzaSyC3Z0OYU8cmc980OemFLNkiFCBbVV6mnzk\"")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.maps)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.loopj.android:android-async-http:1.4.9")
    //after adding manually the implementation, need to manually "Sync Project with Gradle Files"
}