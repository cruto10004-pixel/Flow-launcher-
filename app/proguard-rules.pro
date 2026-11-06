-keep class com.flow.launcher.** { *; }
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
