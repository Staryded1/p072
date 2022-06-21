package com.example.p072;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.p070.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class newkinoteatr extends AppCompatActivity {
    TextView tvInfo;
    EditText tvName;
    EditText tvAddress;
    newkinoteatr mt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newkinoteatr);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvName = (EditText) findViewById(R.id.editTextTextPersonName);
        tvAddress =(EditText) findViewById(R.id.editTextTextPersonAddress);

    }
    public void onclick(View v) {
        mt = new newkinoteatr();
        mt.execute(tvName.getText().toString(),tvAddress.getText().toString());
    }

    private void execute(String toString, String toString1) {
    }

    class MyTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Begin");
        }
        @Override
        protected Void doInBackground(String... params) {
            HttpURLConnection myConnection=null;
            try {
                URL githubEndpoint = new URL("http://localhost:8080/kinoo/");
                myConnection =
                        (HttpURLConnection) githubEndpoint.openConnection();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            myConnection.setDoOutput(true);
            try {
                myConnection.getOutputStream().write( ("id=1&nazvanie=" + params[0]+"&adres="+params[1]).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            };

            int i=0;
            try {
                i = myConnection.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
            }
//                tvInfo.setText(str);
            if (i==200) {
            }
            return null;
        }
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            tvInfo.setText("End");
        }
    }
}

