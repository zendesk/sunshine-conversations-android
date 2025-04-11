# [Smooch SDK for Android](http://www.smooch.io)

[![Bintray](https://api.bintray.com/packages/smoochorg/maven/smooch/images/download.svg)](https://zendesk.jfrog.io/ui/native/repo/io/smooch/)
[![License](http://img.shields.io/cocoapods/l/Smooch.svg)](https://smooch.io/terms)

Sign up and get an integration id: [https://app.smooch.io/signup](https://app.smooch.io/signup).

Add the dependencies right into your `build.gradle` file

```
implementation 'io.smooch:core:9.7.0'
implementation 'io.smooch:ui:9.7.0'
```

Initialize Smooch in your [Application](https://developer.android.com/reference/android/app/Application.html) class

```java
import android.app.Application;
import io.smooch.core.Settings;
import io.smooch.core.Smooch;
import io.smooch.core.SmoochCallback;

public class YourApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Smooch.init(this, new Settings("YOUR_INTEGRATION_ID"), new SmoochCallback<InitializationStatus>() {
            @Override
            public void run(Response<InitializationStatus> response) {
                // Handle init result
            }
        });
    }
}
```

Show the ConversationActivity

```java
import io.smooch.ui.ConversationActivity;

...

ConversationActivity.builder().show(this);
```

For more details see our [docs and full install guide](https://docs.smooch.io/guide/native-android-sdk/).

# Dependencies

Smooch uses the following dependencies:

```
    // Core dependencies
    implementation 'com.google.firebase:firebase-messaging:24.1.0'
    implementation 'com.google.code.gson:gson:2.10.1'
    implementation 'com.squareup.okhttp3:okhttp:4.12.0'
    implementation 'com.squareup.retrofit2:retrofit:2.11.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.11.0'
    implementation 'com.google.dagger:dagger:2.51.1'

    annotationProcessor 'com.google.dagger:dagger-compiler:2.51.1'

    // UI dependencies
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    implementation 'com.google.android.gms:play-services-location:21.3.0'
    implementation 'com.davemorrissey.labs:subsampling-scale-image-view-androidx:3.10.0'
```

If your app also uses Firebase or support libraries, their version must match the ones used by Smooch to avoid runtime errors. If you need to update the version used by Smooch to match yours, you can do so by updating the dependencies in your app's `build.gradle` file.

```

dependencies {
    // Smooch
    implementation 'io.smooch:core:9.7.0'
    implementation 'io.smooch:ui:9.7.0'

    // Libraries imported by Smooch
    implementation "com.google.firebase:firebase-messaging:24.1.0"
    implementation "androidx.appcompat:appcompat:1.7.0"
    implementation "androidx.legacy:legacy-support-v4:1.0.0"
    implementation "androidx.exifinterface:exifinterface:1.3.7"
    implementation "androidx.recyclerview:recyclerview:1.3.2"
    implementation "androidx.media:media:1.7.0"
    implementation "com.google.android.gms:play-services-location:21.3.0"

    // Your other app dependencies
}

```
