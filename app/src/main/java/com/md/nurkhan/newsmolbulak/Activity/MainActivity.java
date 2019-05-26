package com.md.nurkhan.newsmolbulak.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.md.nurkhan.newsmolbulak.Adapter.RecycleNewsAdapter;
import com.md.nurkhan.newsmolbulak.Model.NewsModel;
import com.md.nurkhan.newsmolbulak.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecycleNewsAdapter adapter;
    ArrayList<NewsModel> listModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);

        if (isNetvorkAvaiteble(MainActivity.this)) {
            new MyTask().execute();
            Log.e("ONLINE", "onCreate: " + "eeeeeest");
        } else {

            Intent intent = new Intent(MainActivity.this, NetWorkOff.class);
            startActivity(intent);
            finish();
            Log.e("OFFLINE", "onCreate: " + "neeeeeeet");
        }

    }

    public boolean isNetvorkAvaiteble(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo().isConnected();
    }


    @SuppressLint("StaticFieldLeak")
    public class MyTask extends AsyncTask<Void, Void, ArrayList<NewsModel>> {


        @Override
        protected ArrayList<NewsModel> doInBackground(Void... params) {

            ArrayList<NewsModel> list = new ArrayList<>();

            try {
                URL url = new URL("https://newsapi.org/v2/everything?q=bitcoin&from=2019-04-26&sortBy=publishedAt&apiKey=9ab68cc38e644d67bae1ee56507c1091");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(is));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line).append('\n');
                }
                JSONObject object = new JSONObject(total.toString());
                JSONArray array = object.getJSONArray("articles");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);

                    NewsModel news = new NewsModel();

//                    news.setId(jsonObject.getInt("id"));

                    news.setTitle(jsonObject.getString("title"));

                    news.setDescription(jsonObject.getString("description"));

                    news.setImage(jsonObject.getString("urlToImage"));

                    news.setData(jsonObject.getString("publishedAt"));

                    list.add(news);

                }

                listModel = list;
                adapter = new RecycleNewsAdapter(listModel);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsModel> newss) {
            super.onPostExecute(newss);
            LinearLayoutManager linearLayoutManagerVertical = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(linearLayoutManagerVertical);
            recyclerView.setAdapter(adapter);
            RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
            recyclerView.setItemAnimator(itemAnimator);
            recyclerView.setHasFixedSize(true);
            adapter.notifyDataSetChanged();
            if (adapter != null) {
                Toast.makeText(MainActivity.this, "Загружено!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Не Загруженно!", Toast.LENGTH_SHORT).show();
            }
            adapter.setClickListener(new RecycleNewsAdapter.newsClickListener() {
                @Override
                public void onNewsClick(int pos) {
                    NewsModel newss = listModel.get(pos);
                    Intent intent = new Intent(MainActivity.this, InDeteliseTwoNews.class);
                    intent.putExtra("key", newss);
                    startActivity(intent);
                }
            });
        }
    }
}
