package com.example.covid_19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.covid_19.model.IndiaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class India extends AppCompatActivity {

    private static final String API_KEY = "37dac22687msh1236868fea5ce69p1b4592jsn819df0df2f1a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india);
        new SomeTask().execute(API_KEY);
    }

    public class SomeTask extends AsyncTask<String, Void, String> {

        List<IndiaData> indiaDataList = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            Toast.makeText(getApplicationContext(), "Loading COVID-19 Datasets", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... values) {

            String apiKey = values[0];

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://covid-19-india2.p.rapidapi.com/details.php")
                    .get()
                    .addHeader("x-rapidapi-host", "covid-19-india2.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "37dac22687msh1236868fea5ce69p1b4592jsn819df0df2f1a")
                    .build();

            try {

                Response response = client.newCall(request).execute();
                ResponseBody responseBody = response.body();
                String jsonString = responseBody.string();
                return jsonString;

            } catch(IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String jsonString) {

            try {
                JSONObject jsonObject=new JSONObject(jsonString);
                getkeys(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            RecyclerView recyclerView = findViewById(R.id.rv_india);
            IndiaAdapter adapter=new IndiaAdapter(indiaDataList);

            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(linearLayoutManager);



        }

        public void getkey(JSONObject json, String key) throws JSONException {

            if(json.get(key) instanceof JSONObject){
                IndiaData indiaData = new IndiaData(json.getJSONObject(key), key);
                indiaDataList.add(indiaData);
            }

        }

        public void getkeys(JSONObject json) throws JSONException {

            Iterator<?> keys;
            keys=json.keys();
            String nextkeys;
            while(keys.hasNext()){
                nextkeys=(String)keys.next();
                getkey(json, nextkeys);

            }
        }
    }
}