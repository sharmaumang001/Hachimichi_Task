package com.sharmaumang.hachimichi_task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

public class ImageView extends AppCompatActivity {

    TextView mImageName;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        mImageName = findViewById(R.id.image_name);
        mViewPager = (ViewPager) findViewById(R.id.viewPage);

        ImageAdapter mAdapterView = new ImageAdapter(this);
        mViewPager.setAdapter(mAdapterView);





    }
}