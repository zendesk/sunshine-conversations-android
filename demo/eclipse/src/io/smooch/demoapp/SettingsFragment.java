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

import io.smooch.core.Smooch;
import io.smooch.demoapp.R;

public class SettingsFragment extends Fragment {
    private EditText mAppToken;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View settingsView = inflater.inflate(
                R.layout.fragment_settings, container, false);

        mAppToken = (EditText) settingsView.findViewById(R.id.editText_appToken);
        final Button mInit = (Button) settingsView.findViewById(R.id.button_init);
        mInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appToken = mAppToken.getText().toString();

                if (!appToken.equals("")) {
                    Toast toast = Toast.makeText(getActivity(), "Smooch re-initialized w/ token '" + appToken + "'.", Toast.LENGTH_LONG);
                    TextView toastView = (TextView) toast.getView().findViewById(android.R.id.message);
                    if (toastView != null)
                        toastView.setGravity(Gravity.CENTER);
                    toast.show();

                    // This is only for showcase purposes, you should always
                    // initialized Smooch from the Application class
                    Smooch.init(getActivity().getApplication(), appToken);
                }
            }
        });

        mAppToken.setOnFocusChangeListener(new View.OnFocusChangeListener() {

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(4);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mAppToken.getWindowToken(), 0);
    }
}
