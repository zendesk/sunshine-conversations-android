# [Smooch SDK for Android](http://www.smooch.io)

[![Bintray](https://api.bintray.com/packages/smoochorg/maven/smooch/images/download.svg)](https://bintray.com/smoochorg/maven/smooch/view#files)
[![License](http://img.shields.io/cocoapods/l/Smooch.svg)](http://smooch.io/terms.html)

# tl;dr

Sign up and get an app token: [https://app.smooch.io/signup](https://app.smooch.io/signup).

Add the dependencies right into your `build.gradle` file

```
compile 'io.smooch:core:latest.release'
compile 'io.smooch:ui:latest.release'
```
    
Initialize Smooch in your [Application](developer.android.com/reference/android/app/Application.html) class

```java
import android.app.Application;
import io.smooch.core.Smooch;


public class YourApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Smooch.init(this, "YOUR_APP_TOKEN");
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

# Upgrade guide to Smooch

SupportKit is now Smooch!

For a list of changes and a guide to upgrade, refer to our [2.0.0 release](https://github.com/smooch/smooch-android/releases/tag/2.0.0).
