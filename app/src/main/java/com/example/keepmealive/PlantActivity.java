package com.example.keepmealive;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PlantActivity extends AppCompatActivity {

    private ImageButton openAddPlantFragment;
    ImageButton backHomeButton;

    RecyclerView addedPlants;
    static PlantDatabaseHelper plantdb;
    ArrayList <String> plant_name, plant_type;
    ArrayList<String> plant_added;

    PlAdapter pladapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);
        openAddPlantFragment = findViewById(R.id.addPlant);
        backHomeButton = findViewById(R.id.backHome);

        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        openAddPlantFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment dialog = new addPlant_fragment();
                dialog.show(getSupportFragmentManager(), "Addplant");
            }
        });

        plantdb=new PlantDatabaseHelper(PlantActivity.this);
        addedPlants = findViewById(R.id.recyclerView);

        plant_name = new ArrayList<>();
        plant_type = new ArrayList<>();
        plant_added = new ArrayList<>();

        displayData();

        pladapter = new PlAdapter(PlantActivity.this, plant_name, plant_type, plant_added);
        addedPlants.setAdapter(pladapter);
        addedPlants.setLayoutManager(new LinearLayoutManager(PlantActivity.this));

    }
    void displayData() {
        Cursor crs = plantdb.readAllData();
        int period;
        if (crs.getCount() != 0) {
            while (crs.moveToNext()) {
                plant_name.add(crs.getString(1));
                plant_type.add(crs.getString(2));
                Date dt = new Date(crs.getLong(3));
                period=crs.getInt(4);
                Calendar c = Calendar.getInstance();
                c.setTime(dt);
                c.add(Calendar.DATE, period);
                dt=c.getTime();
                plant_added.add(dt.toString());
            }
        } else
        {
            Toast.makeText(this,"No Data", Toast.LENGTH_SHORT).show();
        }
    }

}