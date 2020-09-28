package com.example.mybabiesapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybabiesapp.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static com.example.mybabiesapp.View.MainActivity.currentId;

public class AnilarActivity extends AppCompatActivity {
    Button btnIptal, btnKaydet, btnOku;
    EditText txtAnilar;
    FileInputStream iStream;
    FileOutputStream oStream;
    TextView anilar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anilar);
        txtAnilar = findViewById(R.id.txt_anilar);
        btnIptal = findViewById(R.id.aniiptal);
        btnKaydet = findViewById(R.id.anikaydet);
        btnOku = findViewById(R.id.anioku);
        anilar = findViewById(R.id.anilar);
        final Intent intent = getIntent();
        final int id = currentId;

        btnIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (intent.hasExtra("id")) {
                    try {
                        oStream = openFileOutput("dosya.txt" + id, Context.MODE_APPEND);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    try {
                        oStream.write(txtAnilar.getText().toString().getBytes());
                        oStream.close();
                        Toast.makeText(AnilarActivity.this, "Başarıyla dosyaya yazıldı.", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        btnOku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int a = 65;
                    anilar.setText(Character.toString((char) a));
                    try {
                        iStream = openFileInput("dosya.txt" + id);
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
                    anilar.setText(okunanMetin);

                /*
                anilar.setText(intent.getStringExtra("id"));*/
            }
        });

    }
}