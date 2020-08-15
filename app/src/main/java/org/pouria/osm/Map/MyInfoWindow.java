package org.pouria.osm.Map;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;
import org.pouria.osm.R;

class MyInfoWindow extends MarkerInfoWindow {

    public MyInfoWindow(int layoutResId, MapView mapView) {
        super(layoutResId, mapView);
    }
    public void onClose() {
    }

    public void onOpen(Object arg0) {
//        LinearLayout layout = (LinearLayout) mView.findViewById(R.id.bubble_layout);
        Button btnMoreInfo = (Button) mView.findViewById(R.id.bubble_moreinfo);

//        layout.setOnClickListener(v -> {
//            Toast.makeText(v.getContext(), "Button clicked", Toast.LENGTH_LONG).show();
//        });
        btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Button clicked", Toast.LENGTH_LONG).show();
            }
        });
    }
}
