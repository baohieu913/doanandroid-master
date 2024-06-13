plugins {
    alias(libs.plugins.androidApplication)
    id("com.google.gms.google-services")
    id("com.onesignal.androidsdk.onesignal-gradle-plugin")
}

android {
    namespace = "com.example.dating"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dating"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["onesignal_app_id"] = "09bd6e49-3cf4-4328-964c-6f17934b6924"
        manifestPlaceholders["onesignal_google_project_number"] = "1082564341120"


    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.annotation)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.onesignal:OneSignal:2.4.0")


//Add these lines to fix the error.
    implementation("com.google.firebase:firebase-analytics:22.0.0")
    implementation("com.google.firebase:firebase-firestore:25.0.0")

    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("com.google.firebase:firebase-storage:21.0.0")
    implementation("com.google.firebase:firebase-messaging:24.0.0")

    //implementation("com.github.bumptech.glide:glide:3.7.0")
    implementation("com.lorentzos.swipecards:library:1.0.9")
    implementation("com.google.firebase:firebase-core:21.1.1")
    implementation("com.google.android.material:material:1.1.0-alpha03")
    implementation("com.google.firebase:firebase-database:21.0.0")
    //navigation
    implementation("com.github.ittianyu:BottomNavigationViewEx:1.2.4")

    //Circle ImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.github.dimorinny:show-case-card-view:0.0.4")

    implementation("com.github.BakerJQ:Android-InfiniteCards:1.0.5")
    //implementation("com.google.firebase:firebase-ads:19.7.0")

//    implementation 'com.google.android.gms:play-services-ads:19.7.0'
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("com.google.android.gms:play-services-fitness:21.2.0")

    implementation("com.google.android.gms:play-services-basement-license:12.0.1")
    implementation("com.android.support:support-v4:28.0.0")

    implementation("com.github.bumptech.glide:glide:3.7.0")

}



