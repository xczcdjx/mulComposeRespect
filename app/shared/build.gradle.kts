import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
//    alias(libs.plugins.ksp)
    alias(libs.plugins.koin.compiler)
}

kotlin {
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
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
            implementation(libs.koin.android)
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
            // ktorfit

        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jsMain.dependencies {
            implementation(libs.wrappers.browser)
        }
    }
    // KSP Common sourceSet
//    sourceSets.named("commonMain").configure {
//        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
//    }
}
//ksp {
//    arg("KOIN_DEFAULT_MODULE", "true")
//}
dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)
//    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
//
//    add("kspAndroid", libs.koin.ksp.compiler)
//    add("kspJvm", libs.koin.ksp.compiler)
//
//    add("kspIosArm64", libs.koin.ksp.compiler)
//    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
//
//    add("kspJs", libs.koin.ksp.compiler)
//    add("kspWasmJs", libs.koin.ksp.compiler)
}
//tasks.matching {
//    it.name.startsWith("ksp") && it.name != "kspCommonMainKotlinMetadata"
//}.configureEach {
//    dependsOn("kspCommonMainKotlinMetadata")
//}
compose.desktop {
    application {
        mainClass = "com.djx.mulcomposerespect.MainKt"
    }
}