package com.example.mybabiesapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mybabiesapp.R;

import static com.example.mybabiesapp.View.AddEditActivity.EXTRA_AGIRLIK;

public class IstatislikActivity extends AppCompatActivity {
    TextView txtName1, txtName2, txtName3;
    TextView txtagirlik, txtuzunluk, txtkafaUzunluk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istatislik);
        txtName1 = findViewById(R.id.txt_names);
        txtName2 = findViewById(R.id.txt_names2);
        txtName3 = findViewById(R.id.txt_names3);
        txtagirlik = findViewById(R.id.ist_agirlik);
        txtuzunluk = findViewById(R.id.ist_uzunluk);
        txtkafaUzunluk = findViewById(R.id.ist_kafauzunluk);

        Intent intent = getIntent();
        txtName1.setText(intent.getStringExtra("ad"));
        txtName2.setText(intent.getStringExtra("ad"));
        txtName3.setText(intent.getStringExtra("ad"));
        txtagirlik.setText(intent.getStringExtra("agirlik"));
        txtuzunluk.setText(intent.getStringExtra("uzunluk"));
        txtkafaUzunluk.setText(intent.getStringExtra("kafauzunluk"));

    }
}