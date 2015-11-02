package io.smooch.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import io.smooch.core.Smooch;
import io.smooch.core.User;
import io.smooch.demoapp.R;

public class WhispersFragment extends Fragment {

    public WhispersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View whispersView = inflater.inflate(
                R.layout.fragment_whispers, container, false);

        final Button mCheckout = (Button) whispersView.findViewById(R.id.button_whispers_checkout);
        mCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "User have checked out!", Toast.LENGTH_LONG).show();
                mCheckout.setEnabled(false);

                // Send `checkout` event to Smooch when your users checkout
                Smooch.track("checkout");
            }
        });

        final Button mKarma = (Button) whispersView.findViewById(R.id.button_whispers_karma);
        mKarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "User have gained more karma!", Toast.LENGTH_LONG).show();
                mKarma.setEnabled(false);

                // When you update your users' properties, we can trigger whispers based on any properties.
                // In this case, a whisper will be sent when user have over 9000 karma.
                User skUser = User.getCurrentUser();
                final Map<String, Object> customProperties = new HashMap<>();
                customProperties.put("Karma", 9001);
                skUser.addProperties(customProperties);
            }
        });

        return whispersView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(3);
    }
}
