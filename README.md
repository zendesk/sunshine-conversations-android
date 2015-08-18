# [SupportKit SDK for Android](http://www.supportkit.io)

[![Bintray](https://api.bintray.com/packages/supportkitorg/maven/supportkit/images/download.svg)](https://bintray.com/supportkitorg/maven/supportkit/view#files)
[![License](http://img.shields.io/cocoapods/l/SupportKit.svg)](http://supportkit.io/terms.html)

# tl;dr

Sign up and get an app token: [https://app.supportkit.io/signup](https://app.supportkit.io/signup).

Add the dependencies right into your build.gradle file:

    compile 'com.google.android.gms:play-services-gcm:7.5.0'
    compile 'com.google.code.gson:gson:2.3.1'
    compile 'com.squareup.okhttp:okhttp:2.4.0'
    compile 'io.supportkit:core:latest.release'
    compile 'io.supportkit:ui:latest.release'
    
Init SupportKit
    
    import io.supportkit.core.SupportKit;

    ...

    public class YourApplication extends Application {
        @Override
        public void onCreate() {
            super.onCreate();
            SupportKit.init(this, "YOUR_APP_TOKEN");
        }
    }
    
Show the ConversationActivity
    
    import io.supportkit.ui.ConversationActivity;

    ...

    ConversationActivity.show(this);



For Eclipse and all the details see our [Docs and full install guide](http://docs.supportkit.io/android/).