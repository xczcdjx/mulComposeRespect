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
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.kotlinSerialization)
    // ksp
    alias(libs.plugins.ksp)
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

/*    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }*/

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
            implementation(libs.koin.android)
            implementation(libs.permissions)
            implementation(libs.permissions.compose)
            implementation(libs.permissions.camera)
        }
        jvmMain.dependencies {
            // Ktor JVM/Desktop engine
            implementation(libs.ktor.client.java)
            implementation(libs.logback)
        }
        iosMain.dependencies {
            // Ktor iOS engine
            implementation(libs.ktor.client.darwin)
            implementation(libs.permissions)
            implementation(libs.permissions.compose)
            implementation(libs.permissions.camera)
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

            // icon
            implementation(libs.material.icons.extended)

            // koin
            api(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core) // core
            implementation(libs.koin.compose) // inject
            implementation(libs.koin.core.viewmodel) // ViewModel 核心能力
            implementation(libs.koin.compose.viewmodel) // viewmodel
            api(libs.koin.annotations) // 注解包
//            ksp(libs.koin.ksp.compiler)
            // navigation
            implementation(libs.navigation.compose)
            // ktorfit
            implementation(libs.ktorfit)
            implementation(libs.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.kotlinx.json)
            // 日志
            implementation(libs.kermit)
            // toast
            implementation(libs.compose.sonner)
            // datastore
            // DataStore library
            implementation(libs.androidx.datastore)
            // The Preferences DataStore library
            implementation(libs.androidx.datastore.preferences)
            // 扫码
            implementation(libs.kscan)
            /*// permission
            implementation(libs.permissions)
            implementation(libs.permissions.compose)
            implementation(libs.permissions.camera)*/
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        /*jsMain.dependencies {
            implementation(libs.wrappers.browser)
            implementation(libs.ktor.client.js)
        }
        wasmJsMain.dependencies {
            // Ktor WasmJs 一般也用 js engine
            implementation(libs.ktor.client.js)
        }*/
    }
    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }
}
dependencies {
    androidRuntimeClasspath(libs.compose.uiTooling)

    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
    add("kspAndroid", libs.koin.ksp.compiler)
    add("kspIosSimulatorArm64", libs.koin.ksp.compiler)
    add("kspIosArm64", libs.koin.ksp.compiler)

    add("kspCommonMainMetadata", libs.ktorfit.compiler)
    add("kspAndroid", libs.ktorfit.compiler)
    add("kspIosSimulatorArm64", libs.ktorfit.compiler)
    add("kspIosArm64", libs.ktorfit.compiler)
}
ksp {
    arg("KOIN_DEFAULT_MODULE","true")
}
tasks.matching { it.name.startsWith("ksp") && it.name != "kspCommonMainKotlinMetadata" }.configureEach {
    dependsOn("kspCommonMainKotlinMetadata")
}
//compose.resources {
//    packageOfResClass = "mulcomposerespect.app.generated.resources"
//}
compose.desktop {
    application {
        mainClass = "com.djx.mulcomposerespect.MainKt"
    }
}