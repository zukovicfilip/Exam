package com.example.whattowatch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.whattowatch.fragments.PrefsFragment;
import com.example.whattowatch.fragments.SearchFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private List<String> drawerData;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        SearchFragment searchFragment = new SearchFragment();
        transaction.replace(R.id.root, searchFragment);
        transaction.commit();

    }

    private void setupToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.show();
//        }
    }
//
//    private void setupDrawer() {
//        drawerList = findViewById(R.id.left_drawer);
//        drawerLayout = findViewById(R.id.drawer_layout);
//        fillDrawerData();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, drawerData);
//        drawerList.setAdapter(adapter);
//        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                if(position == 0) {
//                    SearchFragment searchFragment = new SearchFragment();
//                    transaction.replace(R.id.root, searchFragment);
//                    transaction.commit();
//                } else if(position == 1) {
//                    PogledatiFragment pogledatiFragment = new PogledatiFragment();
//                    transaction.replace(R.id.root, pogledatiFragment);
//                    transaction.commit();
//                } else if(position == 2) {
//                    PrefsFragment prefsFragment = new PrefsFragment();
//                    transaction.replace(R.id.root, prefsFragment);
//                    transaction.commit();
//                }
//                drawerLayout.closeDrawer(drawerList);
//            }
//        });
//
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
//
//    }
//
//    private void fillDrawerData() {
//        drawerData = new ArrayList<String>();
//        drawerData.add("Pretraga filmova");
//        drawerData.add("Gledaj kasnije");
//        drawerData.add("Podešavanja");
//    }
}
