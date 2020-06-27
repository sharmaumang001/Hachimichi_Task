package com.sharmaumang.hachimichi_task;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ImageAdapter extends PagerAdapter {
    Context mContext;

    ImageAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((android.widget.ImageView) object);
    }

    private int[] sliderImageId = new int[]{

//       R.drawable.image1, R.drawable.image2, R.drawable.image3

    };

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        android.widget.ImageView imageView = new android.widget.ImageView(mContext);
        imageView.setScaleType(android.widget.ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(sliderImageId[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((ImageView) object);
    }

    @Override
    public int getCount() {
        return sliderImageId.length;
    }
}
