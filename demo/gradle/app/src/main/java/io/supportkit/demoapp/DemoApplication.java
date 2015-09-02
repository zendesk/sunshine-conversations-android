package io.supportkit.demoapp;

import android.app.Application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.supportkit.core.SupportKit;
import io.supportkit.core.User;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize SupportKit with your app token
        // Get your own at https://app.supportkit.io/
        // and paste it here!
        SupportKit.init(this, "4z6ruil9xi9kixnf4ezztf1kl");
        addSomeProperties(User.getCurrentUser());
    }

    private void addSomeProperties(final User user) {
        final Map<String, Object> customProperties = new HashMap<>();

        // Identify user with default properties
        user.setFirstName("Demo");
        user.setLastName("App");
        user.setEmail("demo.app@supportkit.io");
        user.setSignedUpAt(new Date(1420070400000l));

        // Add your own custom properties
        customProperties.put("Last Seen", new Date());
        customProperties.put("Awesome", true);
        customProperties.put("Karma", 1337);
        user.addProperties(customProperties);
    }
}
