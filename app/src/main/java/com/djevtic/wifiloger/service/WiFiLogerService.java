package com.djevtic.wifiloger.service;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.djevtic.wifiloger.util.StaticValues;

import java.util.List;

/**
 * Created by djevtic on 13.2.17..
 */

public class WiFiLogerService extends IntentService {


    private WifiManager mWifiManager;
    boolean scanning = false;

    public WiFiLogerService(String name) {
        super(name);
    }

    public WiFiLogerService() {
        super("WiFiLogerService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("djevtic", "Service Started");
//        if (intent.getExtras() != null) {
//            scanning = intent.getBooleanExtra("scanning", false);
//            Log.d("djevtic", "Scaning is: "+scanning);
//            if (scanning) {
//                Log.d("djevtic", "Scan started");
//                mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
//                registerReceiver(mWifiScanReceiver,
//                        new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
//                mWifiManager.startScan();
//            } else {
//                if (mWifiManager != null && mWifiScanReceiver != null) {
//                    Log.d("djevtic", "Scan Stopped");
//                    unregisterReceiver(mWifiScanReceiver);
//                }
//            }
//        } else {
//            Log.d("djevtic", "Service Started");
//        }
        scanning = true;
        mWifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                registerReceiver(mWifiScanReceiver,
                        new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                mWifiManager.startScan();
        registerReceiver(mLifeCycleProcess, new IntentFilter(StaticValues.SERVICE_LIFECYCLE));

        while(scanning){
            Log.d("djevtic", "Service work");
        }
    }

    private final BroadcastReceiver mWifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                List<ScanResult> mScanResults = mWifiManager.getScanResults();
                // add your logic here
                Log.d("djevtic", "We found something");
            }
        }
    };

    private final BroadcastReceiver mLifeCycleProcess = new BroadcastReceiver() {
        @Override
        public void onReceive(Context c, Intent intent) {
            if (intent.getAction().equals(StaticValues.SERVICE_LIFECYCLE)) {
                scanning = false;
                // add your logic here
                Log.d("djevtic", "Die Service Die");
            }
        }
    };

    @Override
    public void onDestroy() {
        Log.d("djevtic", "onDestroy called?");
        unregisterReceiver(mWifiScanReceiver);
        unregisterReceiver(mLifeCycleProcess);
        super.onDestroy();
    }
}
