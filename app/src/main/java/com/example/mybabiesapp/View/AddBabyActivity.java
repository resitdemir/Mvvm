package com.example.mybabiesapp.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mybabiesapp.Helper.DataConverter;
import com.example.mybabiesapp.Model.Baby;
import com.example.mybabiesapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddBabyActivity extends AppCompatActivity {
    private EditText etAd,etdogumTarihi,etcinsiet,etgebelikYasi,etiliskiler;
    private EditText etagirlik;
    private EditText etuzunluk;
    private EditText etkafaUzunluk;
    private Button btniptal,btnkaydet,btnPick;
    private ImageView imageView;
    Bitmap bmpImage;
    Uri imageData;
    private static final int GALLERY_REQUEST_CODE = 123;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_baby);

        init();
        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                        //permission denied
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission,PERMISSION_REQUEST_CODE);
                    }
                    else{
                        //permission already granted
                        pickImageFromGallery();
                    }
                }
                else{
                    pickImageFromGallery();
                }
            }
        });

        btniptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }

            private void saveNote() {
                String ad = etAd.getText().toString();
                String dogumTarihi = etdogumTarihi.getText().toString();
                String cinsiyet = etcinsiet.getText().toString();
                String gebelikYasi =etgebelikYasi.getText().toString();
                String agirlik =etagirlik.getText().toString();
                String uzunluk =etuzunluk.getText().toString();
                String kafaUzunluk =etkafaUzunluk.getText().toString();
                String iliskiler =etiliskiler.getText().toString();

                if(ad.trim().isEmpty() || cinsiyet.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Lütfen Adı ve Cinsiyeti giriniz",Toast.LENGTH_LONG).show();
                    return;
                }

                Intent data = new Intent();
                data.putExtra("ad",ad);
                data.putExtra("dogumTarihi",dogumTarihi);
                data.putExtra("cinsiyet",cinsiyet);
                data.putExtra("gebelikYasi",gebelikYasi);
                data.putExtra("agirlik",agirlik);
                data.putExtra("uzunluk",uzunluk);
                data.putExtra("kafaUzunluk",kafaUzunluk);
                data.putExtra("iliskiler",iliskiler);
                data.putExtra("resId", DataConverter.convertImage2ByteArray(bmpImage));
                setResult(RESULT_OK,data);
                finish();
            }
        });
    }

    private void pickImageFromGallery(){
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent2.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent2,GALLERY_REQUEST_CODE);
        }
        /*
        Galeriden de seçtirebilirdik....
        Intent intent2 = new Intent();
        intent2.setType("image/*");
        intent2.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent2,"resim sec"),GALLERY_REQUEST_CODE);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {

            bmpImage = (Bitmap) data.getExtras().get("data");
            if(bmpImage != null){
                imageView.setImageBitmap(bmpImage);
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:{
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }else {
                    Toast.makeText(this,"Permission denied...!",Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    public void init(){
        etAd = findViewById(R.id.txtad);
        etdogumTarihi = findViewById(R.id.txtdogumTarihi);
        etcinsiet = findViewById(R.id.cinsiet);
        etgebelikYasi = findViewById(R.id.gebelikYasi);
        btniptal = findViewById(R.id.txtiptal);
        btnkaydet = findViewById(R.id.txtkaydet);
        etagirlik =findViewById(R.id.agirlik);
        etuzunluk = findViewById(R.id.uzunluk);
        etkafaUzunluk = findViewById(R.id.kafaUzunluk);
        etiliskiler = findViewById(R.id.iliskiler);
        imageView = findViewById(R.id.camera);
        btnPick = findViewById(R.id.pick_image);
        bmpImage = null;
    }
}