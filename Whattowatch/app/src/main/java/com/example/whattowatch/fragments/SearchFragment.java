package com.example.whattowatch.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.whattowatch.adapter.CustomAdapter;
import com.example.whattowatch.R;
import com.example.whattowatch.net.MyService;
import com.example.whattowatch.net.MyServiceContract;
import com.example.whattowatch.model.Result;
import com.example.whattowatch.model.Search;
import android.widget.Button;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText etMovieTitle = getActivity().findViewById(R.id.etMovieTitle);

        Button search = getActivity().findViewById(R.id.bSearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMoviesByTitle(etMovieTitle.getText().toString().trim());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_search, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if(item.getItemId() == R.id.action_repertoar) {
            PogledatiFragment pogledatiFragment = new PogledatiFragment();
            transaction.replace(R.id.root, pogledatiFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(item.getItemId() == R.id.action_prefs) {
            PrefsFragment prefsFragment = new PrefsFragment();
            transaction.replace(R.id.root, prefsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMoviesByTitle(String title) {
        HashMap<String, String> query = new HashMap<>();
        query.put("apikey", MyServiceContract.USER_KEY);
        query.put("s", title);

        Call<Result> call = MyService.apiInterface().getMovieByTitle(query);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.code() == 200){
                    showResults(response.body().getSearch());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showResults(final List<Search> results) {
        ListView lvLista = getActivity().findViewById(R.id.lvLista);
        CustomAdapter adapter = new CustomAdapter(getActivity(), results);
        lvLista.setAdapter(adapter);
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                DetailsFragment detailsFragment = new DetailsFragment(results.get(position));
                transaction.replace(R.id.root, detailsFragment);
                transaction.commit();
            }
        });
    }
}
