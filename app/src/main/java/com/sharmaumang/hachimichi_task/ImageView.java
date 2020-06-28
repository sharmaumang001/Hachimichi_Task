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

    ViewFlipper mViewFlipper;
    ImageView mImageView1, mImageView2, mImageView3;
    TextView mTextView1, mTextView2, mTextView3;
    Button mUploadNew;


    StorageReference mFirebaseStorage;
    DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        mUploadNew = findViewById(R.id.upload_new);
        mViewFlipper =findViewById(R.id.viewFlipper_ID);


//        mImageView1 = (ImageView) findViewById(R.id.image_view1);


        mTextView1 =findViewById(R.id.textView1);
        mTextView2 =findViewById(R.id.textView2);
        mTextView3 = findViewById(R.id.textView3);

        mImageView1 = (ImageView) findViewById(R.id.image_view1);
        mImageView2 = (ImageView) findViewById(R.id.image_view2);
        mImageView3 = (ImageView) findViewById(R.id.image_view3);




        mUploadNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(ImageView.this,ImageUpload.class);
                startActivity(mIntent);
            }
        });


        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Images/");
        mFirebaseStorage = FirebaseStorage.getInstance().getReference("Images/");


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    upload upload = postSnapshot.getValue(upload.class);
                    String Url = upload.getImageUrl();
                    String name = upload.getName();

                    Picasso.get().load(Url).into((Target) mImageView1);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(ImageView.this,error.getMessage(),Toast.LENGTH_SHORT);

            }
        });

    }
}