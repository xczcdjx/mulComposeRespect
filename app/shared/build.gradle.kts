import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask
import kotlin.jvm.java

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.koin.compiler)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
            binaryOption("bundleId", "com.djx.mulcomposerespect.shared")
        }
    }
    jvm()

    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    androidLibrary {
        namespace = "com.djx.mulcomposerespect.app.shared"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()

        compilerOptions {
            jvmTarget = JvmTarget.JVM_11
        }
        androidResources {
            enable = true
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.compose.uiToolingPreview)
            // Ktor Android engine
            implementation(libs.ktor.client.okhttp)
        }
        jvmMain.dependencies {
            // Ktor JVM/Desktop engine
            implementation(libs.ktor.client.java)
        }
        iosMain.dependencies {
            // Ktor iOS engine
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            api(projects.core)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            // koin
            api(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core) // core
            implementation(libs.koin.compose) // inject
            implementation(libs.koin.core.viewmodel) // ViewModel 核心能力
            implementation(libs.koin.compose.viewmodel) // viewmodel
            implementation(libs.koin.annotations) // 注解包
            // navigation
            implementation(libs.navigation.compose)
            // ktorfit
            implementation(libs.ktorfit)
            implementation(libs.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.content.negotiation)
            implementation(libs.kotlinx.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jsMain.dependencies {
            implementation(libs.wrappers.browser)
            implementation(libs.ktor.client.js)
        }
        wasmJsMain.dependencies {
            // Ktor WasmJs 一般也用 js engine
            implementation(libs.ktor.client.js)
        }
    }
}
dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)

/*    add("kspCommonMainMetadata", libs.ktorfit.compiler)
    add("kspAndroid", libs.ktorfit.compiler)
    add("kspIosSimulatorArm64", libs.ktorfit.compiler)
    add("kspIosX64", libs.ktorfit.compiler)
    add("kspIosArm64", libs.ktorfit.compiler)*/
}


compose.desktop {
    application {
        mainClass = "com.djx.mulcomposerespect.MainKt"
    }
}