package com.example.mybabiesapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mybabiesapp.R;

public class DiaryActivity extends AppCompatActivity {
    Button bt1, bt2, bt3, kaydet;
    LinearLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        bt1 = findViewById(R.id.bg1);
        bt2 = findViewById(R.id.bg2);
        bt3 = findViewById(R.id.bg3);
        background = findViewById(R.id.back);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.setBackgroundResource(R.drawable.diary1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.setBackgroundResource(R.drawable.diary2);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.setBackgroundResource(R.drawable.diary3);
            }
        });

        /*kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }
}
