package com.example.whattowatch.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.whattowatch.R;
import com.example.whattowatch.model.Search;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Activity activity;
    private List<Search> data;

    public CustomAdapter(Activity activity, List<Search> data) {
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
            convertView = activity.getLayoutInflater().inflate(R.layout.custom_list, null);
        }

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvYear = convertView.findViewById(R.id.tvYear);
        ImageView ivThumb = convertView.findViewById(R.id.ivThumb);

        tvTitle.setText(data.get(position).getTitle());
        tvYear.setText(data.get(position).getYear());
        Picasso.get().load(data.get(position).getPoster()).into(ivThumb);

        return convertView;
    }
}