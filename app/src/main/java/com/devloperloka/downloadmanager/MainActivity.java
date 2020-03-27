package com.devloperloka.downloadmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "groupA";
    String sjf;
    String fcfs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        fcfs = intent.getStringExtra("fcfs");
        sjf = intent.getStringExtra("sjf");

        if (sjf != null) {
            saveString();
            Toast.makeText(MainActivity.this, sjf, Toast.LENGTH_LONG).show();
        } else {

            Toast.makeText(MainActivity.this, fcfs, Toast.LENGTH_LONG).show();
        }


        findViewById(R.id.jump_download_list).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean groupByTag = false;
                        DownloadListActivity.start(v.getContext(), groupByTag ? TAG : "");
                    }
                });

        findViewById(R.id.jump_webview_download).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, WebViewDownloadActivity.class);
                        intent.putExtra("fcfs", fcfs);
                        intent.putExtra("fcfs", sjf);
                        startActivity(intent);

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = getSharedPreferences("saveCounter", MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = getSharedPreferences("saveBoolean", MODE_PRIVATE);
        SharedPreferences sharedPreferences4 = getSharedPreferences("saveString", MODE_PRIVATE);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();


        editor.clear();
        editor.apply();
        editor1.clear();
        editor1.apply();
        editor2.clear();
        editor2.apply();
        editor3.clear();
        editor3.apply();
        editor4.clear();
        editor4.apply();

        //shutdown will stop all tasks and release some resource.
//        Pump.shutdown();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("preferences", MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = getSharedPreferences("saveCounter", MODE_PRIVATE);
        SharedPreferences sharedPreferences3 = getSharedPreferences("saveBoolean", MODE_PRIVATE);
        SharedPreferences sharedPreferences4 = getSharedPreferences("saveString", MODE_PRIVATE);


        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();
        SharedPreferences.Editor editor4 = sharedPreferences4.edit();


        editor.clear();
        editor.apply();
        editor1.clear();
        editor1.apply();
        editor2.clear();
        editor2.apply();
        editor3.clear();
        editor3.apply();
        editor4.clear();
        editor4.apply();

    }

    public void saveString() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveString", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("saveString", "SJF");
        editor.apply();
    }

    public String getString() {
        SharedPreferences sharedPreferences = getSharedPreferences("saveString", MODE_PRIVATE);
        return sharedPreferences.getString("saveString", null);
    }
}
