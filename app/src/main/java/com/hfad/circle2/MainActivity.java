package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    String typeOfCircle = "circle_black_big";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageView startCircle = (ImageView) findViewById(R.id.startCircle);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle arguments = getIntent().getExtras();
        typeOfCircle = arguments != null ? arguments.getString("typeOfCircle") : "";

        switch (typeOfCircle) {
            case "circle_black":
                startCircle.setImageResource(R.drawable.circle_black);
                break;
            case "circle_blue":
                startCircle.setImageResource(R.drawable.circle_blue);
                break;
            case "circle_red":
                startCircle.setImageResource(R.drawable.circle_red);
                break;
            case "circle_purple":
                startCircle.setImageResource(R.drawable.circle_purple);
                break;
            case "circle_purple2":
                startCircle.setImageResource(R.drawable.circle_purple2);
                break;
        }
    }

    public void onClick (View view){
        EditText namefield = (EditText) findViewById(R.id.name);
        String namestr = namefield.getText().toString();

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("name", namestr);
        startActivity(intent);
    }

    public void onChoice (View view) {
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivity(intent);
    }
}
