import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yifeplayte.wearablelocalaccount"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.yifeplayte.wearablelocalaccount"
        minSdk = 29
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        applicationVariants.configureEach {
            outputs.configureEach {
                if (this is com.android.build.gradle.internal.api.BaseVariantOutputImpl) {
                    outputFileName = outputFileName.replace("app", rootProject.name).replace(Regex("debug|release"), versionName)
                }
            }
        }
    }

    buildTypes {
        named("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
        }
        named("debug") {
            versionNameSuffix = "-debug-" + DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now())
        }
    }

    androidResources {
        additionalParameters.add("--allow-reserved-package-id")
        additionalParameters.add("--package-id")
        additionalParameters.add("0x45")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    compileOnly("de.robv.android.xposed:api:82")
    implementation("com.github.kyuubiran:EzXHelper:2.0.7")
}