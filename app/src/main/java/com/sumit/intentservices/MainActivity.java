package com.sumit.intentservices;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button mbtndownload;
    private TextView tvview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtndownload=findViewById(R.id.btndownload);
        tvview=findViewById(R.id.tvtext);
        IntentFilter intentFilter=new IntentFilter("parv.com");
        registerReceiver(receiver,intentFilter);
        Intent intent=new Intent(this,DownloaderService.class);
        mbtndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
    }
    private BroadcastReceiver receiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tvview.setText(intent.getStringExtra("data"));
        }
    };
}