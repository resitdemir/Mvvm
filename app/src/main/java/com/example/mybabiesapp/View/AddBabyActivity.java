package com.example.mybabiesapp.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybabiesapp.Helper.DataConverter;
import com.example.mybabiesapp.Model.Baby;
import com.example.mybabiesapp.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Array;
import java.util.Calendar;

public class AddBabyActivity extends AppCompatActivity {
    private EditText etAd;
    private String etdogumTarihi;
    private String etcinsiet;
    private EditText etgebelikYasi;
    private EditText etiliskiler;
    private EditText etagirlik;
    private EditText etuzunluk;
    private EditText etkafaUzunluk;
    private Button btniptal,btnkaydet,btnPick,btnSelectDate,btn_result;
    private ImageView imageView;
    Bitmap bmpImage;
    RadioGroup radioGroup;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final int GALLERY_REQUEST_CODE = 123;
    private static final int PERMISSION_REQUEST_CODE = 200;
    public static byte[] re;
    public static String dg;

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
                String dogumTarihi = etdogumTarihi;
                String cinsiyet = etcinsiet;
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
                re = DataConverter.convertImage2ByteArray(bmpImage);
                data.putExtra("resId", DataConverter.convertImage2ByteArray(bmpImage));
                setResult(RESULT_OK,data);
                finish();
            }
        });

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddBabyActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                String date = month + "/" + dayOfMonth + "/" +year;
                etdogumTarihi = date;
                dg = date;
                mDisplayDate.setText(date);
            }
        };

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton){
                    Toast.makeText(getApplicationContext(),"erkek" , Toast.LENGTH_SHORT).show();
                    etcinsiet = "Erkek";
                }
                else {
                    Toast.makeText(getApplicationContext(),"kız" , Toast.LENGTH_SHORT).show();
                    etcinsiet = "Kız";
                }
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
        btnSelectDate = findViewById(R.id.selectDate);
        mDisplayDate = findViewById(R.id.dogumTarihi);
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
        radioGroup = findViewById(R.id.radioGroup);
    }
}