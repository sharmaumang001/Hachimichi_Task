package com.sharmaumang.hachimichi_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class ImageView extends AppCompatActivity {


    RecyclerView mRecyclerView;

    ImageAdapter mImageAdapter;

    Button mUploadNew;



    StorageReference mFirebaseStorage;
    DatabaseReference mDatabaseReference;


    public ArrayList<upload> mImageUrls;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        mUploadNew = findViewById(R.id.upload_new);




        mRecyclerView = findViewById(R.id.recycler_ID);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Button working

        mUploadNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ImageView.this,ImageUpload.class);
                startActivity(mIntent);
            }
        });


                                          //Auto Recycler view scroll

//
//        final int speedScroll = 1200;
//        final Handler handler = new Handler();
//
//        Runnable runnable = new Runnable() {
//
//            ImageAdapter mImageAdapter = new ImageAdapter(ImageView.this,mImageUrls);
//            int count = 0;
//            boolean flag = true;
//
//            @Override
//            public void run() {
//
//                if (count < mImageAdapter.getItemCount()) {
//
//                    if (count == (mImageAdapter.getItemCount() - 1)) {
//                        flag = false;
//                    }else if(count == 0) {
//                        flag = true;
//                    }
//
//                    if (flag) count++;
//                    else count--;
//
//                    mRecyclerView.smoothScrollToPosition(count);
//                    handler.postDelayed(this, speedScroll);
//                }
//            }
//        };
//
//        handler.postDelayed(runnable,speedScroll);

        mImageUrls= new ArrayList<>();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Images/");
        mFirebaseStorage = FirebaseStorage.getInstance().getReference("Images/");


        mDatabaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mImageUrls.clear();

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {



                    upload upload = postSnapshot.getValue(upload.class);
                    mImageUrls.add(upload);

                    String Url = upload.getImageUrl();
                    String name = upload.getName();

                }

                mImageAdapter = new ImageAdapter(ImageView.this,mImageUrls);
                mRecyclerView.setAdapter(mImageAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ImageView.this,error.getMessage(),Toast.LENGTH_SHORT);

            }
        });

    }
}