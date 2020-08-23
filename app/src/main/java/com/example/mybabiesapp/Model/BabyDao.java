package com.example.mybabiesapp.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BabyDao {
    @Insert
    void insert(Baby baby);

    @Update
    void update(Baby baby);

    @Delete
    void delete(Baby baby);

    @Query("Delete from baby_table")
    void deleteAllBaby();

    @Query("select * from baby_table order by id desc")
    LiveData<List<Baby>> getAllBaby();
}
