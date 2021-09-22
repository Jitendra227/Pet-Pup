package com.jitendra.petpup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.jitendra.petpup.Adapter.PupListAdapter;
import com.jitendra.petpup.model.data.PupItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = "Mainactivittyy";
    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle toggle = null;
    private NavigationView navView;

    private RecyclerView recyclerView;
    private ArrayList<PupItems> nameList;
    private PupListAdapter pupListAdapter;

    private static final String url = "https://dog.ceo/api/breeds/list/all";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        nameList = new ArrayList<>();

        drawerLayout = findViewById(R.id.drawerLayout);
        navView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        extractBreedNameFromJson();
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        navView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //Log.i(TAG, "onDrawerSlide");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                Log.i(TAG, "onDrawerOpened");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                Log.i(TAG, "onDrawerClosed");
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //Log.i(TAG, "onDrawerStateChanged");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            if (getApplicationContext()==MainActivity.this){
                Toast.makeText(this, "Already in home screen", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }


        } else if (id == R.id.nav_RandomScreen) {
            if (getApplicationContext()==MainActivity.this){
                Toast.makeText(this, "Already in random screen", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = new Intent(getApplicationContext(), RandomBreedScrren.class);
                startActivity(intent);
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void buildRecyclerView() {
        pupListAdapter = new PupListAdapter(nameList, MainActivity.this, R.layout.pup_list, new PupListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PupItems item) {
                Intent intent = new Intent(MainActivity.this, BreedDetailScreen.class);
                intent.putExtra("breed_name_list", item.getMessage().toString());
                startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(pupListAdapter);
    }

    private void extractBreedNameFromJson() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        List<String> breedNameList = new ArrayList<>();
                        Log.d(TAG, "onResponse: Success");
                        try {

                            JSONObject obj = new JSONObject(response);
                            JSONObject msg = obj.getJSONObject("message");
                            //JSONArray australianArray = msg.getJSONArray("australian");
                            //int size = australianArray.length();
                            //String dog =  australianArray.get(0).toString();
                            //Log.d(TAG, size + " " +australianArray + dog);

                            Log.d(TAG, "onResponse: Entered Try block --->");

                            for (Iterator<String> iter = msg.keys(); iter.hasNext(); ) {
                                String key = iter.next();
                                Log.d("key", key);

                                nameList.add(new PupItems(key,"success"));

                            }

                            Log.d("messageObj", msg.toString());
//                            JSONArray breedArray = obj.getJSONArray("message");
//
//
//                                JSONObject breedObj = breedArray.getJSONObject(i);
//                                PupItems pupIndividualItems = new PupItems(breedObj.getString("message"));
//
//                                nameList.add(pupIndividualItems);
//                           }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                        buildRecyclerView();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

}
