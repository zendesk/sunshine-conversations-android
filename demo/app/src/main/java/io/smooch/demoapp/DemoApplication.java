package io.smooch.demoapp;

import android.app.Application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.smooch.core.Settings;
import io.smooch.core.Smooch;
import io.smooch.core.SmoochCallback;
import io.smooch.core.User;

public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Smooch with your app id
        // Get your own at https://app.smooch.io/
        // and paste it here!
        Smooch.init(this, new Settings("53ecf8ad0f4f4d0a0000eaad"), new SmoochCallback() {
            @Override
            public void run(Response response) {
                // Handle init response here!
            }
        });
        addSomeProperties(User.getCurrentUser());
    }

    private void addSomeProperties(final User user) {
        final Map<String, Object> customProperties = new HashMap<>();

        // Identify user with default properties
        user.setFirstName("Demo");
        user.setLastName("App");
        user.setEmail("demo.app@smooch.io");
        user.setSignedUpAt(new Date(1420070400000l));

        // Add your own custom properties
        customProperties.put("Last Seen", new Date());
        customProperties.put("Awesome", true);
        customProperties.put("Karma", 1337);
        user.addProperties(customProperties);
    }
}
