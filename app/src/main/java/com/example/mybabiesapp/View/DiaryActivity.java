package com.example.mybabiesapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybabiesapp.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.mybabiesapp.View.MainActivity.currentId;

public class DiaryActivity extends AppCompatActivity {
    Button bt1, bt2, bt3, kaydet,oku,iptal;
    LinearLayout background;
    FileInputStream iStream;
    FileOutputStream oStream;
    EditText txtDiary;
    TextView txtani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        bt1 = findViewById(R.id.bg1);
        bt2 = findViewById(R.id.bg2);
        bt3 = findViewById(R.id.bg3);
        background = findViewById(R.id.back);
        iptal = findViewById(R.id.diaryiptal);
        txtDiary = findViewById(R.id.et_diary);
        kaydet = findViewById(R.id.diarykaydet);
        final Intent intent = getIntent();
        final int id = currentId;
        txtani = findViewById(R.id.diaries);
        oku = findViewById(R.id.diaryoku);

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

        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.hasExtra("id")) {
                    try {
                        oStream = openFileOutput("dosyam.txt" + id, Context.MODE_APPEND);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    try {
                        oStream.write(txtDiary.getText().toString().getBytes());
                        oStream.close();
                        Toast.makeText(DiaryActivity.this, "Başarıyla dosyaya yazıldı.", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        iptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        oku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = 65;
                txtani.setText(Character.toString((char) a));
                try {
                    iStream = openFileInput("dosyam.txt" + id);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                int x;
                String okunanMetin = " ";
                try {
                    while ((x = iStream.read()) != -1) {
                        okunanMetin += Character.toString((char) x);
                    }
                    iStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                txtani.setText(okunanMetin);

                /*
                anilar.setText(intent.getStringExtra("id"));*/
            }
        });



    }
}
