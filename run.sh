# desktop 热重载
./gradlew  :app:desktopApp:hotRun --auto
# desktop run
./gradlew :app:desktopApp:run
# 静态资源生成
./gradlew :app:shared:genComposeRes
# Android Debug
./gradlew :app:androidApp:installDebug && \
adb shell am start -n com.djx.mulcomposerespect/.MainActivity
# Ios
#./gradlew :iosApp:embedAndSignAppleFrameworkForXcode
#open iosApp/iosApp.xcodeproj