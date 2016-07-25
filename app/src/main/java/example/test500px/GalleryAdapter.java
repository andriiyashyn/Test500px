package example.test500px;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by andrew_yashin on 7/23/16.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImageViewHolder>{
    List<Gallery_Image> ImageList;
    Context context;
    int position;

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView image_item;
        String name;
        String created_at;


        public ImageViewHolder(final View view)
        {
            super(view);
            image_item = (ImageView) view.findViewById(R.id.image_item);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Gallery_Image photo = ImageList.get(position);

                    Log.d("my_log", photo.getName() + "  " + photo.getImageView_url() + "  " + photo.getCreated_at());

                    Intent intent = new Intent(v.getContext(), ZoomPhoto.class);
                    intent.putExtra("URL", photo.getImageView_url());
                    intent.putExtra("NAME", photo.getName());
                    intent.putExtra("CREATED_AT", photo.getCreated_at());
                    intent.putExtra("Position", Integer.toString(position+1));
                    intent.putExtra("Total", Integer.toString(ImageList.size()));
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    public GalleryAdapter(Context context, List<Gallery_Image> imageList){
        this.context = context;
        this.ImageList = imageList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Gallery_Image gallery_image = ImageList.get(position);
        this.position = position;

        Picasso.with(context)
                .load(gallery_image.getImageView_url())
                .into(holder.image_item);




        //Glide.with(context).load("http://goo.gl/gEgYUd")..into(holder.image_item);
    }

    @Override
    public int getItemCount() {
        return ImageList.size();
    }
}
