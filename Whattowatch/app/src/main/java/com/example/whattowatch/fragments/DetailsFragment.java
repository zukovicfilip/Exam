package com.example.whattowatch.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.example.whattowatch.MainActivity;
import com.example.whattowatch.R;
import com.example.whattowatch.database.DataBaseHelper;
import com.example.whattowatch.model.Movie;
import com.example.whattowatch.model.Search;
import com.example.whattowatch.net.MyService;
import com.example.whattowatch.net.MyServiceContract;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.squareup.picasso.Picasso;
import java.sql.SQLException;
import java.sql.Time;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {

    private Search search;
    private Movie movie;
    private DataBaseHelper dataBaseHelper;

    private TimePicker timePicker;
    private EditText etCena;

    public DetailsFragment(Search search) {
        this.search = search;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        timePicker = getActivity().findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        etCena = getActivity().findViewById(R.id.etCena);

        getMovieDetails(search.getImdbID());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_detalji, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_dodaj) {
            movie.setCena(etCena.getText().toString());
            movie.setSatnica(formatirajSatnicu());
            try {
                getDataBaseHelper().getMovieDao().create(movie);
                prikaziObavestenje(movie.getTitle() + " je uspesno dodat na repertoar.");
            } catch (SQLException e) {
                Toast.makeText(getContext(), "greska", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMovieDetails(String id) {
        HashMap<String, String> query = new HashMap<>();
        query.put("apikey", MyServiceContract.USER_KEY);
        query.put("i", id);

        Call<Movie> call = MyService.apiInterface().getMovieDetails(query);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.code() == 200){
                    movie = response.body();
                    showDetails();
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showDetails() {
        ImageView ivThumbDetails = getActivity().findViewById(R.id.ivThumbDetails);
        TextView tvTitleDetails = getActivity().findViewById(R.id.tvTitleDetails);
        TextView tvYearDetails = getActivity().findViewById(R.id.tvYearDetails);
        TextView tvRuntimeDetails = getActivity().findViewById(R.id.tvRuntimeDetails);
        TextView tvPlotDetails = getActivity().findViewById(R.id.tvPlotDetails);
        TextView tvLanguageDetails = getActivity().findViewById(R.id.tvLanguageDetails);
        TextView tvGenre = getActivity().findViewById(R.id.tvGenre);


        Picasso.get().load(movie.getPoster()).resize(400,600).into(ivThumbDetails);
        tvTitleDetails.setText(movie.getTitle());
        tvYearDetails.setText(movie.getYear());
        tvRuntimeDetails.setText(movie.getRuntime());
        tvPlotDetails.setText(movie.getPlot());
        tvLanguageDetails.setText(movie.getLanguage());
        tvGenre.setText(movie.getGenre());
    }

    private void prikaziObavestenje(String poruka) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean toast = preferences.getBoolean(PrefsFragment.TOAST, false);
        boolean notifikacija = preferences.getBoolean(PrefsFragment.NOTIFIKACIJA, false);

        if(toast) {
            Toast.makeText(getContext(), poruka, Toast.LENGTH_SHORT).show();
        }

        if(notifikacija) {
            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder;
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                builder = new Notification.Builder(getContext(), MainActivity.OBAVESTENJE_CHANNEL_ID);
            else
                builder = new Notification.Builder(getContext());

            builder.setContentTitle("Notifikacija");
            builder.setContentText(poruka);
            builder.setSmallIcon(R.drawable.splash_image);
            notificationManager.notify(101, builder.build());
        }
    }

    private String formatirajSatnicu() {
        String satnica;
        if(timePicker.getHour() < 10)
            satnica = "0" + timePicker.getHour();
        else
            satnica = "" + timePicker.getHour();

        if(timePicker.getMinute() < 10)
            satnica = satnica + ":0" + timePicker.getMinute();
        else
            satnica = satnica + ":" + timePicker.getMinute();

        return satnica;

    }

    private DataBaseHelper getDataBaseHelper() {
        if (dataBaseHelper == null) {
            dataBaseHelper = OpenHelperManager.getHelper(getActivity(), DataBaseHelper.class);
        }
        return dataBaseHelper;
    }
}
