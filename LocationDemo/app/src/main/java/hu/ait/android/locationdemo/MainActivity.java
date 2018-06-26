package hu.ait.android.locationdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.ait.android.locationdemo.location.LocationMonitor;

public class MainActivity extends AppCompatActivity
  implements LocationMonitor.LocationObserver, OnMapReadyCallback {

    private GoogleMap mMap;

    @BindView(R.id.tvData)
    TextView tvData;
    @BindView(R.id.btnStart)
    Button btnStart;

    private LocationMonitor locationMonitor = null;

    private Location prevLocation = null;
    private float distance = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        requestNeededPermission();

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Toast...
            }

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    101);
        } else {
            // we have the permission
            btnStart.setEnabled(true);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 101) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "Permission granted, jupeee!", Toast.LENGTH_SHORT).show();

                // start our job, we have the permission
                btnStart.setEnabled(true);
            } else {
                Toast.makeText(this, "Permission not granted :(", Toast.LENGTH_SHORT).show();
                btnStart.setEnabled(false);
            }
        }
    }


    @OnClick(R.id.btnStart)
    void startClicked() {
        locationMonitor = new LocationMonitor();
        try {
            locationMonitor.startLocationMonitoring(this,
                    (LocationManager) getSystemService(LOCATION_SERVICE)
            );
        }catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (locationMonitor != null) {
            locationMonitor.stopLocatoinMonitoring();
        }
    }

    @Override
    public void locationAvailable(Location location) {
        if (prevLocation != null && location.getAccuracy() <= 20 &&
                (location.getTime() - prevLocation.getTime()) > 5000) {
            distance += location.distanceTo(prevLocation);
        }
        prevLocation = location;

        Double lat = location.getLatitude();
        Double lng = location.getLongitude();

        StringBuilder sb = new StringBuilder();
        sb.append("DISTANCE (m): "+distance);
        sb.append("\n");
        sb.append("Location: "+lat+", "+lng);
        sb.append("\n");
        sb.append("Provider: "+location.getProvider());
        sb.append("\n");
        sb.append("Accuracy: "+location.getAccuracy());
        sb.append("\n");
        sb.append("Altitude: "+location.getAltitude());
        sb.append("\n");
        sb.append("Speed: "+location.getSpeed());
        sb.append("\n");
        sb.append("Bearing: "+location.getBearing());

        tvData.setText(sb.toString());

        //moveToLocation(new LatLng(location.getLatitude(), location.getLongitude()));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(17)
                .bearing(location.getBearing())
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void locationInfo(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }


    private void moveToLocation(LatLng latLng) {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setTrafficEnabled(true);

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        PolygonOptions polyRect = new PolygonOptions().add(new LatLng(44, 19),
                new LatLng(44, 26),
                new LatLng(48, 26),
                new LatLng(48, 19));
        Polygon polygon = mMap.addPolygon(polyRect);
        polygon.setFillColor(Color.argb(50, 0, 255, 0));


        LatLng latLng = new LatLng(47, 19);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Hungary"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions().
                        position(latLng).
                        title("Marker").snippet("Long text of the marker")
                        .icon(BitmapDescriptorFactory.fromResource(
                                R.drawable.marker));

                Marker marker = mMap.addMarker(markerOptions);
                marker.setDraggable(true);
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }
}
