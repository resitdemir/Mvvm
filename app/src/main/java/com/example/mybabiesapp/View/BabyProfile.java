package com.example.mybabiesapp.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mybabiesapp.Helper.DataConverter;
import com.example.mybabiesapp.Model.Baby;
import com.example.mybabiesapp.R;

import static com.example.mybabiesapp.View.AddEditActivity.EXTRA_AD;
import static com.example.mybabiesapp.View.AddEditActivity.EXTRA_AGIRLIK;
import static com.example.mybabiesapp.View.AddEditActivity.EXTRA_ID;
import static com.example.mybabiesapp.View.AddEditActivity.EXTRA_KAFAUZUNLUK;
import static com.example.mybabiesapp.View.AddEditActivity.EXTRA_RESIM;
import static com.example.mybabiesapp.View.AddEditActivity.EXTRA_UZUNLUK;

public class BabyProfile extends AppCompatActivity {
    TextView txtName;
    ImageView imageView;
    ImageButton diary,istatislik,anilar,rehber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby_profile);

        txtName = findViewById(R.id.baby_name);
        imageView = findViewById(R.id.profile_image);
        diary = findViewById(R.id.btn_diary);
        istatislik = findViewById(R.id.btn_istatislik);
        anilar = findViewById(R.id.btn_ani);
        rehber = findViewById(R.id.btn_rehber);

        final Intent intent = getIntent();
        byte[] resimUr = intent.getByteArrayExtra(EXTRA_RESIM);
        final String id = intent.getStringExtra(EXTRA_ID);
        final String ad = intent.getStringExtra(EXTRA_AD);
        final String agirlik = intent.getStringExtra(EXTRA_AGIRLIK);
        final String uzunluk = intent.getStringExtra(EXTRA_UZUNLUK);
        final String kafaUzunluk = intent.getStringExtra(EXTRA_KAFAUZUNLUK);
        txtName.setText(ad);
        imageView.setImageBitmap(DataConverter.converByteArray2Image(resimUr));

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),DiaryActivity.class);
                startActivity(intent1);
            }
        });

        istatislik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(),IstatislikActivity.class);
                intent2.putExtra("ad",ad);
                intent2.putExtra("agirlik",agirlik);
                intent2.putExtra("uzunluk",uzunluk);
                intent2.putExtra("kafauzunluk",kafaUzunluk);
                startActivity(intent2);
            }
        });
        anilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(),AnilarActivity.class);
                startActivity(intent3);
            }
        });
        rehber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(),KlavuzActivity.class);
                startActivity(intent4);
            }
        });
    }

}