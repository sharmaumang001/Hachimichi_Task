package com.sharmaumang.hachimichi_task;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.style.IconMarginSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<upload> mUploads;


    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public ImageView imageView;
        View mView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textViewName = mView.findViewById(R.id.text_view_name);
            imageView = mView.findViewById(R.id.image_view_upload);


        }
    }

    public ImageAdapter(Context context, List<upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        Glide.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }


}