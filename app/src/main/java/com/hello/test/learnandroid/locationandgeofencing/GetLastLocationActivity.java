package com.hello.test.learnandroid.locationandgeofencing;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.hello.test.learnandroid.R;

import java.text.DateFormat;

public class GetLastLocationActivity extends AppCompatActivity {
  private GoogleApiClient googleApiClient;
  private TextView textView;
  private Button button;
  private static final int REQUEST_FINE_LOCATION = 1101;

  private GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
    @Override
    public void onConnected(@Nullable Bundle bundle) {
      button.setEnabled(true);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
  };

  private GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = new GoogleApiClient.OnConnectionFailedListener() {
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
      Toast.makeText(GetLastLocationActivity.this, connectionResult.toString(),Toast.LENGTH_LONG).show();
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestPermission();
    setContentView(R.layout.activity_get_last_location);
    textView = (TextView)findViewById(R.id.textView);
    button = (Button)findViewById(R.id.button);
    button.setEnabled(false);
    setupGoogleAPIClient();
  }

  protected synchronized void setupGoogleAPIClient(){
    googleApiClient = new GoogleApiClient.Builder(this)
      .addConnectionCallbacks(connectionCallbacks)
      .addOnConnectionFailedListener(onConnectionFailedListener)
      .addApi(LocationServices.API)
      .build();
    googleApiClient.connect();
  }

  public void getLocation(View view){
    try{
      Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
      if(lastLocation != null){
        textView.setText(
          DateFormat.getTimeInstance().format(lastLocation.getTime()) + "\n" +
            "Latitude=" + lastLocation.getLatitude() + "\n" +
            "Longitude=" + lastLocation.getLongitude()
        );
      }else{
        Toast.makeText(GetLastLocationActivity.this, "null", Toast.LENGTH_LONG).show();
      }
    }catch (SecurityException e){
      e.printStackTrace();
    }
  }

  private void requestPermission(){
    int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

    if(permission != PackageManager.PERMISSION_GRANTED){
      if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        Manifest.permission.ACCESS_FINE_LOCATION)) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Permission to access Fine Access Location be required for this app")
          .setTitle("Permission required");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int id) {
            makeRequest();
          }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
      }else{
        makeRequest();
      }
    }
  }

  protected void makeRequest() {
    ActivityCompat.requestPermissions(this,
      new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
      REQUEST_FINE_LOCATION);
  }
}
