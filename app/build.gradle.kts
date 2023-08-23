import java.util.Properties

val localProperties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.mapsplatformPlugin)
}

android {
    namespace = "com.dawinder.googlemaps_jetpackcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dawinder.googlemaps_jetpackcompose"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Jetpack Compose Platform
    implementation(platform(libs.compose.bom))
    implementation(libs.play.services.maps)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    androidTestImplementation(platform(libs.compose.bom))

    // Jetpack Compose Libraries with BOM version
    implementation(libs.bundles.compose)

    // Other Jetpack Compose Libraries
    implementation(libs.bundles.otherCompose)

    // Core Libraries
    implementation(libs.core.ktx)

    // Google Maps Libraries
    implementation(libs.bundles.googleMaps)

    //JUnit Test Libraries
    testImplementation(libs.junit)

    //Android Test Libraries
    androidTestImplementation(libs.bundles.androidTest)

    //Debug Libraries
    debugImplementation(libs.bundles.debug)
}