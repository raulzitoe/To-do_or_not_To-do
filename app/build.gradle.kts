//plugins {
//    id 'com.android.application'
//    id 'org.jetbrains.kotlin.android'
//    id 'kotlin-kapt'
//    id 'androidx.navigation.safeargs'
//}
//
//android {
//    compileSdk 34
//
//    defaultConfig {
//        applicationId "com.group.to_doornotto_do"
//        minSdk 23
//        targetSdk 34
//        versionCode 4
//        versionName "1.0.3"
//        ndk.debugSymbolLevel = 'FULL'
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//    }
//
//    buildTypes {
//        release {
//            debuggable false
//            minifyEnabled true
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//
//        debug {
//            debuggable true
//            minifyEnabled false
//        }
//    }
//
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_17
//        targetCompatibility JavaVersion.VERSION_17
//    }
//
//    buildFeatures {
//        viewBinding true
//    }
//
//    namespace 'com.group.to_doornotto_do'
//}
//
//dependencies {
//    implementation 'androidx.core:core-ktx:1.13.1'
//
//    def nav_version = "2.7.7"
//    def activityVersion = '1.9.1'
//    def appCompatVersion = '1.7.0'
//    def constraintLayoutVersion = '2.1.4'
//    def lifecycleVersion = '2.8.4'
//    def materialVersion = '1.12.0'
//    def roomVersion = '2.6.1'
//
//    // Java language implementation
//    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
//    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
//
//    // Kotlin
//    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
//    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
//
//    implementation "androidx.appcompat:appcompat:$appCompatVersion"
//    implementation "androidx.activity:activity-ktx:$activityVersion"
//
//    // Room components
//    implementation "androidx.room:room-ktx:$roomVersion"
//    kapt "androidx.room:room-compiler:$roomVersion"
//    androidTestImplementation "androidx.room:room-testing:$roomVersion"
//
//    // Lifecycle components
//    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
//    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
//
//    // UI
//    implementation "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
//    implementation "com.google.android.material:material:$materialVersion"
//
//    // Gson
//    implementation 'com.google.code.gson:gson:2.10.1'
//}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "com.group.to_doornotto_do"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.group.to_doornotto_do"
        minSdk = 26
        targetSdk = 34
        versionCode = 4
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.google.gson)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}