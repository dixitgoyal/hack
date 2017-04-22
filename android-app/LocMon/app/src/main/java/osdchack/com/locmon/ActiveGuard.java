package osdchack.com.locmon;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class ActiveGuard extends AppCompatActivity {

    Button sendData;
    Context context;
    LocationManager locationManager;
    LocationListener locationListener;
    Location devLocation;
    Geocoder geocoder;
    String URL = "http://harshgoyal.xyz/locmon/api/index.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_guard);
        context =this;
        sendData = (Button) findViewById(R.id.send_data);
        geocoder = new Geocoder(this, Locale.getDefault());
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                devLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Toast.makeText(context, "switch on the location", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("GPS not enabled");
                builder.setMessage("open and enable location via gps");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
                return;
            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("GPS not found");
                Toast.makeText(context, "switch on the location", Toast.LENGTH_SHORT).show();
                builder.setMessage("open and enable location via gps");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
                builder.setCancelable(false);
                builder.create().show();
                return;
            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 5, locationListener);


        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(devLocation==null)
                {
                    Toast.makeText(context,"please wait...",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    try {
                        List<Address> add = geocoder.getFromLocation(devLocation.getLatitude(), devLocation.getLongitude(), 1);
                        long longD = devLocation.getTime();
                        Date d = new Date(longD);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yy HH:mm:ss");
                        String sDate = sdf.format(d);
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("action","trackUser");
                        params.put("uid","1");
                        params.put("date",sDate.substring(0,8));
                        params.put("timeStart","08:32:07");
                        params.put("timeEnd","09:02:07");
                        params.put("locationBit","1");
                        params.put("activeBit","1");
                        RequestQueue requestQueue = Volley.newRequestQueue(context);
                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, new JSONObject(params)
                                , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(context,"updated",Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(context,"stored in db",Toast.LENGTH_SHORT).show();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
