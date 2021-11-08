package com.example.keepmealive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class PlantActivity extends AppCompatActivity {

    private ImageButton openAddPlantFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);
        openAddPlantFragment = findViewById(R.id.addPlant);



        openAddPlantFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment dialog = new addPlant_fragment();
                dialog.show(getSupportFragmentManager(), "Addplant");
            }
        });


    }

    void backHome(View v)
    {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}