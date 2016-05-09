package com.red.lifka.sisfarmaapp.DB;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.red.lifka.sisfarmaapp.Cliente.Factura;
import com.red.lifka.sisfarmaapp.Cliente.LineaFactura;
import com.red.lifka.sisfarmaapp.Cliente.ProductoFarmacia;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONManager {

    public String getJSON(String address) {
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(address);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

            } else {
                Log.e("JSONManager", "Failed to get JSON object");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


    public String postJSON(JSONObject json, String url) {


        int TIMEOUT_MILLISEC = 10000;  // = 10 seconds
        StringBuilder builder = new StringBuilder();
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, TIMEOUT_MILLISEC);
        HttpConnectionParams.setSoTimeout(httpParams, TIMEOUT_MILLISEC);
        HttpClient client = new DefaultHttpClient(httpParams);

        HttpPost request = new HttpPost(url);

        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");


        try {
            request.setEntity(new ByteArrayEntity(
                    json.toString().getBytes("UTF8")));
            HttpResponse response = client.execute(request);


            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);

                }
            } else {
                Log.e("ERROR_ status  2", "STATUS --> " + statusCode);

            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.e("ERROR_ ClientProtocol", e.getMessage());
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
