package com.example.bloodbank.veiw.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.veiw.fragment.ActoinCycle.NotificationFragment;
import com.example.bloodbank.veiw.fragment.HomeCycle.AboutAppFragment;
import com.example.bloodbank.veiw.fragment.HomeCycle.ConnectingUsFragment;
import com.example.bloodbank.veiw.fragment.HomeCycle.Home.HomeFragment;
import com.example.bloodbank.veiw.fragment.HomeCycle.Home.PostsFragment;
import com.example.bloodbank.veiw.fragment.HomeCycle.SettingesNotifictionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeCycleActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.home_cycle_iv_notification)
    ImageView homeCycleIvNotification;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.home_cycle_iv_home)
    ImageView homeCycleIvHome;
    @BindView(R.id.home_cycle_iv_text)
    TextView homeCycleIvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);
        ButterKnife.bind(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        HomeFragment homeFragment = new HomeFragment();
        HelperMethod.replace(homeFragment, getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_profil) {
            // Handle the camera action
        } else if (id == R.id.setting_notification) {

            SettingesNotifictionFragment settingesNotifictionFragment = new SettingesNotifictionFragment();
            HelperMethod.replace(settingesNotifictionFragment, getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);

        } else if (id == R.id.favorit) {
            PostsFragment postsFragment = new PostsFragment();
            postsFragment.Favourites = true;
            HelperMethod.replace(postsFragment, getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);

        } else if (id == R.id.main) {
            HomeFragment homeFragment = new HomeFragment();
            HelperMethod.replace(homeFragment, getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);

        } else if (id == R.id.user_regulation) {

        } else if (id == R.id.connect_us) {
            ConnectingUsFragment connectingUsFragment = new ConnectingUsFragment();
            HelperMethod.replace(connectingUsFragment, getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);

        } else if (id == R.id.about_app) {

            AboutAppFragment aboutAppFragment = new AboutAppFragment();
            HelperMethod.replace(aboutAppFragment, getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);

        } else if (id == R.id.registring_exiting) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @OnClick(R.id.home_cycle_iv_notification)
    public void onViewClicked() {
        NotificationFragment notificationFragment = new NotificationFragment();
        HelperMethod.replace(notificationFragment, getSupportFragmentManager(), R.id.home_cycle_fl_fragment_contaner, null, null);
    }

    public void setVisibilityHome(int visibility, View.OnClickListener listener) {
        homeCycleIvHome.setVisibility(visibility);
        if (listener != null) {
            homeCycleIvHome.setOnClickListener(listener);
        }
    }

    public void setvisibility(int visibility) {
        homeCycleIvHome.setVisibility(visibility);
    }

    public void setVisibilityNotification(int visibility, View.OnClickListener listener) {
        homeCycleIvNotification.setVisibility(visibility);
        if (listener != null) {
            homeCycleIvNotification.setOnClickListener(listener);
        }
    }



    public void setVisibilityTitel(int visibility) {
        homeCycleIvText.setVisibility(visibility);
    }

}
