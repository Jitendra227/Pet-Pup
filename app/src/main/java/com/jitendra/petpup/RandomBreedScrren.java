package com.jitendra.petpup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.jitendra.petpup.Adapter.RandomScreenAdapter;
import com.jitendra.petpup.model.data.PupImages;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class RandomBreedScrren extends AppCompatActivity {
    private static final String TAG = "RandomBreedScrren";

    private ArrayList<PupImages> imageLists;
    private RecyclerView recyclerView;
    private RandomScreenAdapter randomScreenAdapter;

    private static final String IMAGE_URL = "https://dog.ceo/api/breeds/image/random/10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_breed_scrren);

        recyclerView = findViewById(R.id.recycler_view_random);

        imageLists = new ArrayList<>();

        //buildRecyclerView();

        //       GetData getData = new GetData();
        //       getData.execute();
        fetchRandomImages();
        // getAllImages();
    }

    private void buildRecyclerView() {
        randomScreenAdapter = new RandomScreenAdapter(RandomBreedScrren.this, imageLists, R.layout.pup_list);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(randomScreenAdapter);
    }


//    public class GetData extends AsyncTask<String, String, String> {
//        @Override
//        protected String doInBackground(String... strings) {
//            String current = "";
//            try {
//                URL url;
//                HttpURLConnection urlConnection = null;
//                try {
//                    url = new URL(IMAGE_URL);
//                    urlConnection = (HttpURLConnection) url.openConnection();
//
//                    InputStream is = urlConnection.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(is);
//
//                    int data = isr.read();
//                    while (data != -1) {
//                        current += (char) data;
//                        data = isr.read();
//                    }
//                    return current;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    if (urlConnection != null)
//                        urlConnection.disconnect();
//                }
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//
//            return current;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            try {
//                JSONObject jsonObject = new JSONObject(s);
//                JSONArray jsonArray = jsonObject.getJSONArray("message");
//
//                for (int i=0;i <10; i++) {
//                    JSONObject obj = jsonArray.getJSONObject(i);
//
//                    String imglinks = jsonArray.getString(i);
//                    imageLists.add(new PupImages(imglinks,"success"));
//
//                    Log.d(TAG, "onPostExecute: check----->.>>>>>"+imglinks);
//                }
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            buildRecyclerView();
//        }
//    }
//
//}

    private void fetchRandomImages() {
        RequestQueue queue = Volley.newRequestQueue(RandomBreedScrren.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, IMAGE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("message");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        //

                        String imglinks = jsonArray.getString(i);
                        Log.d(TAG, "onResponse: <<<<<<<link>>>>>>"+imglinks);
                        imageLists.add(new PupImages(imglinks, "success"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                buildRecyclerView();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RandomBreedScrren.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}


//    private void getAllImages() {
//        RequestQueue queue = Volley.newRequestQueue(RandomBreedScrren.this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, IMAGE_URL, null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, "onResponse: successfully entered--------->>>>>>>>");
//                        recyclerView.setVisibility(View.VISIBLE);
//                        for (int i = 0; i < 10; i++) {
//                            try {
//                                JSONObject responseObj = response.getJSONObject(i);
//
//                                String imageName = response.optString(0);
//
//                                JSONArray array = response.optJSONArray(0);
//
//                                ArrayList<PupImages> img = new ArrayList<>();
//
//                                String courseImageURL = responseObj.getString("imageUrl");
//                                imageLists.add(new PupImages(courseImageURL));
//                                buildRecyclerView();
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "onErrorResponse: "+ error.getMessage());
//                Toast.makeText(RandomBreedScrren.this, "something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//        queue.add(jsonArrayRequest);
//    }


//    private void extractJSON(){
//        try {
//            JSONObject jsonObject = new JSONObject(imagesJSON);
//            arrayImages = jsonObject.getJSONArray(JSON_ARRAY);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void showImage(){
//        try {
//            JSONObject jsonObject = arrayImages.getJSONObject(TRACK);
//            getImage(jsonObject.getString(IMAGE_URL));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void getAllImages() {
//        class GetAllImages extends AsyncTask<String,Void,String>{
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                imagesJSON = s;
//                extractJSON();
//                showImage();
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                String uri = params[0];
//                BufferedReader bufferedReader = null;
//                try {
//                    URL url = new URL(uri);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String json;
//                    while((json = bufferedReader.readLine())!= null){
//                        sb.append(json+"\n");
//                    }
//
//                    return sb.toString().trim();
//
//                }catch(Exception e){
//                    return null;
//                }
//            }
//        }
//        GetAllImages gai = new GetAllImages();
//        gai.execute(IMAGE_URL);
//    }
//
//    private void getImage(String urlToImage){
//        class GetImage extends AsyncTask<String,Void,Bitmap> {
//            @Override
//            protected Bitmap doInBackground(String... params) {
//                URL url = null;
//                Bitmap image = null;
//
//                String urlToImage = params[0];
//                try {
//                    url = new URL(urlToImage);
//                    image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return image;
//            }
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                super.onPostExecute(bitmap);
//                pupItems.add(new PupItems(bitmap.toString(),"success"));
//
//            }
//        }
//        GetImage gi = new GetImage();
//        gi.execute(urlToImage);
//    }
