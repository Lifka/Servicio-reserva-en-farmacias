package com.red.lifka.sisfarmaapp.Gui;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.red.lifka.sisfarmaapp.R;

import java.util.List;

public class LocationLoad extends Service implements LocationListener {
    LocationManager mLocationManager;
    Location lastKnownLocation;
    Context context;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 metros
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minuto

    public LocationLoad(Context context){
        this.context = context;


        mLocationManager =  (LocationManager) context.getSystemService(LOCATION_SERVICE);
        boolean isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean  isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            Log.e("ERROR_ permisos", "Sin permison de localización");
        } else {


             try {

                 if (isGPSEnabled && lastKnownLocation == null) {
                         /****/Log.d("Localización", "Obteniendo localización mediante GPS");

                         mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES,
                                 MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                         lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                     }

                 if (isNetworkEnabled && lastKnownLocation == null) {
                      /****/Log.d("Localización", "Obteniendo localización mediante red");
                      mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES,
                              MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                      lastKnownLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                 }

                 if (!isGPSEnabled && !isNetworkEnabled) {
                     Log.e("ERROR_ localización", "Ningún medio de localización ");
                 }

             } catch (Exception e) {
                 Log.e("ERROR_ permisos", "Sin permison de localización " + e.getMessage());
             }

            if (lastKnownLocation == null){
                lastKnownLocation = new Location(new String());
                lastKnownLocation.setLatitude(0.0f);
                lastKnownLocation.setLongitude(0.0f);
            }

        }
    }


    @Override
    public void onLocationChanged(Location location) {
        lastKnownLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public Location getLocation(){
        return lastKnownLocation;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}