package com.example.keepmealive;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class addPlant_fragment extends DialogFragment implements View.OnClickListener{

    private Context mContext;

    public addPlant_fragment() {
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mContext=context;
    }

    EditText name_input, type_input, water_input;
    Button add_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.add_plant_layout,container,false);
        name_input= (EditText) view.findViewById(R.id.PlantName);
        type_input= (EditText) view.findViewById(R.id.PlantType);
        water_input= (EditText) view.findViewById(R.id.WateringTime);
        add_button = (Button) view.findViewById(R.id.addPlantButton);
        add_button.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        String namestr = name_input.getText().toString();
        String typestr = type_input.getText().toString();
        String waterstr = water_input.getText().toString();
        PlantDatabaseHelper plantDb = new PlantDatabaseHelper(getContext());
        plantDb.addPlant(namestr, typestr);
        dismiss();
//        Toast.makeText(mContext, namestr,Toast.LENGTH_SHORT).show();
    }
}