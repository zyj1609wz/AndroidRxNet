package com.zyj.app.net;

/**
 * Created by ${zyj} on 2016/7/15.
 */
public enum ConnectivityStatus {

    UNKNOWN("unknown"),
    WIFI_CONNECTED("connected to WiFi network"),
    MOBILE_CONNECTED("connected to mobile network"),
    OFFLINE("offline"),

    WIFI_STATE_ENABLING( "wifi opening"),
    WIFI_STATE_ENABLED("wifi open"),
    WIFI_STATE_DISABLING("wifi closing"),
    WIFI_STATE_DISABLED("wifi closed"),
    WIFI_STATE_UNKNOWN( "wifi unknown" );

    public final String description;

    ConnectivityStatus(final String description) {
        this.description = description;
    }

    @Override public String toString() {
        return "ConnectivityStatus{" + "description='" + description + '\'' + '}';
    }

}
