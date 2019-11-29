package com.example.whattowatch.net;
import com.example.whattowatch.model.Movie;
import com.example.whattowatch.model.Result;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Klasa koja opisuje koji tj mapira putanju servisa
 * opisuje koji metod koristimo ali i sta ocekujemo kao rezultat
 *
 * */
public interface MyApiEndpointInterface {

    @GET("/")
    Call<Result> getMovieByTitle(@QueryMap Map<String, String> options);

    @GET("/")
    Call<Movie> getMovieDetails(@QueryMap Map<String, String> options);
}
