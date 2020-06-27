package com.sharmaumang.hachimichi_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ImageView extends AppCompatActivity {

    TextView mImageName;
    ViewPager mViewPager;

    ImageAdapter mImageAdapter;
    DatabaseReference mDatabaseReference;
    StorageReference mStorageReference;

    public ArrayList<upload> mImageurls = new ArrayList<upload>();
    public int position=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        mImageName = findViewById(R.id.image_name);
        mViewPager = (ViewPager) findViewById(R.id.viewPage);



        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Images");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    upload upload = postSnapshot.getValue(upload.class);
                    mImageurls.add(upload);
                }
                ImageAdapter mAdapterView = new ImageAdapter(ImageView.this,mImageurls);
                mViewPager.setAdapter(mAdapterView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ImageView.this,error.getMessage(),Toast.LENGTH_SHORT);

            }
        });





    }
}