# [Smooch SDK for Android](http://www.smooch.io)

[![Bintray](https://api.bintray.com/packages/smoochorg/maven/smooch/images/download.svg)](https://bintray.com/smoochorg/maven/smooch/view#files)
[![License](http://img.shields.io/cocoapods/l/Smooch.svg)](https://smooch.io/terms)

Sign up and get an integration id: [https://app.smooch.io/signup](https://app.smooch.io/signup).

Add the dependencies right into your `build.gradle` file

```
implementation 'io.smooch:core:7.0.2'
implementation 'io.smooch:ui:7.0.2'
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
    implementation 'com.google.firebase:firebase-messaging:18.0.0'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.squareup.okhttp3:okhttp:3.12.6'
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
    implementation 'com.google.dagger:dagger:2.25.2'
    implementation 'com.android.support:support-annotations:28.0.0'

    annotationProcessor 'com.google.dagger:dagger-compiler:2.25.2'

    // UI dependencies
    implementation 'com.github.bumptech.glide:glide:4.9.0'
    implementation 'com.android.support:support-v4:28.0.0'
    implementation 'com.android.support:appcompat-v7:28.0.0'
    implementation 'com.android.support:exifinterface:28.0.0'
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'com.android.support:support-media-compat:28.0.0'
    implementation 'com.google.android.gms:play-services-location:16.0.0'
    implementation 'com.davemorrissey.labs:subsampling-scale-image-view:3.10.0'
```

If your app also uses Firebase or support libraries, their version must match the ones used by Smooch to avoid runtime errors. If you need to update the version used by Smooch to match yours, you can do so by updating the dependencies in your app's `build.gradle` file.

```
def supportLibraryVersion = "YOUR_SUPPORT_LIBRARY_VERSION"
def firebaseMessagingVersion = "YOUR_FIREBASE_MESSAGING_VERSION"
def playServicesVersion = "YOUR_PLAY_SERVICES_VERSION"

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "com.android.support:appcompat-v7:${supportLibraryVersion}"

    // Smooch
    implementation 'io.smooch:core:7.0.2'
    implementation 'io.smooch:ui:7.0.2'

    // Libraries imported by Smooch
    implementation "com.google.firebase:firebase-messaging:${firebaseMessagingVersion}"
    implementation "com.android.support:support-v4:${supportLibraryVersion}"
    implementation "com.android.support:exifinterface:${supportLibraryVersion}"
    implementation "com.android.support:recyclerview-v7:${supportLibraryVersion}"
    implementation "com.android.support:support-media-compat:${supportLibraryVersion}"
    implementation "com.google.android.gms:play-services-location:${playServicesVersion}"

    // Your other app dependencies
}

```
