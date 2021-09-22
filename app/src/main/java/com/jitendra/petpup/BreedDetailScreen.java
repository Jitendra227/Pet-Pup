package com.jitendra.petpup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.JsonSyntaxException;
import com.jitendra.petpup.Adapter.BreedDetailAdapter;
import com.jitendra.petpup.model.data.BreedImages;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BreedDetailScreen extends AppCompatActivity {

    private static final String TAG = "BreedDetailScreen";

    private ArrayList<BreedImages> imageList;
    private RecyclerView recyclerView;
    private BreedDetailAdapter breedDetailAdapter;

    //private static final String urll = "https://dog.ceo/api/breed/shihtzu/images/random/10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_detail_screen);


        recyclerView = findViewById(R.id.recycler_view_breed_images);
        imageList = new ArrayList<>();
        Intent intent = getIntent();
        String breedName = intent.getExtras().getString("breed_name_list");

        String IMG_URL = "https://dog.ceo/api/breed/{breedName}/images/random/10";


        fetchBreedImages(IMG_URL);
        buildRecyclerView();

    }

    private void buildRecyclerView() {
        breedDetailAdapter = new BreedDetailAdapter(imageList, BreedDetailScreen.this, R.layout.specific_breed_pics);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(breedDetailAdapter);
    }


    private void fetchBreedImages(String urll) {


        RequestQueue queue = Volley.newRequestQueue(BreedDetailScreen.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urll, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "onResponse: successfully entered--------->>>>>>>>");

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("message");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        String imglinks = jsonArray.getString(i);
                        imageList.add(new BreedImages(imglinks, "success"));
                    }
                } catch (JsonSyntaxException | JSONException e) {
                    e.printStackTrace();
                }
                buildRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(BreedDetailScreen.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}


//        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
//
//        // Initialize a new ImageRequest
//        // Bitmap listener
//        ImageRequest imageRequest = new ImageRequest(
//                url,
//                response -> {
//
//                    imageList.add(response);
//
//                    // Save this downloaded bitmap to internal storage
//                    Uri uri = saveImageToInternalStorage(response);
//
//                    // Display the internal storage saved image to image view
//                },
//                0, // Image width
//                0, // Image height
//                ImageView.ScaleType.CENTER_CROP,
//                Bitmap.Config.RGB_565,
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // Do something with error response
//                        error.printStackTrace();
//                        Snackbar.make(rlayout, "Error", Snackbar.LENGTH_LONG).show();
//                    }
//                }
//        );
//
//        // Add ImageRequest to the RequestQueue
//        requestQueue.add(imageRequest);
//    }
//
//    protected Uri saveImageToInternalStorage(Bitmap bitmap) {
//        // Initialize ContextWrapper
//        ContextWrapper wrapper = new ContextWrapper(getApplicationContext());
//
//        // Initializing a new file
//        // The bellow line return a directory in internal storage
//        File file = wrapper.getDir("Images", MODE_PRIVATE);
//
//        // Create a file to save the image
//        file = new File(file, "UniqueFileName" + ".jpg");
//
//        try {
//            // Initialize a new OutputStream
//            OutputStream stream = null;
//
//            // If the output file exists, it can be replaced or appended to it
//            stream = new FileOutputStream(file);
//
//            // Compress the bitmap
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//
//            // Flushes the stream
//            stream.flush();
//
//            // Closes the stream
//            stream.close();
//
//        } catch (IOException e) // Catch the exception
//        {
//            e.printStackTrace();
//        }
//
//        // Parse the gallery image url to uri
//        Uri savedImageURI = Uri.parse(file.getAbsolutePath());
//
//        // Return the saved image Uri
//        return savedImageURI;
//    }

        //RequestQueue queue = Volley.newRequestQueue(BreedDetailScreen.this);
//        RequestQueue queue = Volley.newRequestQueue();
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//            },new Response.ErrorListener() {
//                return
//            }


