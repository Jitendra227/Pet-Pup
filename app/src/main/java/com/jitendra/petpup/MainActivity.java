package com.jitendra.petpup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jitendra.petpup.Adapter.PupListAdapter;
import com.jitendra.petpup.model.data.PupItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Mainactivittyy";

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


        extractBreedNameFromJson();


    }

//    private void fetchData() {
//        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
//                response -> {
//                    recyclerView.setVisibility(View.VISIBLE);
//                    JSONObject msg = obj.getJSONObject("message");
//
//                        try {
//                            Log.d(TAG, "onResponse: successfull");
//
//                            JSONOArray obj = response.getJSONObject(i);
//                            String breedName = obj.getString("message");
//                            nameList.add(new PupItems(breedName, "succcess"));
//                            buildRecyclerView();
//                        } catch (JSONException e) {
//                            Log.d(TAG, "onResponse: unsuccessful!!");
//                            e.printStackTrace();
//                        }
//
//
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(jsonObjectRequest);
//    }
//
    private void buildRecyclerView() {
        pupListAdapter = new PupListAdapter(nameList, MainActivity.this, R.layout.pup_list);
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

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }



}


//    private void fetchData() {
//        Call<List<PupItems>> call = apiCallInterface.getPupList();
//
//        call.enqueue(new Callback<List<PupItems>>() {
//            @Override
//            public void onResponse(Call<List<PupItems>> call, Response<List<PupItems>> response) {
//                if(!response.isSuccessful()){
//                    Log.d(TAG, "onResponse: Failed");
//                    return;
//                }
//
//                Log.d(TAG, "onResponse: Successful");
//
//
//            @Override
//            public void onFailure(Call<List<PupItems>> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//                t.printStackTrace();
//
//            }
//        });
//    }
