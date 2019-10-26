package com.example.locationpicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.locationpicker.Constants.EXTRA_LATITUDE;
import static com.example.locationpicker.Constants.EXTRA_LONGITUDE;

public class MainActivity extends AppCompatActivity {

    //https://www.dev2qa.com/android-togglebutton-example/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pickLocationBtn = findViewById(R.id.btnPickLocation);
        pickLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationPickerActivity.class);
                startActivityForResult(intent,LocationPickerActivity.LOCATION_REQUEST_CODE);
            }
        });

        Button btnOpenMap = findViewById(R.id.button_open_map);
        btnOpenMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //get location
        if (requestCode == LocationPickerActivity.LOCATION_REQUEST_CODE){

            if (resultCode == RESULT_OK){
                //location results received
                String latitude = data.getStringExtra(EXTRA_LATITUDE);
                String longitude = data.getStringExtra(EXTRA_LONGITUDE);

                Toast.makeText(this, "LOC = "+latitude+ " --- "+longitude, Toast.LENGTH_SHORT).show();
            }else {
                //location not received
                Toast.makeText(this, "Location not received", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
