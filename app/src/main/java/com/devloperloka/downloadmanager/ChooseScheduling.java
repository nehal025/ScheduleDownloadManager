package com.devloperloka.downloadmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.huxq17.download.config.DownloadConfig;

public class ChooseScheduling extends AppCompatActivity {
    Button fcfs;
    Button sjf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_scheduling);

        long minUsableStorageSpace = 10 * 1024L;
        DownloadConfig.newBuilder().setMaxRunningTaskNum(1).setMinUsableStorageSpace(minUsableStorageSpace).build();

        fcfs = findViewById(R.id.button1);
        sjf = findViewById(R.id.button2);

        fcfs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ChooseScheduling.this, MainActivity.class);
                String fcfs = "FCFS";
                intent.putExtra("fcfs", fcfs);
                startActivity(intent);

            }
        });


        sjf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(ChooseScheduling.this, MainActivity.class);
                String sjf = "SJF";
                intent.putExtra("sjf", sjf);
                startActivity(intent);

            }
        });


    }
}

