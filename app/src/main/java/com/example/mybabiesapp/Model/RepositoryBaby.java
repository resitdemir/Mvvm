package com.example.mybabiesapp.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.mybabiesapp.Model.Baby;
import com.example.mybabiesapp.Model.BabyDao;
import com.example.mybabiesapp.Model.BabyDatabase;

import java.util.List;

public class RepositoryBaby {
    private BabyDao babyDao;
    private LiveData<List<Baby>> allBaby;

    public RepositoryBaby(Application application){
        BabyDatabase database = BabyDatabase.getInstance(application);
        babyDao = database.babyDao();
        allBaby = babyDao.getAllBaby();
    }

    public void insert(Baby baby){
        new InsertBabyAsyncTask(babyDao).execute(baby);
    }
    public void update(Baby baby){
        new UpdateBabyAsyncTask(babyDao).execute(baby);
    }
    public void delete(Baby baby){
        new DeleteBabyAsyncTask(babyDao).execute(baby);
    }
    public void deleteAllBaby(){
        new DeleteAllBabyAsyncTask(babyDao).execute();
    }

    public LiveData<List<Baby>> getAllBaby(){
        return allBaby;
    }

    private static class InsertBabyAsyncTask extends AsyncTask<Baby,Void,Void>{
        private BabyDao babyDao;
        private InsertBabyAsyncTask(BabyDao babyDao){
            this.babyDao = babyDao;
        }
        @Override
        protected Void doInBackground(Baby... babies) {
            babyDao.insert(babies[0]);
            return null;
        }
    }

    private static class UpdateBabyAsyncTask extends AsyncTask<Baby,Void,Void>{
        private BabyDao babyDao;

        private UpdateBabyAsyncTask(BabyDao babyDao){
            this.babyDao = babyDao;
        }

        @Override
        protected Void doInBackground(Baby... babies) {
            babyDao.update(babies[0]);
            return null;
        }
    }

    private static class DeleteBabyAsyncTask extends AsyncTask<Baby,Void,Void>{
        private BabyDao babyDao;

        private DeleteBabyAsyncTask(BabyDao babyDao){
            this.babyDao = babyDao;
        }

        @Override
        protected Void doInBackground(Baby... babies) {
            babyDao.delete(babies[0]);
            return null;
        }
    }

    private static class DeleteAllBabyAsyncTask extends AsyncTask<Void,Void,Void>{
        private BabyDao babyDao;

        private DeleteAllBabyAsyncTask(BabyDao babyDao){
            this.babyDao = babyDao;
        }

        @Override
        protected Void doInBackground(Void...voids) {
            babyDao.deleteAllBaby();
            return null;
        }
    }
}

