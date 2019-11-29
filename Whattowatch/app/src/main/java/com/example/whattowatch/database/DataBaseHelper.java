package com.example.whattowatch.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.whattowatch.model.Movie;
import com.example.whattowatch.model.Search;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {


    private static final String DATABASE_NAME = "ormlite.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Movie, Integer> movieDao = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Movie.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Movie.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<Movie, Integer> getMovieDao() throws SQLException {
        if(movieDao == null) {
            movieDao = getDao(Movie.class);
        }

        return movieDao;
    }
}
