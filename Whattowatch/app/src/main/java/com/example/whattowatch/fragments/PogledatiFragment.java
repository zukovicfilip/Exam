package com.example.whattowatch.fragments;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.example.whattowatch.MainActivity;
import com.example.whattowatch.R;
import com.example.whattowatch.adapter.CustomAdapter;
import com.example.whattowatch.adapter.RepertoarAdapter;
import com.example.whattowatch.database.DataBaseHelper;
import com.example.whattowatch.model.Movie;
import com.example.whattowatch.model.Search;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

public class PogledatiFragment extends Fragment {

    private ListView lvLista;
    private DataBaseHelper dataBaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_pogledati, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            lvLista = getActivity().findViewById(R.id.lvListaPogledati);
            final List<Movie> repertoar = getDataBaseHelper().getMovieDao().queryForAll();
            RepertoarAdapter adapter = new RepertoarAdapter(getActivity(), repertoar);
            lvLista.setAdapter(adapter);

            lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Movie film = repertoar.get(position);
                    prikaziObavestenje("Projekcija u " + film.getSatnica() + " za " + film.getTitle() + " je uspesno rezervisana.");

                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            notificationManager.notify(202, builder.build());
        }
    }

    private DataBaseHelper getDataBaseHelper() {
        if (dataBaseHelper == null) {
            dataBaseHelper = OpenHelperManager.getHelper(getActivity(), DataBaseHelper.class);
        }
        return dataBaseHelper;
    }
}
