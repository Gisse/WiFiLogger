package com.djevtic.wifiloger.util;

/**
 * Created by djevtic on 17.2.17..
 */

public class WiFIModel {

    private String mSsid;
    private String mBssid;
    private String mCapabilities;
    private String mLevel;
    private String mOperatorFName;
    private String mIs80211;
    private String mPasspoint;
    private String mLongitude;
    private String mLatitude;

    public WiFIModel(String ssid, String bssid, String capabilities, String level, String operator,
                     String is801, String passpoint, String logitude, String latitude){
        setmSsid(ssid);
        setmBssid(bssid);
        setmCapabilities(capabilities);
        setmLevel(level);
        setmOperatorFName(operator);
        setmIs80211(is801);
        setmPasspoint(passpoint);
        setmLongitude(logitude);
        setmLatitude(latitude);
    }

    public String getmSsid() {
        return mSsid;
    }

    public void setmSsid(String mSsid) {
        this.mSsid = mSsid;
    }

    public String getmBssid() {
        return mBssid;
    }

    public void setmBssid(String mBssid) {
        this.mBssid = mBssid;
    }

    public String getmCapabilities() {
        return mCapabilities;
    }

    public void setmCapabilities(String mCapabilities) {
        this.mCapabilities = mCapabilities;
    }

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public String getmOperatorFName() {
        return mOperatorFName;
    }

    public void setmOperatorFName(String mOperatorFName) {
        this.mOperatorFName = mOperatorFName;
    }

    public String getmIs80211() {
        return mIs80211;
    }

    public void setmIs80211(String mIs80211) {
        this.mIs80211 = mIs80211;
    }

    public String getmPasspoint() {
        return mPasspoint;
    }

    public void setmPasspoint(String mPasspoint) {
        this.mPasspoint = mPasspoint;
    }

    public String getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(String mLongitude) {
        this.mLongitude = mLongitude;
    }

    public String getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(String mLatitude) {
        this.mLatitude = mLatitude;
    }
}
