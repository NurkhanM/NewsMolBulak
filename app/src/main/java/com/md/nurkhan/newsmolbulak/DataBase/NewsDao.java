package com.md.nurkhan.newsmolbulak.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.md.nurkhan.newsmolbulak.Model.NewsModel;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM newsmodel")
    LiveData<List<NewsModel>> getAll();

    @Query("SELECT * FROM newsmodel WHERE id = :id")
    NewsModel getNews(int id);

    @Insert
    void insert(NewsModel newsModel);

    @Update
    void update(NewsModel newsModel);

    @Delete
    void delete(NewsModel newsModel);
}
