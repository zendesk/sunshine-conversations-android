# [Smooch SDK for Android](http://www.smooch.io)

[![Bintray](https://api.bintray.com/packages/smoochorg/maven/smooch/images/download.svg)](https://bintray.com/smoochorg/maven/smooch/view#files)
[![License](http://img.shields.io/cocoapods/l/Smooch.svg)](https://smooch.io/terms)

Sign up and get an app id: [https://app.smooch.io/signup](https://app.smooch.io/signup).

Add the dependencies right into your `build.gradle` file

```
implementation 'io.smooch:core:6.2.4'
implementation 'io.smooch:ui:6.2.4'
```

Initialize Smooch in your [Application](developer.android.com/reference/android/app/Application.html) class

```java
import android.app.Application;
import io.smooch.core.Settings;
import io.smooch.core.Smooch;
import io.smooch.core.SmoochCallback;


public class YourApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Smooch.init(this, new Settings("YOUR_APP_ID"), new SmoochCallback() {
            @Override
            public void run(Response response) {
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

ConversationActivity.show(this);
```

For more details see our [docs and full install guide](http://docs.smooch.io/android/).

# Dependencies

Smooch uses the following dependencies:

```
    // Core dependencies
    implementation 'com.google.code.gson:gson:2.4'
    implementation 'com.squareup.okhttp3:okhttp:3.11.0'
    implementation 'com.google.firebase:firebase-core:16.0.9'
    implementation 'com.google.firebase:firebase-messaging:18.0.0'
    implementation 'com.android.support:support-annotations:28.0.0'

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
def firebaseCoreVersion = "YOUR_FIREBASE_CORE_VERSION"
def firebaseMessagingVersion = "YOUR_FIREBASE_MESSAGING_VERSION"
def playServicesVersion = "YOUR_PLAY_SERVICES_VERSION"

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation "com.android.support:appcompat-v7:${supportLibraryVersion}"

    // Smooch
    implementation 'io.smooch:core:6.2.4'
    implementation 'io.smooch:ui:6.2.4'

    // Libraries imported by Smooch
    implementation "com.google.firebase:firebase-core:${firebaseCoreVersion}"
    implementation "com.google.firebase:firebase-messaging:${firebaseMessagingVersion}"
    implementation "com.android.support:support-v4:${supportLibraryVersion}"
    implementation "com.android.support:exifinterface:${supportLibraryVersion}"
    implementation "com.android.support:recyclerview-v7:${supportLibraryVersion}"
    implementation "com.android.support:support-media-compat:${supportLibraryVersion}"
    implementation "com.google.android.gms:play-services-location:${playServicesVersion}"

    // Your other app dependencies
}

```
