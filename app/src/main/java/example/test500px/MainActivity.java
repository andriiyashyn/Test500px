package example.test500px;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import static android.provider.Settings.System.AIRPLANE_MODE_ON;

public class MainActivity extends AppCompatActivity {

    Boolean thread_Boolean = false;
    ArrayList<Gallery_Image> images;
    GalleryAdapter galleryAdapter;
    String Api_URL = "https://api.500px.com/v1/photos?feature=popular&consumer_key=wB4ozJxTijCwNuggJvPGtBGCRqaZVcF6jsrzUadF";
    HttpURLConnection urlConnection = null;
    String JSONline = "";
    int i = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init recyclerView and items
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        images = new ArrayList<Gallery_Image>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        //Parsing JSON
        new ParseJSON().execute();


        galleryAdapter = new GalleryAdapter(this, images);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(galleryAdapter);

        //Init Button
        final Button more = (Button) findViewById(R.id.more);

        //Action for clicking button
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Api_URL = "https://api.500px.com/v1/photos?feature=popular&consumer_key=wB4ozJxTijCwNuggJvPGtBGCRqaZVcF6jsrzUadF&page=" + Integer.toString(i);
                new ParseJSON().execute();
                i++;
                more.setText("MORE");
            }
        });



    }

    private class ParseJSON extends AsyncTask<Void, Void, String> {

        private HttpURLConnection urlConnection = null;
        private String JSONline = "";


        //Get JSON String
        @Override
        protected String doInBackground(Void... params) {

            try {

                URL url
                        = new URL(Api_URL);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder stringBuffer = new StringBuilder();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }

                JSONline = stringBuffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("my_log", JSONline);

            return JSONline;
        }

        //Downloading items
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(JSONline);
            Log.d("my_log", JSONline);

            try {

                JSONObject page = new JSONObject(JSONline);
                JSONArray photos = page.getJSONArray("photos");



                for (int i = 0; i < photos.length(); i++)
                {
                    JSONObject photo = photos.getJSONObject(i);
                    Gallery_Image gallery_image = new Gallery_Image();


                    Log.d("my_log", photo.toString());

                    gallery_image.setImageView_url(photo.getString("image_url"));
                    gallery_image.setName(photo.getString("name"));
                    gallery_image.setCreated_at(photo.getString("created_at"));

                    Log.d("my_log", gallery_image.getName());
                    Log.d("my_log", "test");

                    images.add(gallery_image);
                }
                galleryAdapter.notifyDataSetChanged();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }



}
