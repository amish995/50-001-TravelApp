package amish.travelapp;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class AttractionLocator extends MainActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_locator);

        initDrawer();

        final MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        final Button enterButton = (Button) findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action to be performed when enter button is pressed in the Attraction Locator Activity
                // Supposed to pass the data from the text field to the map fragment via some autocorrect mechanism
                OnSearch(v);
            }
        });

        final Button mapTypeButton = (Button) findViewById(R.id.type_button);
        mapTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL){
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    mapTypeButton.setText("NORMAL");
                }
                else{
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    mapTypeButton.setText("SATELLITE");
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng singapore = new LatLng(-1, 103);
        mMap.addMarker(new MarkerOptions().position(singapore).title("Welcome to Singapore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(singapore, 5));
    }

    public void OnSearch(View view){
        List<Address> addressList = null;
        final EditText attrName = (EditText) findViewById(R.id.attr_name);
        String attr_to_loc = attrName.getText().toString();
        if (attr_to_loc != null || !attr_to_loc.equals("")){
            Geocoder geocoder = new Geocoder(this);
            try {
                addressList = geocoder.getFromLocationName(attr_to_loc, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = addressList.get(0);
            LatLng latlng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(latlng).title(attr_to_loc));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        }
    }
}
