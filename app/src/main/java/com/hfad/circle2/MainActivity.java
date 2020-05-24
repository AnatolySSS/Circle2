package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Animation anim_circle;
    Animation anim_exit_act;
    ImageView startCircle;
    ImageView imageNoAds;
    ImageView imageResults;
    ImageView imageChangeCircle;
    ImageView imageBuy;
    TextView circle2;
    TextView tapToGame;

    String typeOfCircle = "circle_black2";
    static final String ACCESS_MESSAGE = "ACCESS_MESSAGE";
    private static  final int REQUEST_ACCESS_TYPE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCircle = (ImageView) findViewById(R.id.startCircle);
        imageNoAds = (ImageView) findViewById(R.id.imageNoAds);
        imageResults = (ImageView) findViewById(R.id.imageResults);
        imageChangeCircle = (ImageView) findViewById(R.id.imageChangeCircle);
        imageBuy = (ImageView) findViewById(R.id.imageBuy);
        circle2 = (TextView) findViewById(R.id.circle2);
        tapToGame = (TextView) findViewById(R.id.tapToGame);
    }

    public void onClick (View view){
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("typeOfCircle", typeOfCircle);

        anim_exit_act = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_main);
        imageNoAds.startAnimation(anim_exit_act);
        imageResults.startAnimation(anim_exit_act);
        imageChangeCircle.startAnimation(anim_exit_act);
        imageBuy.startAnimation(anim_exit_act);
        circle2.startAnimation(anim_exit_act);
        tapToGame.startAnimation(anim_exit_act);

        startActivity(intent);
    }

    public void onChoice (View view) {
        Intent intent = new Intent(this, ChoiceActivity.class);
        startActivityForResult(intent, REQUEST_ACCESS_TYPE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        typeOfCircle = data.getStringExtra(ACCESS_MESSAGE);

        if (typeOfCircle.equals("circle_black2")) {
            startCircle.setImageResource(R.drawable.circle_black2);
        } else if (typeOfCircle.equals("circle_blue2")) {
            startCircle.setImageResource(R.drawable.circle_blue2);
        } else if (typeOfCircle.equals("circle_red2")) {
            startCircle.setImageResource(R.drawable.circle_red2);
        } else if (typeOfCircle.equals("circle_purple2")) {
            startCircle.setImageResource(R.drawable.circle_purple2);
        }
        anim_circle = AnimationUtils.loadAnimation(this, R.anim.myalpha);
        startCircle.startAnimation(anim_circle);
    }
}
