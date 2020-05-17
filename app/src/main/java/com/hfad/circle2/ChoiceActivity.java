package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChoiceActivity extends AppCompatActivity {

    ImageView bigCircle;
    String typeOfCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        bigCircle = (ImageView) findViewById(R.id.bigCircle);
    }

    public void OnClickBackButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void onClickCircleBlack(View view) {
        bigCircle.setImageResource(R.drawable.circle_black_big);
        typeOfCircle = "circle_black";
    }

    public void onClickCircleBlue(View view) {
        bigCircle.setImageResource(R.drawable.circle_blue_big);
        typeOfCircle = "circle_blue";
    }

    public void onClickCircleRed(View view) {
         bigCircle.setImageResource(R.drawable.circle_red_big);
        typeOfCircle = "circle_red";
    }

    public void onClickCirclePurple(View view) {
        bigCircle.setImageResource(R.drawable.circle_purple_big);
        typeOfCircle = "circle_purple";
    }

    public void onClickCirclePurpleValued(View view) {
        bigCircle.setImageResource(R.drawable.circle_purple2_big);
        typeOfCircle = "circle_purple2";
    }

    public void OnClickChoiceCircleButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("typeOfCircle", typeOfCircle);
        startActivity(intent);
    }
}
