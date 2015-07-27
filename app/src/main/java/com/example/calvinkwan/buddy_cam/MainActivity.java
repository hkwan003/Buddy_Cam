package com.example.calvinkwan.buddy_cam;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<List_User_Cameras> cam_list_results = GetSearchResults();

        final ListView lv = (ListView) findViewById(R.id.srListView);
        lv.setAdapter(new List_cameras_adapter(getApplicationContext(), cam_list_results));


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                Object o = lv.getItemAtPosition(position);
                List_User_Cameras fullObject = (List_User_Cameras)o;
                Toast.makeText(getApplicationContext(), "this is my Toast message!!! =)", Toast.LENGTH_LONG).show();
            }
        });
    }

    private ArrayList<List_User_Cameras> GetSearchResults()
    {
        ArrayList<List_User_Cameras> results = new ArrayList<List_User_Cameras>();

        List_User_Cameras sr = new List_User_Cameras();
        sr.setName("Camera 1");
        sr.setCityState("192.132.43.238");
        results.add(sr);

        sr = new List_User_Cameras();
        sr.setName("Camera 2");
        sr.setCityState("192.172.68.232");
        results.add(sr);

        sr = new List_User_Cameras();
        sr.setName("Camera 3");
        sr.setCityState("192.172.68.232");
        results.add(sr);

        sr = new List_User_Cameras();
        sr.setName("Camera 4");
        sr.setCityState("192.172.68.232");
        results.add(sr);

        sr = new List_User_Cameras();
        sr.setName("Camera 5");
        sr.setCityState("192.172.68.232");
        results.add(sr);

        return results;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
