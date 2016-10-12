package com.zyj.rxnet.demo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.zyj.app.rxnet.ConnectivityStatus;
import com.zyj.app.rxnet.ReactiveNetwork;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ReactiveNetwork";
    private TextView tvConnectivityStatus;
    private TextView tvInternetStatus;
    private TextView wifiSwitchStatus ;

    private ReactiveNetwork reactiveNetwork;
    private Subscription networkConnectivitySubscription;
    private Subscription internetConnectivitySubscription;
    private Subscription wifiSwitchSubscription ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvConnectivityStatus = (TextView) findViewById( R.id.tv1 );
        tvInternetStatus= (TextView) findViewById( R.id.tv2 );
        wifiSwitchStatus= (TextView) findViewById( R.id.tv3 );

        reactiveNetwork = new ReactiveNetwork();

        //监听网络连接类型的 （数据流量 、wifi 、断线）
        networkConnectivitySubscription =
                reactiveNetwork.observeNetworkConnectivity(getApplicationContext())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<ConnectivityStatus>() {
                            @Override public void call(final ConnectivityStatus status) {

                                Log.d(TAG, status.toString());
                                tvConnectivityStatus.setText( "网络连接状态： " + status.description);
                            }
                        });

        //监听是否链接互联网的 （ 是 ， 否）
        internetConnectivitySubscription =
                reactiveNetwork.observeInternetConnectivity()
                        .observeOn( AndroidSchedulers.mainThread())
                        .subscribe(new Action1<Boolean>() {
                            @Override
                            public void call(Boolean aBoolean) {
                                tvInternetStatus.setText( "是否有可用的网络： "+ aBoolean.toString());
                            }
                        }) ;

        //监听wifi 开关状态的（ wifi 正在打开 、 wifi 打开 、wifi 正在关闭、 wifi 关闭）
        wifiSwitchSubscription =
                reactiveNetwork.observeWifiSwitch( this )
                        .subscribe(new Action1<ConnectivityStatus>() {
                            @Override
                            public void call(ConnectivityStatus connectivityStatus) {
                                wifiSwitchStatus.setText( "wifi 是否打开： " + connectivityStatus.description);
                            }
                        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        safelyUnsubscribe(networkConnectivitySubscription, internetConnectivitySubscription , wifiSwitchSubscription );
    }

    private void safelyUnsubscribe(Subscription... subscriptions) {
        for (Subscription subscription : subscriptions) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }
}
