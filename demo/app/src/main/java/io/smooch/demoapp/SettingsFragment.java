package io.smooch.demoapp;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
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
    private EditText integrationIdField;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View settingsView = inflater.inflate(
                R.layout.fragment_settings, container, false);

        integrationIdField = settingsView.findViewById(R.id.editText_integration_id);
        final Button buttonInit = settingsView.findViewById(R.id.button_init);
        buttonInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String integrationId = integrationIdField.getText().toString();

                if (!integrationId.equals("")) {
                    Toast toast = Toast.makeText(getActivity(),
                            "Smooch re-initialized w/ id '" + integrationId + "'.", Toast.LENGTH_LONG);
                    TextView toastView = toast.getView().findViewById(android.R.id.message);
                    if (toastView != null) {
                        toastView.setGravity(Gravity.CENTER);
                    }
                    toast.show();

                    // This is only for showcase purposes, you should always
                    // initialize Smooch from the Application class
                    Smooch.init(getActivity().getApplication(), new Settings(integrationId), null);
                }
            }
        });

        integrationIdField.setOnFocusChangeListener(new View.OnFocusChangeListener() {

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
        ((MainActivity) context).onSectionAttached(4);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(integrationIdField.getWindowToken(), 0);
    }
}
