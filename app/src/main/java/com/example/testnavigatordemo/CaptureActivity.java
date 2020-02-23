package com.example.testnavigatordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.libannotation.ActivityDestination;

@ActivityDestination(pageUrl = "main/tabs/capture")
public class CaptureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
    }
}
