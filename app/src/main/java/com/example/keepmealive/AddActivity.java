package com.example.keepmealive;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity
{
    EditText name_input, type_input, watering_input;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_plant_layout);

        name_input = findViewById(R.id.PlantName);
        type_input = findViewById(R.id.PlantType);
        watering_input = findViewById(R.id.WateringTime);
        addButton = findViewById(R.id.addPlantButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlantDatabaseHelper plantdb = new PlantDatabaseHelper(AddActivity.this);
                plantdb.addPlant(name_input.getText().toString().trim(), type_input.getText().toString().trim());

            }
        });
    }
}
