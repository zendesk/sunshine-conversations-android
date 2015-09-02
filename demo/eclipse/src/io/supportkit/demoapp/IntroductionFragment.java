package io.supportkit.demoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import io.supportkit.ui.ConversationActivity;

public class IntroductionFragment extends Fragment {

    public IntroductionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button mHelpBtn = (Button) rootView.findViewById(R.id.button_help);
        mHelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing SupportKit on the button click
                ConversationActivity.show(getActivity(), Intent.FLAG_ACTIVITY_NEW_TASK);
            }
        });

        TextView mSignup = (TextView) rootView.findViewById(R.id.textView_signup);
        mSignup.setMovementMethod(LinkMovementMethod.getInstance());

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(1);
    }
}
