package com.red.lifka.sisfarmaapp.DB;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONManager {

    public String getJSON(String address) {

        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(address);
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            client.setRequestProperty("X-Requested-With", "application/json;charset=utf-8");
            client.setRequestMethod("GET");

            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

            InputStream content = client.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR_ get", e.getMessage());
        }

        return builder.toString();

    }


    public String postJSON(JSONObject json, String address) {
        StringBuilder builder = new StringBuilder();

        try {
            URL url = new URL(address);
            HttpURLConnection client = (HttpURLConnection) url.openConnection();


            client.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            client.setRequestProperty("X-Requested-With", "application/json;charset=utf-8");
            client.setRequestMethod("POST");

            OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
            String output = json.toString();
            writer.write(output);
            writer.flush();
            writer.close();

            InputStream content = client.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Log.e("ERROR_ UnsupportedEnc", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ERROR_ IOException", e.getMessage());
        }

        return builder.toString();

    }

}
