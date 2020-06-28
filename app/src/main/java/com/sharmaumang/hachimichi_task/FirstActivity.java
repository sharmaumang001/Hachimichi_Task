package com.sharmaumang.hachimichi_task;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    Button taskOneBtn,taskTwoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        taskTwoBtn = findViewById(R.id.taskTwoBtn);
        taskOneBtn = findViewById(R.id.taskOneBtn);


        taskOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FirstActivity.this,"Task A",Toast.LENGTH_SHORT).show();

                Intent mIntent = new Intent(FirstActivity.this,ImageView.class);
                startActivity(mIntent);


            }
        });

        taskTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FirstActivity.this,"Task B",Toast.LENGTH_SHORT).show();

                Intent mIntent = new Intent(FirstActivity.this,SensorActivity.class);
                startActivity(mIntent);


            }
        });


    }

}