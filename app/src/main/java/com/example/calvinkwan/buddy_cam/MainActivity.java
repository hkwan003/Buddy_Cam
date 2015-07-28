package com.example.calvinkwan.buddy_cam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<IpCamera> cameraList = getSearchResults();

        final ListView listView = (ListView) findViewById(R.id.srListView);
        listView.setAdapter(new CameraListAdapter(getApplicationContext(), cameraList));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                IpCamera ipCamera = (IpCamera) listView.getItemAtPosition(position);;
                Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)", Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<IpCamera> getSearchResults() {
        ArrayList<IpCamera> results = new ArrayList<IpCamera>();

        IpCamera sr = new IpCamera();
        sr.setName("Camera 1");
        sr.setCityState("192.132.43.238");
        results.add(sr);

        sr = new IpCamera();
        sr.setName("Camera 2");
        sr.setCityState("192.172.68.232");
        results.add(sr);

        sr = new IpCamera();
        sr.setName("Camera 3");
        sr.setCityState("192.172.68.232");
        results.add(sr);

        sr = new IpCamera();
        sr.setName("Camera 4");
        sr.setCityState("192.172.68.232");
        results.add(sr);

        sr = new IpCamera();
        sr.setName("Camera 5");
        sr.setCityState("192.172.68.232");
        results.add(sr);

        return results;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
