package com.myproject.publicservices;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String pref_location = "";
    public static int backKey = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pref_location = PreferenceManager.getDefaultSharedPreferences(this).getString("location","638109");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.mainFrame, fragment);
        ft.addToBackStack(null);
        ft.commit();
        Log.d("My backstack count", "stack:" + getSupportFragmentManager().getBackStackEntryCount());

    }

    @Override
    protected void onPostResume() {
        pref_location = PreferenceManager.getDefaultSharedPreferences(this).getString("location","638109");
        super.onPostResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            } else if(backKey == 1){
                backKey = 0;
                Fragment fragment = new HomeFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.commit();
            } else {
                super.onBackPressed();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
            backKey = 1;
        } else if (id == R.id.nav_health) {
            fragment = new HealthFragment();
            backKey = 1;
            HealthFragment.url = "http://gokulonlinedatabase.net16.net/publicservice/publicservice.php?Table=hospital";
            HealthFragment.health_info = "Hospital Details";
        } else if (id == R.id.nav_water) {
            fragment = new WaterFragment();
            backKey = 1;
        } else if (id == R.id.nav_education) {
            fragment = new EducationFragment();
            backKey = 1;
            EducationFragment.url = "http://gokulonlinedatabase.net16.net/publicservice/publicservice.php?Table=education";
            EducationFragment.education_info = "Near by Education Centers";
        }  else if (id == R.id.nav_transport) {
            fragment = new TransportFragment();
            backKey = 1;
            TransportFragment.url = "http://gokulonlinedatabase.net16.net/publicservice/publicservice.php?Table=bus";
            TransportFragment.info = "Bus Details";
        } else if (id == R.id.nav_energy) {
            fragment = new TransportFragment();
            backKey = 1;
            TransportFragment.url = "http://gokulonlinedatabase.net16.net/publicservice/publicservice.php?Table=energy";
            TransportFragment.info = "Todays Power cut details";
        } else if (id == R.id.nav_setting) {
            Fragment preferenceFragment = new SettingsFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, preferenceFragment);
            ft.commit();
        }

        if(fragment != null){

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
