package com.md.nurkhan.newsmolbulak.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.md.nurkhan.newsmolbulak.Model.NewsModel;
import com.md.nurkhan.newsmolbulak.R;

public class InDeteliseTwoNews extends AppCompatActivity {

    ImageView imgD;
    TextView txtTitleD, txtDescriptionD, txtDataD;
    NewsModel news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_detelise_two_news);

        imgD = findViewById(R.id.imgDetelse);
        txtTitleD = findViewById(R.id.txtTitleD);
        txtDescriptionD = findViewById(R.id.txtDescriptionD);
        txtDataD = findViewById(R.id.txtDataD);


       news = (NewsModel) getIntent().getSerializableExtra("key");

        if (news != null) {
            Glide.with(this).load(news.getImage()).into(imgD);
            txtTitleD.setText(news.getTitle());
            txtDescriptionD.setText(news.getDescription());
            txtDataD.setText(news.getData());
        }
    }

    public void onShare(View view) {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, news.getTitle());
        shareIntent.putExtra(Intent.EXTRA_STREAM, news.getImage() );
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share images..."));
    }

    public void onSave(View view) {
        //Здесь будет храниться в Базу данных не успел сделать

//        if (news == null) news = new NewsModel();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    App.getDatabase().newsDao().update(news);
//                finish();
//            }
//        }).start();
    }
}
