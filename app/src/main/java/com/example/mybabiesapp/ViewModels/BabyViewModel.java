package com.example.mybabiesapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mybabiesapp.Model.Baby;
import com.example.mybabiesapp.Model.RepositoryBaby;

import java.util.List;

public class BabyViewModel extends AndroidViewModel {
     RepositoryBaby repository;
     LiveData<List<Baby>> allBaby;

    public BabyViewModel(@NonNull Application application) {
        super(application);
        repository = new RepositoryBaby(application);
        allBaby = repository.getAllBaby();
    }

    public void insert(Baby baby){
        repository.insert(baby);
    }
    public void update(Baby baby){
        repository.update(baby);
    }
    public void delete(Baby baby){
        repository.delete(baby);
    }
    public void deleteAllBaby(){
        repository.deleteAllBaby();
    }
    public LiveData<List<Baby>> getAllBaby(){
        return allBaby;
    }
}
