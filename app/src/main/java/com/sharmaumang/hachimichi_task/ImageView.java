package com.sharmaumang.hachimichi_task;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ImageView extends AppCompatActivity {

    Button mUploadNew;
    RecyclerView mRecyclerView;

    ImageAdapter mImageAdapter;
    StorageReference mFirebaseStorage;
    DatabaseReference mDatabaseReference;
//    int count = 0;
//    boolean flag = true;
//
//    public int speedScroll = 150;
//    public Handler handler;


    public ArrayList<upload> mImageUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        mUploadNew = findViewById(R.id.upload_new);
        mRecyclerView = findViewById(R.id.recycler_ID);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Auto Recycler
//        handler = new Handler();
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                if (count < mImageAdapter.getItemCount()) {
//                    if ((){
//                        flag = false;
//                    }
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
//

        mUploadNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ImageView.this,ImageUpload.class);
                startActivity(mIntent);
            }
        });

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