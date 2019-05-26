package com.md.nurkhan.newsmolbulak.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.md.nurkhan.newsmolbulak.Model.NewsModel;

@Database(entities = {NewsModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NewsDao newsDao();

}
