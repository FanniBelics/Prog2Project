package com.example.keepmealive;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class addPlant_fragment extends DialogFragment {

    EditText name_input, type_input, water_input;
    Button add_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        name_input= (EditText) getView().findViewById(R.id.PlantName);
        type_input= (EditText) getView().findViewById(R.id.PlantType);
        water_input= (EditText) getView().findViewById(R.id.WateringTime);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlantDatabaseHelper plantDb = new PlantDatabaseHelper(AddActivity.this);
                plantDb.addPlant(name_input.getText().toString().trim(), type_input.getText().toString().trim());
            }
        });

        return inflater.inflate(R.layout.add_plant_layout,container,false);
    }
}