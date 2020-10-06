package com.example.mybabiesapp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybabiesapp.Adapter.BabyAdapter;
import com.example.mybabiesapp.Model.Baby;
import com.example.mybabiesapp.R;
import com.example.mybabiesapp.ViewModels.BabyViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BabyViewModel babyViewModel;
    public static final int ADD_BABY_REQUEST = 1;
    public static final int EDIT_BABY_REQUEST = 2;
    public static int currentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton buttonAddBaby = findViewById(R.id.floatingActionButton);
        buttonAddBaby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBabyActivity.class);
                startActivityForResult(intent, ADD_BABY_REQUEST);
            }
        });

        FloatingActionButton buttonAddBaby2 = findViewById(R.id.floatingActionButton2);
        buttonAddBaby2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (babyViewModel.getAllBaby().getValue().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Bebek Yoktur", Toast.LENGTH_SHORT).show();
                } else {
                    babyViewModel.deleteAllBaby();
                    Toast.makeText(MainActivity.this, "Bebekler Silindi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BabyAdapter adapter = new BabyAdapter(this);
        recyclerView.setAdapter(adapter);

        babyViewModel = ViewModelProviders.of(this).get(BabyViewModel.class);
        babyViewModel.getAllBaby().observe(this, new Observer<List<Baby>>() {
            @Override
            public void onChanged(List<Baby> babies) {
                adapter.setBabies(babies);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                babyViewModel.delete(adapter.getBabyAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Bebek Silindi", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new BabyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Baby baby) {
                Intent intent2 = new Intent(MainActivity.this, BabyProfile.class);
                intent2.putExtra(AddEditActivity.EXTRA_ID, baby.getId());
                currentId = baby.getId();
                intent2.putExtra(AddEditActivity.EXTRA_AD, baby.getAd());
                intent2.putExtra(AddEditActivity.EXTRA_DOGUM_TARIHI, baby.getDogumTarihi());
                intent2.putExtra(AddEditActivity.EXTRA_CINSIYET, baby.getCinsiet());
                intent2.putExtra(AddEditActivity.EXTRA_GEBELIKYASI, baby.getGebelikYasi());
                intent2.putExtra(AddEditActivity.EXTRA_AGIRLIK, baby.getAgirlik());
                intent2.putExtra(AddEditActivity.EXTRA_UZUNLUK, baby.getUzunluk());
                intent2.putExtra(AddEditActivity.EXTRA_KAFAUZUNLUK, baby.getKafaUzunluk());
                intent2.putExtra(AddEditActivity.EXTRA_ILISKILER, baby.getIliskiler());
                intent2.putExtra(AddEditActivity.EXTRA_RESIM,baby.getResimUrl());
                startActivityForResult(intent2, EDIT_BABY_REQUEST);
            }

            @Override
            public void onEditClick(Baby baby) {
                Intent intent3 = new Intent(MainActivity.this, AddEditActivity.class);
                intent3.putExtra(AddEditActivity.EXTRA_ID, baby.getId());
                intent3.putExtra(AddEditActivity.EXTRA_AD, baby.getAd());
                intent3.putExtra(AddEditActivity.EXTRA_DOGUM_TARIHI, baby.getDogumTarihi());
                intent3.putExtra(AddEditActivity.EXTRA_CINSIYET, baby.getCinsiet());
                intent3.putExtra(AddEditActivity.EXTRA_GEBELIKYASI, baby.getGebelikYasi());
                intent3.putExtra(AddEditActivity.EXTRA_AGIRLIK, baby.getAgirlik());
                intent3.putExtra(AddEditActivity.EXTRA_UZUNLUK, baby.getUzunluk());
                intent3.putExtra(AddEditActivity.EXTRA_KAFAUZUNLUK, baby.getKafaUzunluk());
                intent3.putExtra(AddEditActivity.EXTRA_ILISKILER, baby.getIliskiler());
                startActivityForResult(intent3, EDIT_BABY_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_BABY_REQUEST && resultCode == RESULT_OK) {
            String ad = data.getStringExtra("ad");
            String dogumTarihi = data.getStringExtra("dogumTarihi");
            String cinsiyet = data.getStringExtra("cinsiyet");
            String gebelikYasi = data.getStringExtra("gebelikYasi");
            String agirlik = data.getStringExtra("agirlik");
            String uzunluk = data.getStringExtra("uzunluk");
            String kafaUzunluk = data.getStringExtra("kafaUzunluk");
            String iliskiler = data.getStringExtra("iliskiler");
            byte[] resimUr = data.getByteArrayExtra("resId");

            Baby baby = new Baby(ad, dogumTarihi, cinsiyet, gebelikYasi, agirlik, uzunluk, kafaUzunluk, iliskiler,resimUr);
            babyViewModel.insert(baby);

            Toast.makeText(this, "Bebek Kaydedildi", Toast.LENGTH_SHORT).show();

        }
        else if (requestCode == EDIT_BABY_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Bebek güncellenemedi", Toast.LENGTH_SHORT).show();
                return;
            }
            String ad = data.getStringExtra(AddEditActivity.EXTRA_AD);
            String dogumTarihi = data.getStringExtra(AddEditActivity.EXTRA_DOGUM_TARIHI);
            String cinsiyet = data.getStringExtra(AddEditActivity.EXTRA_CINSIYET);
            String gebelikYasi = data.getStringExtra(AddEditActivity.EXTRA_GEBELIKYASI);
            String agirlik = data.getStringExtra(AddEditActivity.EXTRA_AGIRLIK);
            String uzunluk = data.getStringExtra(AddEditActivity.EXTRA_UZUNLUK);
            String kafaUzunluk = data.getStringExtra(AddEditActivity.EXTRA_KAFAUZUNLUK);
            String iliskiler = data.getStringExtra(AddEditActivity.EXTRA_ILISKILER);
            byte[] resimUr = data.getByteArrayExtra("resId");

            Baby baby = new Baby(ad, dogumTarihi, cinsiyet, gebelikYasi, agirlik, uzunluk, kafaUzunluk, iliskiler,resimUr);
            baby.setId(id);
            babyViewModel.update(baby);

            Toast.makeText(this, "Bebek güncellendi", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bebek güncellenemedi", Toast.LENGTH_SHORT).show();
        }
    }
}