package com.example.keepmealive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlAdapter extends RecyclerView.Adapter<PlAdapter.ViewHolder> {

    private Context context;
    private  ArrayList plant_name, plant_type, water_time;

    PlAdapter(Context context, ArrayList plant_name, ArrayList plant_type, ArrayList water_time)
    {
        this.context=context;
        this.plant_name=plant_name;
        this.plant_type=plant_type;
        this.water_time=water_time;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.plantrow, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.plant_name_txt.setText(String.valueOf(plant_name.get(position)));
        holder.plant_type_txt.setText(String.valueOf(plant_type.get(position)));
        holder.watering_txt.setText(String.valueOf(water_time.get(position)));
    }

    @Override
    public int getItemCount() {
        return plant_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView plant_name_txt, plant_type_txt, watering_txt;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            plant_name_txt=itemView.findViewById(R.id.plantNameText);
            plant_type_txt=itemView.findViewById(R.id.plantTypeText);
            watering_txt=itemView.findViewById(R.id.lastTimeText);
        }
    }
}
