package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }

    public void OnClickBackButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void OnClickChoiceCircleButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void onClickCircleBlack(View view) {
        ImageView bigCircle = (ImageView) findViewById(R.id.bigCircle);
        bigCircle.setImageResource(R.drawable.circle_black_big);
    }

    public void onClickCircleBlue(View view) {
        ImageView bigCircle = (ImageView) findViewById(R.id.bigCircle);
        bigCircle.setImageResource(R.drawable.circle_blue_big);
    }

    public void onClickCircleRed(View view) {
        ImageView bigCircle = (ImageView) findViewById(R.id.bigCircle);
        bigCircle.setImageResource(R.drawable.circle_red_big);
    }

    public void onClickCirclePurple(View view) {
        ImageView bigCircle = (ImageView) findViewById(R.id.bigCircle);
        bigCircle.setImageResource(R.drawable.circle_purple_big);
    }

    public void onClickCirclePurpleValued(View view) {
        ImageView bigCircle = (ImageView) findViewById(R.id.bigCircle);
        bigCircle.setImageResource(R.drawable.circle_purple2_big);
    }
}
