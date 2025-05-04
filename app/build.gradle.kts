plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.dagger.hilt.android )
}

android {
    namespace = "com.rtllabs.githubappexplore"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rtllabs.githubappexplore"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "githubAPI", "\"https://api.github.com/\"")
        buildConfigField("String", "githubToken", "\"\"")
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
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
    /*kapt {
        correctErrorTypes true
    }*/


}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.retrofit.com.squareup)
    implementation(libs.retrofit.com.squareup.logging.interceptor)
    implementation(libs.retrofit.com.squareup.converter.gson)

    /*implementation(libs.dagger.com.google)
    implementation(libs.dagger.com.google.dagger.android)
    implementation(libs.dagger.com.google.dagger.android.support)
    implementation(libs.dagger.com.google.dagger.compiler)*/
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.hilt.android)
    kapt(libs.dagger.compiler)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
