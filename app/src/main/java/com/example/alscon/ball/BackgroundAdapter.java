package com.example.alscon.ball;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Alscon on 04-Jan-17.
 */

public class BackgroundAdapter extends RecyclerView.Adapter<BackgroundAdapter.ViewHolder> {
    private ArrayList<AdapterElements> arrayList;
    private Context mcontext;

    public BackgroundAdapter(Context context, ArrayList<AdapterElements> android) {
        this.arrayList = android;
        this.mcontext = context;
    }

    @Override
    public void onBindViewHolder(BackgroundAdapter.ViewHolder holder, int i) {

        holder.textView.setText(arrayList.get(i).getTitle());
        holder.imageView.setImageResource(arrayList.get(i).getImage());
    }

    @Override
    public BackgroundAdapter.ViewHolder onCreateViewHolder(ViewGroup vGroup, int i) {
        View view = LayoutInflater.from(vGroup.getContext()).inflate(R.layout.background_adapter, vGroup, false);
        return new ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View v) {
            super(v);

            textView = (TextView) v.findViewById(R.id.text);
            imageView = (ImageView) v.findViewById(R.id.image);
        }
    }
}
