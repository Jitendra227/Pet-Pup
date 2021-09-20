package com.jitendra.petpup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.jitendra.petpup.Adapter.PupListAdapter;
import com.jitendra.petpup.model.ApiCallInterface;
import com.jitendra.petpup.model.data.PupItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";

    private RecyclerView recyclerView;
    private ArrayList<PupItems> nameList;
    private PupListAdapter pupListAdapter;
    private ApiCallInterface apiCallInterface;

    String url = "https://dog.ceo/api/breeds/list/all";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        nameList = new ArrayList<>();

        fetchData();

        buildRecyclerView();

    }

    private void fetchData() {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    recyclerView.setVisibility(View.VISIBLE);
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            Log.d(TAG, "onResponse: successfull");
                            JSONObject obj = response.getJSONObject(i);
                            String breedName = obj.getString("message");
                            nameList.add(new PupItems(breedName, "succcess"));
                            buildRecyclerView();
                        } catch (JSONException e) {
                            Log.d(TAG, "onResponse: unsuccessful!!");
                            e.printStackTrace();
                        }
                    }

                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {
        pupListAdapter = new PupListAdapter(nameList, MainActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(pupListAdapter);
    }
}

//    public void getBreedData() {
//        Call<PupItems> call = apiCallInterface.getBreedImages("affenpinscher");
//
//        call.enqueue(new Callback<PupItems>() {
//            @Override
//            public void onResponse(Call<PupItems> call, Response<PupItems> response) {
//                    if (response.isSuccessful() && response.body() != null) {
//                        PupItems product = (PupItems) response.body();
//                        nameList.add(product);
//
//                        Log.d("Status", product.getStatus());
//                        Log.d("Message", product.getMessage().toString());
//                    }
//                }
//
//            @Override
//            public void onFailure(Call<PupItems> call, Throwable t) {
//                Log.d(TAG, "onFailure: "+t.getMessage());
//                t.printStackTrace();
//                Toast.makeText(MainActivity.this, "You are offline", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
//    private void fetchBreedData() {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d(TAG, "onResponse: Success");
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            JSONArray breedArray = obj.getJSONArray("message");
//
//                            for (int i = 0; i < breedArray.length(); i++) {
//                                JSONObject breedObj = breedArray.getJSONObject(i);
//                                PupItems pupIndividualItems = new PupItems(breedObj.getString("message"));
//
//                                nameList.add(pupIndividualItems);
//
//                                PupListAdapter adapter = new PupListAdapter(nameList,getApplicationContext());
//                                recyclerView.setAdapter(adapter);
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            PupListAdapter adapter = new PupListAdapter(nameList,getApplicationContext());
//                            recyclerView.setAdapter(adapter);
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        //adding the string request to request queue
//        requestQueue.add(stringRequest);
//    }


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
