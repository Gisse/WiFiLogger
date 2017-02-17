package com.djevtic.wifiloger.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.wifi.ScanResult;

import com.djevtic.wifiloger.util.WiFIModel;

import java.util.ArrayList;

/**
 * Created by djevtic on 17.2.17..
 */

public class WiFiDatabase {

    private static final String TABLE_WIFI_NETWORKS = "networks";

    //Fields in networks table
    private static final String KEY_ID = "id";
    private static final String KEY_BSSID = "bssid";
    private static final String KEY_SSID = "ssid";
    private static final String KEY_CAPABILITIES = "capabilities";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_OPERATOR_FRIENDLY_NAME = "operatorFriendlyName";
    private static final String KEY_80211 = "is80211";
    private static final String KEY_PASSPOINT = "passpoint";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    public WiFiDatabase(Context context){
        this.mDbHelper = new DatabaseHelper(context);
        this.mDb = mDbHelper.getWritableDatabase();
    }

    public void insertNetwork(ScanResult result, double longitude, double latitude){
        ContentValues values = new ContentValues();
        values.put(KEY_SSID, result.SSID);
        values.put(KEY_BSSID, result.BSSID);
        values.put(KEY_CAPABILITIES, result.capabilities);
        values.put(KEY_LEVEL, result.level);
        if(result.operatorFriendlyName.length()>0) {
            values.put(KEY_OPERATOR_FRIENDLY_NAME, result.operatorFriendlyName.toString());
        }else{
            values.put(KEY_OPERATOR_FRIENDLY_NAME, "None");
        }
        values.put(KEY_80211, result.is80211mcResponder());
        values.put(KEY_PASSPOINT, result.isPasspointNetwork());
        values.put(KEY_LONGITUDE, longitude);
        values.put(KEY_LATITUDE, latitude);
        mDb.insert(TABLE_WIFI_NETWORKS, null, values);
    }

    public ArrayList<WiFIModel> getWiFiList(){
        ArrayList<WiFIModel> list = new ArrayList<>();
        String[] projection = {KEY_ID, KEY_SSID, KEY_BSSID, KEY_CAPABILITIES, KEY_LEVEL,
                KEY_OPERATOR_FRIENDLY_NAME, KEY_80211, KEY_PASSPOINT, KEY_LONGITUDE, KEY_LATITUDE};

        Cursor c = mDb.query(TABLE_WIFI_NETWORKS, projection, null, null, null, null, KEY_ID + " ASC");
        if (c != null) {
            if (c.moveToFirst()) {
                c.moveToFirst();
                do {
                    WiFIModel model = new WiFIModel(c.getString(1), c.getString(2), c.getString(3),
                            c.getString(4), c.getString(5), c.getString(6), c.getString(7),
                            c.getString(8), c.getString(9));
                    list.add(model);
                }
                while (c.moveToNext());
            }

        }
        return list;
    }


    public class DatabaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 8;

        private static final String DATABASE_NAME = "contactsDatabase";

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_WIFI_NETWORKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_BSSID + " TEXT,"
                + KEY_SSID + " TEXT,"
                + KEY_CAPABILITIES + " TEXT,"
                + KEY_LEVEL + " TEXT,"
                + KEY_OPERATOR_FRIENDLY_NAME + " TEXT,"
                + KEY_80211 + " TEXT,"
                + KEY_PASSPOINT + " TEXT,"
                + KEY_LONGITUDE + " TEXT,"
                + KEY_LATITUDE + " TEXT"
                + ")";

        private String DELETE_TABLE_SQL = "DROP TABLE IF EXISTS " + TABLE_WIFI_NETWORKS;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_CONTACTS_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DELETE_TABLE_SQL);
            onCreate(db);
        }
    }
}
