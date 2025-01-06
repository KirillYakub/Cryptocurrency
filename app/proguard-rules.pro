-keep class retrofit.** { *; }
-keep class resources.**{*;}
-keep class sources.** {*;}
-keep class com.google.gson.** { *; }
-keep class com.squareup.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

-keep,allowobfuscation,allowshrinking class retrofit2.Response

-keepattributes *Annotation*
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

-keep interface com.rapido.passen.network.** { *; }
-keep class com.rapido.passen.models.** {
    *;
}
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
-keepclassmembers class * {
    public <init>(...);
}