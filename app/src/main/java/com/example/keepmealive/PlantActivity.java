package com.example.keepmealive;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class PlantActivity extends AppCompatActivity {

    private ImageButton openAddPlantFragment;
    ImageButton backHomeButton;


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


    }

}