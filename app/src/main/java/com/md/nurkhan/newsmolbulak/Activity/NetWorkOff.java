package com.md.nurkhan.newsmolbulak.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.md.nurkhan.newsmolbulak.R;

public class NetWorkOff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work_off);
    }

    public void onRefresh(View view) {
        Toast.makeText(NetWorkOff.this, "Проверьте сеть?", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(NetWorkOff.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
