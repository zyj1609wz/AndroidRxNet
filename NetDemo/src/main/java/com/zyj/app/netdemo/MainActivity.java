package com.zyj.app.netdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zyj.app.net.ConnectivityStatus;
import com.zyj.app.net.ReactiveNetwork;

public class MainActivity extends AppCompatActivity {

    private ReactiveNetwork reactiveNetwork ;
    private TextView textView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById( R.id.tv );
        reactiveNetwork = new ReactiveNetwork() ;
        reactiveNetwork.setNetworkEvent(new ReactiveNetwork.NetworkEvent() {
            @Override
            public void enent(ConnectivityStatus status) {
                textView .setText( "网络连接的类型 " + status.description);
            }
        });

        reactiveNetwork.observeNetworkConnectivity( this ) ;
    }
}
