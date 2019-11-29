package com.example.whattowatch.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Movie.TABLE_NAME)
public class Movie {

    public static final String TABLE_NAME = "movies";

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_TITLE = "title";
    public static final String FIELD_NAME_POSTER = "poster";
    public static final String FIELD_NAME_CENA = "cena";
    public static final String FIELD_NAME_SATNICA = "satnica";

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    public int id;

    @DatabaseField(columnName = FIELD_NAME_POSTER)
    @SerializedName("Poster")
    @Expose
    public String poster;

    @DatabaseField(columnName = FIELD_NAME_TITLE)
    @SerializedName("Title")
    @Expose
    public String title;

    @DatabaseField(columnName = FIELD_NAME_CENA)
    public String cena;

    @DatabaseField(columnName = FIELD_NAME_SATNICA)
    public String satnica;

    @SerializedName("Year")
    @Expose
    public String year;
    @SerializedName("Runtime")
    @Expose
    public String runtime;
    @SerializedName("Genre")
    @Expose
    public String genre;
    @SerializedName("Plot")
    @Expose
    public String plot;
    @SerializedName("Language")
    @Expose
    public String language;

    public int getId() {
        return id;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getSatnica() {
        return satnica;
    }

    public void setSatnica(String satnica) {
        this.satnica = satnica;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}