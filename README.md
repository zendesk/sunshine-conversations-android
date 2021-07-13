# [Smooch SDK for Android](http://www.smooch.io)

[![Bintray](https://api.bintray.com/packages/smoochorg/maven/smooch/images/download.svg)](https://bintray.com/smoochorg/maven/smooch/view#files)
[![License](http://img.shields.io/cocoapods/l/Smooch.svg)](https://smooch.io/terms)

Sign up and get an integration id: [https://app.smooch.io/signup](https://app.smooch.io/signup).

Add the dependencies right into your `build.gradle` file

```
implementation 'io.smooch:core:8.2.2'
implementation 'io.smooch:ui:8.2.2'
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
    implementation 'com.google.firebase:firebase-messaging:21.0.1'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.okhttp3:okhttp:3.12.6'
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
    implementation 'com.google.dagger:dagger:2.28.3'

    annotationProcessor 'com.google.dagger:dagger-compiler:2.28.3'

    // UI dependencies
    implementation 'com.github.bumptech.glide:glide:4.11.0'
    implementation 'com.google.android.gms:play-services-location:17.1.0'
    implementation 'com.davemorrissey.labs:subsampling-scale-image-view-androidx:3.10.0'
```

If your app also uses Firebase or support libraries, their version must match the ones used by Smooch to avoid runtime errors. If you need to update the version used by Smooch to match yours, you can do so by updating the dependencies in your app's `build.gradle` file.

```

dependencies {
    // Smooch
    implementation 'io.smooch:core:8.2.2'
    implementation 'io.smooch:ui:8.2.2'

    // Libraries imported by Smooch
    implementation "com.google.firebase:firebase-messaging:21.0.1"
    implementation "androidx.appcompat:appcompat:1.2.0"
    implementation "androidx.legacy:legacy-support-v4:1.0.0"
    implementation "androidx.exifinterface:exifinterface:1.3.2"
    implementation "androidx.recyclerview:recyclerview:1.1.0"
    implementation "androidx.media:media:1.2.1"
    implementation "com.google.android.gms:play-services-location:17.1.0"

    // Your other app dependencies
}

```
