package com.zyj.app.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by ${zyj} on 2016/7/15.
 */
public class ReactiveNetwork {

    private ConnectivityStatus status = ConnectivityStatus.UNKNOWN;
    private NetworkEvent networkEvent ;

    /**
     * 判断链接的类型
     * @param context
     * @return
     */
    public void observeNetworkConnectivity(final Context context) {
        final IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        final BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent intent) {
                final ConnectivityStatus newStatus = getConnectivityStatus(context);

                // we need to perform check below,
                // because after going off-line, onReceive() is called twice
                if (newStatus != status) {
                    status = newStatus;
                    if ( networkEvent != null ){
                        networkEvent.enent( newStatus );
                    }
                }
            }
        };

        context.registerReceiver(receiver, filter);
    }

    /**
     * Gets current network connectivity status
     * @param context Application Context is recommended here
     * @return ConnectivityStatus, which can be WIFI_CONNECTED, MOBILE_CONNECTED or OFFLINE
     */
    private ConnectivityStatus getConnectivityStatus(final Context context) {
        final String service = Context.CONNECTIVITY_SERVICE;
        final ConnectivityManager manager = (ConnectivityManager) context.getSystemService(service);
        final NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        if (networkInfo == null) {
            return ConnectivityStatus.OFFLINE;
        }

        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return ConnectivityStatus.WIFI_CONNECTED;
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return ConnectivityStatus.MOBILE_CONNECTED;
        }

        return ConnectivityStatus.OFFLINE;
    }

    public void setNetworkEvent ( NetworkEvent networkEvent ){
        this.networkEvent = networkEvent ;
    }

    public interface NetworkEvent{
        void enent(ConnectivityStatus status) ;
    }
}
