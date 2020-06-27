package com.sharmaumang.hachimichi_task;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    Context mContext;
    private ArrayList<upload> mImageUrls;


    ImageAdapter(Context context,ArrayList<upload> imageUrls) {
        this.mContext = context;
        this.mImageUrls =imageUrls;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

       ImageView imageView = new ImageView(mContext);
        Picasso.get()
                .load(String.valueOf(mImageUrls.get(position)))
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return mImageUrls.size();
    }
}
