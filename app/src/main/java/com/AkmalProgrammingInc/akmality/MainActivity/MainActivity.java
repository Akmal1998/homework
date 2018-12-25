package com.AkmalProgrammingInc.akmality.MainActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.AkmalProgrammingInc.akmality.Application;
import com.bumptech.glide.Glide;
import com.AkmalProgrammingInc.akmality.GitHubAuth.AuthView;
import com.AkmalProgrammingInc.akmality.Contacts.ContactsView;
import com.AkmalProgrammingInc.akmality.AboutDevice.DeviceInfoFragment;
import com.AkmalProgrammingInc.akmality.YandexMaps.MapsView;
import com.AkmalProgrammingInc.akmality.R;
import com.AkmalProgrammingInc.akmality.Repositories.ReposView;
import com.AkmalProgrammingInc.akmality.Camera.SensorView;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.transport.TransportFactory;


import static com.AkmalProgrammingInc.akmality.Application.PREF_TOKEN;
import static com.AkmalProgrammingInc.akmality.GitHubAuth.AuthView.PREFERENCES;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MapsView.MapViewInteractionListener,
        ContactsView.ContactsViewInteractionListener, SensorView.OnFragmentInteractionListener,
        DeviceInfoFragment.OnDeviceInfoFragmentInteractionListener, ReposView.OnReposViewInteractionListener, MainActivityContract.IMainView {

    FragmentManager fragmentManager;
    Fragment fragment;

    private String token, login, url, avatar_url;
    private MainActivityContract.IMainPresenter presenter;

    private DrawerLayout drawer;

    private ImageView avatar;
    private TextView loginText, urlText;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

        try{
            token = getIntent().getExtras().getString(PREF_TOKEN);
            Log.d(getLocalClassName(), token);
        }catch (NullPointerException exception){
            token = null;
        }


        fragmentManager = getSupportFragmentManager();
        fragment = null;

        presenter = new MainPresenter(new MainModel(), this);

        initMapKit();

        initNavigation();


    }

    private void initNavigation(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        url = sp.getString("url", null);
        avatar_url = sp.getString("avatar_url", null);
        login = sp.getString("login", null);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                loginText = drawerView.findViewById(R.id.loginText);
                urlText = drawerView.findViewById(R.id.urlText);
                avatar = drawerView.findViewById(R.id.avatarView);

                if (login != null && url != null){
                    urlText.setText(url);
                    loginText.setText(login);
                        if (avatar_url != null){
                            loadImage(avatar_url);
                        }
                }else{
                    presenter.loadUserInfo(token);
                }

            }
        };
        drawer.addDrawerListener(toggle);
        toggle.syncState();







    }




    private void initMapKit(){
        MapKitFactory.initialize(this);
        TransportFactory.initialize(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void logout() {
        SharedPreferences.Editor editor = sp.edit();

        editor.remove(Application.PREF_TOKEN);
        editor.remove("login");
        editor.remove("url");
        editor.remove("avatar_url");

        editor.apply();

        Intent logoutIntent = new Intent(this, AuthView.class);
        finishAffinity();
        startActivity(logoutIntent);

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_repos) {
            // Repositories
            fragment = ReposView.newInstance(token);
        } else if (id == R.id.nav_maps) {
            // YandexMaps
            fragment = new MapsView();

        } else if (id == R.id.nav_contacts) {
            // Contacts
            fragment = new ContactsView();
        } else if (id == R.id.nav_information){
            // AboutDevice about device
            fragment = new DeviceInfoFragment();
        } else if (id == R.id.nav_sensor) {
            // Sensors + camera
            fragment = new SensorView();
        } else if (id == R.id.nav_logout) {
            // Log out

            logout();
        }

        try {
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }catch (Exception e){
            e.printStackTrace();
        }

        item.setChecked(true);
        setTitle(item.getTitle());


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapViewInteraction(Uri uri) {

    }

    @Override
    public void onContactsViewInteraction(Uri uri) {

    }

    @Override
    public void onSensorViewInteraction(Uri uri) {

    }

    @Override
    public void onDeviceInfoFragmentInteraction(Uri uri) {

    }

    @Override
    public void onReposViewInteraction(Uri uri) {

    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void setUserInfo(String login, String url, String avatar_url) {
        SharedPreferences.Editor editor = sp.edit();

        this.url = url;
        this.login = login;
        this.avatar_url = avatar_url;

        editor.putString("login", login);
        editor.putString("url", url);
        editor.putString("avatar_url", avatar_url);
        editor.apply();

        loadImage(avatar_url);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void loadImage(String url){
        Glide.with(this)
                .load(url)
                .into(avatar);
    }



}
