package example.test500px;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

/**
 * Created by andrew_yashin on 7/23/16.
 */
public class Gallery_Image{
    private String imageView_url;
    private String name;
    private String created_at;

    public Gallery_Image(){}

    public Gallery_Image(String imageView_url, String name, String created_at) {
        this.imageView_url = imageView_url;
        this.name = name;
        this.created_at = created_at;
    }

    public String getImageView_url() {
        return imageView_url;
    }

    public void setImageView_url(String imageView_url) {
        this.imageView_url = imageView_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}

