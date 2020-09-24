package io.smooch.demoapp;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;

import io.smooch.ui.ConversationActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    // Hey App Makers, Smooch here, there isn't
    // really nothing to see here, we have integrated
    // Smooch APIs in the fragments.

    private NavigationDrawerFragment navigationDrawerFragment;

    private CharSequence title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        title = getTitle();

        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position) {
            case 0:
                IntroductionFragment introductionFragment = new IntroductionFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, introductionFragment)
                        .commit();
                break;
            case 1:
                ConversationActivity.builder().show(this);
                break;
            case 2:
                SettingsFragment settingsFragment = new SettingsFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, settingsFragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                title = getString(R.string.title_section1);
                break;
            case 2:
                title = getString(R.string.title_section2);
                break;
            case 3:
                title = getString(R.string.title_section3);
                break;
        }
    }

    void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
}
