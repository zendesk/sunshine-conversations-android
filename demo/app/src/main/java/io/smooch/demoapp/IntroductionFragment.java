package io.smooch.demoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.smooch.ui.ConversationActivity;

public class IntroductionFragment extends Fragment {

    public IntroductionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button helpBtn = rootView.findViewById(R.id.button_help);
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Showing Smooch on the button click
                ConversationActivity.builder()
                        .withFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .show(getActivity());
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).onSectionAttached(1);
    }

}
