package com.bigpumpkin.app.ddng_android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bigpumpkin.app.ddng_android.R;

public class ExternalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type= null;
        String id = null;
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                type = uri.getQueryParameter("type");
                id = uri.getQueryParameter("id ");
            }
        }
    }
}
