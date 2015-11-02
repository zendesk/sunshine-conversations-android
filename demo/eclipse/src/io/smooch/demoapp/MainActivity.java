package io.smooch.demoapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;

import io.smooch.ui.ConversationActivity;
import io.smooch.demoapp.R;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    // Hey App Makers, Smooch here, there isn't
    // really nothing to see here, we have integrated
    // Smooch APIs in the fragments.

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch(position) {
            case 0:
                IntroductionFragment introductionFragment = new IntroductionFragment();

                fragmentManager.beginTransaction()
                    .replace(R.id.container, introductionFragment)
                    .commit();
            break;
            case 1:
                ConversationActivity.show(this);
            break;
            case 2:
                WhispersFragment whispersFragment = new WhispersFragment();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, whispersFragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case 3:
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
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
        }
    }

    void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }
}
