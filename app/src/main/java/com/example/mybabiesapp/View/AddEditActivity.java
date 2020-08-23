package com.example.mybabiesapp.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mybabiesapp.R;

public class AddEditActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.mybabiesapp.View.EXTRA_ID";
    public static final String EXTRA_AD = "com.example.mybabiesapp.View.EXTRA_AD";
    public static final String EXTRA_DOGUM_TARIHI = "com.example.mybabiesapp.View.EXTRA_DOGUM_TARIHI";
    public static final String EXTRA_CINSIYET = "com.example.mybabiesapp.View.EXTRA_CINSIYET";
    public static final String EXTRA_GEBELIKYASI = "com.example.mybabiesapp.View.EXTRA_GEBELIKYASI";
    public static final String EXTRA_AGIRLIK = "com.example.mybabiesapp.View.EXTRA_AGIRLIK";
    public static final String EXTRA_UZUNLUK = "com.example.mybabiesapp.View.EXTRA_UZUNLUK";
    public static final String EXTRA_KAFAUZUNLUK = "com.example.mybabiesapp.View.EXTRA_KAFAUZUNLUK";
    public static final String EXTRA_ILISKILER = "com.example.mybabiesapp.View.EXTRA_ILISKILER";
    public static final String EXTRA_RESIM = "com.example.mybabiesapp.View.EXTRA_RESIM";

    private EditText etAd, etdogumTarihi, etcinsiet, etgebelikYasi, etiliskiler;
    private EditText etagirlik;
    private EditText etuzunluk;
    private EditText etkafaUzunluk;
    private Button btniptal, btnkaydet;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        etAd = findViewById(R.id.txtad);
        etdogumTarihi = findViewById(R.id.txtdogumTarihi);
        etcinsiet = findViewById(R.id.cinsiet);
        etgebelikYasi = findViewById(R.id.gebelikYasi);
        etagirlik = findViewById(R.id.edit_agirlik);
        etuzunluk = findViewById(R.id.edit_uzunluk);
        etkafaUzunluk = findViewById(R.id.edit_kafaUzunluk);
        etiliskiler = findViewById(R.id.iliskiler);
        btniptal = findViewById(R.id.txtiptal2);
        btnkaydet = findViewById(R.id.txtdüzenle);

        btniptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            etAd.setText(intent.getStringExtra(EXTRA_AD));
            etdogumTarihi.setText(intent.getStringExtra(EXTRA_DOGUM_TARIHI));
            etcinsiet.setText(intent.getStringExtra(EXTRA_CINSIYET));
            etgebelikYasi.setText(intent.getStringExtra(EXTRA_GEBELIKYASI));
            etagirlik.setText(intent.getStringExtra(EXTRA_AGIRLIK));
            etuzunluk.setText(intent.getStringExtra(EXTRA_UZUNLUK));
            etkafaUzunluk.setText(intent.getStringExtra(EXTRA_KAFAUZUNLUK));
            etiliskiler.setText(intent.getStringExtra(EXTRA_ILISKILER));
        }

        btnkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
            private void saveNote() {
                String ad = etAd.getText().toString();
                String dogumTarihi = etdogumTarihi.getText().toString();
                String cinsiyet = etcinsiet.getText().toString();
                String gebelikYasi = etgebelikYasi.getText().toString();
                String agirlik = etagirlik.getText().toString();
                String uzunluk = etuzunluk.getText().toString();
                String kafaUzunluk = etkafaUzunluk.getText().toString();
                String iliskiler = etiliskiler.getText().toString();

                if (ad.trim().isEmpty() || cinsiyet.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Lütfen Adı ve Cinsiyeti giriniz", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent data = new Intent();
                data.putExtra(EXTRA_AD, ad);
                data.putExtra(EXTRA_DOGUM_TARIHI, dogumTarihi);
                data.putExtra(EXTRA_CINSIYET, cinsiyet);
                data.putExtra(EXTRA_GEBELIKYASI, gebelikYasi);
                data.putExtra(EXTRA_AGIRLIK, agirlik);
                data.putExtra(EXTRA_UZUNLUK, uzunluk);
                data.putExtra(EXTRA_KAFAUZUNLUK, kafaUzunluk);
                data.putExtra(EXTRA_ILISKILER, iliskiler);

                int id = getIntent().getIntExtra(EXTRA_ID, -1);
                if (id != -1) {
                    data.putExtra(EXTRA_ID, id);
                }
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}