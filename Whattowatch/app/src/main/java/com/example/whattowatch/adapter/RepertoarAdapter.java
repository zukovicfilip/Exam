package com.example.whattowatch.adapter;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.whattowatch.R;
import com.example.whattowatch.model.Movie;
import com.example.whattowatch.model.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RepertoarAdapter extends BaseAdapter {

    private Activity activity;
    private List<Movie> data;

    public RepertoarAdapter(Activity activity, List<Movie> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.repertoar_list, null);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvCena = convertView.findViewById(R.id.tvCena);
        TextView tvSatnica = convertView.findViewById(R.id.tvSatnica);
        ImageView ivThumb = convertView.findViewById(R.id.ivThumb);

        tvTitle.setText(data.get(position).getTitle());
        tvCena.setText("Cena je " + data.get(position).getCena());
        tvSatnica.setText("Projekcija je u " + data.get(position).getSatnica());
        Picasso.get().load(data.get(position).getPoster()).into(ivThumb);

        return convertView;
    }
}