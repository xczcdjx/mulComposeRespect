import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        // Kotlin/Native 有时会把 initXxx 导出成 doInitXxx。
        // KoinInitializer.shared.start()
        KoinIosKt.startKoin()
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}