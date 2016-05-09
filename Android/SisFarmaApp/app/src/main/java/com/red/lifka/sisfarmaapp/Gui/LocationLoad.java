package com.red.lifka.sisfarmaapp.Gui;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.util.Log;

public class LocationLoad extends Activity implements LocationListener{
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    private float latitude, longitude;
    String provider;
    protected boolean gps_enabled,network_enabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        String permission = "android.permission.ACCESS_COARSE_LOCATION";
        int res = context.checkCallingOrSelfPermission(permission);
        if (res == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } else {
            Log.e("Permission error", "No hay permisos GPS");
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = (float) (location.getLatitude());
        longitude = (float) (location.getLongitude());

    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }


    public float getLatitude(){
        return latitude;
    }

    public float getLongitude(){
        return longitude;
    }
}