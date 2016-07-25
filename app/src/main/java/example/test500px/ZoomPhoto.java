package example.test500px;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by andrew_yashin on 7/23/16.
 */
public class ZoomPhoto extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zoomphoto);


        ImageView zoom_photo = (ImageView) findViewById(R.id.zoom_photo);

        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String url = intent.getStringExtra("URL");
        String created_at = intent.getStringExtra("CREATED_AT");
        String position = intent.getStringExtra("Position");
        String total = intent.getStringExtra("Total");



        //Log.d("my_log", "Parcelable " + image.getName() + "  " + image.getCreated_at() + "  " + image.getImageView_url());
        Picasso.with(this).load(url).into(zoom_photo);
        TextView name_photo = (TextView) findViewById(R.id.name_photo);
        TextView date_photo = (TextView) findViewById(R.id.date_photo);
        TextView number = (TextView) findViewById(R.id.number);


        number.setText(position + " / " + total);
        name_photo.setText(name);
        date_photo.setText(created_at);



    }
}
