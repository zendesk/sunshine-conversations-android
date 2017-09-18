package io.smooch.demoapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.smooch.core.Settings;
import io.smooch.core.Smooch;

public class SettingsFragment extends Fragment {
    private EditText mAppId;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View settingsView = inflater.inflate(
                R.layout.fragment_settings, container, false);

        mAppId = settingsView.findViewById(R.id.editText_appId);
        final Button mInit = settingsView.findViewById(R.id.button_init);
        mInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appId = mAppId.getText().toString();

                if (!appId.equals("")) {
                    Toast toast = Toast.makeText(getActivity(), "Smooch re-initialized w/ id '" + appId + "'.", Toast.LENGTH_LONG);
                    TextView toastView = toast.getView().findViewById(android.R.id.message);
                    if (toastView != null)
                        toastView.setGravity(Gravity.CENTER);
                    toast.show();

                    // This is only for showcase purposes, you should always
                    // initialize Smooch from the Application class
                    Smooch.init(getActivity().getApplication(), new Settings(appId), null);
                }
            }
        });

        mAppId.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard();
                }
            }
        });

        settingsView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View v, final MotionEvent event) {
                hideKeyboard();
                return false;
            }
        });

        return settingsView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity) context).onSectionAttached(3);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mAppId.getWindowToken(), 0);
    }
}
