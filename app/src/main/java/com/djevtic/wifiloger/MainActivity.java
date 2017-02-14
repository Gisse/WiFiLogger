package com.djevtic.wifiloger;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.djevtic.wifiloger.adapters.PageAdapter;
import com.djevtic.wifiloger.service.WiFiLogerService;
import com.djevtic.wifiloger.util.StaticValues;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        //startingService();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.start_scan:
                startingService();
                return true;
            case R.id.stop_scan:
                stopService();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startingService() {
        if (checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)
                != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)
                        != PackageManager.PERMISSION_GRANTED &&
                checkSelfPermission(Manifest.permission.CHANGE_NETWORK_STATE)
                        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_WIFI_STATE,
                    Manifest.permission.CHANGE_NETWORK_STATE},
                    StaticValues.PERMISION_REQUEST);
            return;
        }
        Intent serviceStartIntent = new Intent(this, WiFiLogerService.class);
        this.startService(serviceStartIntent);
    }

    private void stopService() {
        Intent stopService = new Intent(StaticValues.SERVICE_LIFECYCLE);
        this.sendBroadcast(stopService);
    }
}
