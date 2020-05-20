package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    ImageView startCircle;
    String typeOfCircle = "circle_black";
    static final String ACCESS_MESSAGE = "ACCESS_MESSAGE";
    private static  final int REQUEST_ACCESS_TYPE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCircle = (ImageView) findViewById(R.id.startCircle);
    }

    public void onClick (View view){
        EditText namefield = (EditText) findViewById(R.id.name);
        String namestr = namefield.getText().toString();
        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("name", namestr);
        intent.putExtra("typeOfCircle", typeOfCircle);
        startActivity(intent);
    }

    public void onClickAnim (View view){
        Animation anim;
        anim = AnimationUtils.loadAnimation(this, R.anim.myalpha);
        startCircle.startAnimation(anim);
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

        if (typeOfCircle.equals("circle_black")) {
            startCircle.setImageResource(R.drawable.circle_black);
        } else if (typeOfCircle.equals("circle_black2")) {
            startCircle.setImageResource(R.drawable.circle_black2);
        } else if (typeOfCircle.equals("circle_blue2")) {
            startCircle.setImageResource(R.drawable.circle_blue2);
        } else if (typeOfCircle.equals("circle_blue")) {
            startCircle.setImageResource(R.drawable.circle_blue);
        } else if (typeOfCircle.equals("circle_red")) {
            startCircle.setImageResource(R.drawable.circle_red);
        } else if (typeOfCircle.equals("circle_red2")) {
            startCircle.setImageResource(R.drawable.circle_red2);
        } else if (typeOfCircle.equals("circle_purple")) {
            startCircle.setImageResource(R.drawable.circle_purple);
        } else if (typeOfCircle.equals("circle_purple2")) {
            startCircle.setImageResource(R.drawable.circle_purple2);
        }
    }
}
