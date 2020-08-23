package com.example.mybabiesapp.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mybabiesapp.R;

@Database(entities = {Baby.class}, version = 1,exportSchema = false)
public abstract class BabyDatabase extends RoomDatabase {

    public abstract BabyDao babyDao();
    public static volatile BabyDatabase INSTANCE;

    public static synchronized BabyDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,BabyDatabase.class,"baby_database")
                    .fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private BabyDao babyDao;

        private PopulateDbAsyncTask(BabyDatabase db){
            babyDao = db.babyDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            /*babyDao.insert(new Baby("Test 1","01.01.1998","boy","20 weeks","20","20","20","anne", R.drawable.harry_potter_felsefe));
            babyDao.insert(new Baby("Test 2","01.01.1997","girl","20 weeks","20","20","20","anne",R.drawable.harry_potter_felsefe));*/
            return null;
        }
    }
}
