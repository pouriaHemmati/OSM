package org.pouria.osm.Map;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import org.pouria.osm.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OsmActivity extends AppCompatActivity {

    private IMapController mapController;

    private static final String TAG = "OsmActivity";
    RoadManager roadManager = new OSRMRoadManager(this);

    private GeoPoint startPoint;
    private GeoPoint Point1;
    private GeoPoint Point2;
    private GeoPoint Point3;
    private GeoPoint Point4;
    private GeoPoint Point5;
    private Marker startMarker;
    private Marker Marker1;
    private Marker Marker2;
    private Marker Marker3;
    private Marker Marker4;
    private Marker Marker5;
    private Polyline roadOverlay;
    private Road minRoad;


    ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
    ArrayList<GeoPoint> waypoints1 = new ArrayList<GeoPoint>();
    ArrayList<GeoPoint> waypoints2 = new ArrayList<GeoPoint>();
    ArrayList<GeoPoint> waypoints3 = new ArrayList<GeoPoint>();
    ArrayList<GeoPoint> waypoints4 = new ArrayList<GeoPoint>();
    ArrayList<GeoPoint> waypoints5 = new ArrayList<GeoPoint>();

    private float distance;
    // find view
    @BindView(R.id.mapView)
    MapView map;
    @BindView(R.id.card_view)
    CardView card_view;
    @BindView(R.id.custom_toast_message)
    TextView custom_toast_message;
    @BindView(R.id.layout_loading)
    RelativeLayout layout_loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Context ctx = getApplicationContext();

        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        if (Build.VERSION.SDK_INT >= 23) {
            if (isStoragePermissionGranted()) {

            }
        }

        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(18);

        GeoPoint show = new GeoPoint(35.715298, 51.404343);
        mapController.setCenter(show);
        MyLocationNewOverlay myLocationoverlay = new MyLocationNewOverlay(map);
        myLocationoverlay.enableFollowLocation();
        myLocationoverlay.enableMyLocation();
        map.getOverlays().add(myLocationoverlay);

        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                return false;
            }

            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public boolean longPressHelper(GeoPoint p) {

                OsmActivity.this.runOnUiThread(() -> {
                    if (startPoint == null) {
                        startPoint = new GeoPoint(p.getLatitude(), p.getLongitude());
                        waypoints.add(0,startPoint);
                        waypoints1.add(0,startPoint);
                        waypoints2.add(0,startPoint);
                        waypoints3.add(0,startPoint);
                        waypoints4.add(0,startPoint);
                        waypoints5.add(0,startPoint);
                        startMarker = new Marker(map);
                        startMarker.setPosition(startPoint);
                        startMarker.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble, map);
                        startMarker.setInfoWindow(infoWindow);
                        map.getOverlays().add(startMarker);
                        startMarker.setTitle("Title of the marker");
                        startMarker.setIcon(getResources().getDrawable(R.drawable.ic_start_location));
                        if (Point5 !=null){
                            layout_loading.setVisibility(View.VISIBLE);
                        }
                        startMarker.setOnMarkerClickListener((marker, mapView) -> {
                            Toast.makeText(ctx , "مبدا" , Toast.LENGTH_LONG).show();
                            startMarker.setDraggable(true);
                            return false;
                        });
                    } else if (startPoint != null && Point1 == null) {
                        Point1 = new GeoPoint(p.getLatitude(), p.getLongitude());
                        waypoints.add(Point1);
                        waypoints1.add(Point1);
                        Marker1 = new Marker(map);
                        Marker1.setPosition(Point1);
                        Marker1.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble, map);
                        Marker1.setInfoWindow(infoWindow);
                        map.getOverlays().add(Marker1);
                        Marker1.setTitle("Title of the marker");
                        Marker1.setIcon(getResources().getDrawable(R.drawable.ic_add_location));
                        Marker1.setOnMarkerClickListener((marker, mapView) -> {
                            Toast.makeText(ctx , "مقصد شماره یک" , Toast.LENGTH_LONG).show();
                            startMarker.setDraggable(true);
                            return false;
                        });

                    } else if (startPoint != null && Point2 == null) {
                        Point2 = new GeoPoint(p.getLatitude(), p.getLongitude());
                        waypoints.add(Point2);
                        waypoints2.add(Point2);
                        Marker2 = new Marker(map);
                        Marker2.setPosition(Point2);
                        Marker2.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble, map);
                        Marker2.setInfoWindow(infoWindow);
                        map.getOverlays().add(Marker2);
                        Marker2.setTitle("Title of the marker");
                        Marker2.setIcon(getResources().getDrawable(R.drawable.ic_add_location));
                        Marker2.setOnMarkerClickListener((marker, mapView) -> {
                            Toast.makeText(ctx , "مقصد شماره دو" , Toast.LENGTH_LONG).show();
                            startMarker.setDraggable(true);
                            return false;
                        });
                    } else if (startPoint != null && Point3 == null) {
                        Point3 = new GeoPoint(p.getLatitude(), p.getLongitude());
                        waypoints.add(Point3);
                        waypoints3.add(Point3);
                        Marker3 = new Marker(map);
                        Marker3.setPosition(Point3);
                        Marker3.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble, map);
                        Marker3.setInfoWindow(infoWindow);
                        map.getOverlays().add(Marker3);
                        Marker3.setTitle("Title of the marker");
                        Marker3.setIcon(getResources().getDrawable(R.drawable.ic_add_location));
                        Marker3.setOnMarkerClickListener((marker, mapView) -> {
                            Toast.makeText(ctx , "مقصد شماره سه" , Toast.LENGTH_LONG).show();
                            startMarker.setDraggable(true);
                            return false;
                        });
                    } else if (startPoint != null && Point4 == null) {
                        Point4 = new GeoPoint(p.getLatitude(), p.getLongitude());
                        waypoints.add(Point4);
                        waypoints4.add(Point4);
                        Marker4 = new Marker(map);
                        Marker4.setPosition(Point4);
                        Marker4.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble, map);
                        Marker4.setInfoWindow(infoWindow);
                        map.getOverlays().add(Marker4);
                        Marker4.setTitle("Title of the marker");
                        Marker4.setIcon(getResources().getDrawable(R.drawable.ic_add_location));
                        Marker4.setOnMarkerClickListener((marker, mapView) -> {
                            Toast.makeText(ctx , "مقصد شماره چهار" , Toast.LENGTH_LONG).show();
                            startMarker.setDraggable(true);
                            return false;
                        });
                    } else if (startPoint != null && Point5 == null) {
                        Point5 = new GeoPoint(p.getLatitude(), p.getLongitude());
                        waypoints.add(Point5);
                        waypoints5.add(Point5);
                        Marker5 = new Marker(map);
                        Marker5.setPosition(Point5);
                        Marker5.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                        InfoWindow infoWindow = new MyInfoWindow(R.layout.bonuspack_bubble, map);
                        Marker5.setInfoWindow(infoWindow);
                        map.getOverlays().add(Marker5);
                        Marker5.setTitle("Title of the marker");
                        Marker5.setIcon(getResources().getDrawable(R.drawable.ic_add_location));
                        Marker5.setOnMarkerClickListener((marker, mapView) -> {
                            Toast.makeText(ctx , "مقصد شماره پنج" , Toast.LENGTH_LONG).show();
                            startMarker.setDraggable(true);
                            return false;
                        });
                        layout_loading.setVisibility(View.VISIBLE);
                    }
                    if (startPoint != null && Point5 != null) {

                        Road road1 = roadManager.getRoad(waypoints1);
                        Road road2 = roadManager.getRoad(waypoints2);
                        Road road3 = roadManager.getRoad(waypoints3);
                        Road road4 = roadManager.getRoad(waypoints4);
                        Road road5 = roadManager.getRoad(waypoints5);
                        ArrayList<Road> roads = new ArrayList<>();
                        roads.add(road1);
                        roads.add(road2);
                        roads.add(road3);
                        roads.add(road4);
                        roads.add(road5);

                        Log.d("mDuration 1", String.valueOf(road1.mLength));
                        Log.d("mDuration 2", String.valueOf(road2.mLength));
                        Log.d("mDuration 3", String.valueOf(road3.mLength));
                        Log.d("mDuration 4", String.valueOf(road4.mLength));
                        Log.d("mDuration 5", String.valueOf(road5.mLength));

                        minRoad = road1;
                        for (int i = 1; i < roads.size(); i++) {
                            if (roads.get(i).mLength < minRoad.mLength) {
                                minRoad = roads.get(i);
                            }
                        }

                        runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                roadOverlay = RoadManager.buildRoadOverlay(minRoad, Color.RED, 10);
                                map.getOverlays().add(roadOverlay);
                                DecimalFormat df = new DecimalFormat("#.##");
                                distance = (Float.parseFloat(df.format(roadOverlay.getDistance()/1000)));
                                map.invalidate();
                                custom_toast_message.setText(distance + " کیلومتر ");
                                card_view.setVisibility(View.VISIBLE);
                                layout_loading.setVisibility(View.GONE);
                            }
                        });
                    }
                });

                return false;
            }
        };

        MapEventsOverlay OverlayEvents = new MapEventsOverlay(getBaseContext(), mReceive);
        map.getOverlays().
                add(OverlayEvents);


    }

    // on click
    @OnClick(R.id.fab_startMarkerClear)
    public void fab_startMarkerClear(){
        map.getOverlays().remove(startMarker);
        startPoint = null;
        map.getOverlays().remove(roadOverlay);
        minRoad =null;
        roadOverlay = null;
        waypoints.remove(0);
        waypoints1.remove(0);
        waypoints2.remove(0);
        waypoints3.remove(0);
        waypoints4.remove(0);
        waypoints5.remove(0);
        map.invalidate();
        card_view.setVisibility(View.GONE);
    }

    @OnClick(R.id.fab_clearAllMark)
    public void fab_clearAllMark(){
        map.getOverlays().remove(Marker1);
        Point1 = null;
        map.getOverlays().remove(Marker2);
        Point2 = null;
        map.getOverlays().remove(Marker3);
        Point3 = null;
        map.getOverlays().remove(Marker4);
        Point4 = null;
        map.getOverlays().remove(Marker5);
        Point5 = null;
        map.getOverlays().remove(roadOverlay);
        roadOverlay = null;
        if (waypoints1.size() > 1){
            waypoints1.remove(1);
            waypoints2.remove(1);
            waypoints3.remove(1);
            waypoints4.remove(1);
            waypoints5.remove(1);
        } else {
            waypoints1.remove(0);
            waypoints2.remove(0);
            waypoints3.remove(0);
            waypoints4.remove(0);
            waypoints5.remove(0);
        }
        map.invalidate();
        card_view.setVisibility(View.GONE);
    }


    public void onResume() {
        super.onResume();
        if (map != null)
            map.onResume();
    }

    public void onPause() {
        super.onPause();
        if (map != null)
            map.onPause();
    }


    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v(TAG, "Permission: " + permissions[0] + "was " + grantResults[0]);

        }
    }
}