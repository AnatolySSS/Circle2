package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingResult;

public class ChoiceActivity extends AppCompatActivity {

    ImageView bigCircle;
    String typeOfCircle;
    Intent data = new Intent();
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        bigCircle = (ImageView) findViewById(R.id.bigCircle);
        getValueOfCircle("circle_black");
    }

    public void onClickCircleBlack(View view) {
        bigCircle.setImageResource(R.drawable.circle_black_big);
        getValueOfCircle("circle_black");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleBlack2(View view) {
        bigCircle.setImageResource(R.drawable.circle_black2_big);
        getValueOfCircle("circle_black2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleBlue(View view) {
        bigCircle.setImageResource(R.drawable.circle_blue_big);
        getValueOfCircle("circle_blue");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleBlue2(View view) {
        bigCircle.setImageResource(R.drawable.circle_blue2_big);
        getValueOfCircle("circle_blue2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleRed(View view) {
         bigCircle.setImageResource(R.drawable.circle_red_big);
        getValueOfCircle("circle_red");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCircleRed2(View view) {
        bigCircle.setImageResource(R.drawable.circle_red2_big);
        getValueOfCircle("circle_red2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCirclePurple(View view) {
        bigCircle.setImageResource(R.drawable.circle_purple_big);
        getValueOfCircle("circle_purple");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    public void onClickCirclePurpleValued(View view) {
        bigCircle.setImageResource(R.drawable.circle_purple2_big);
        getValueOfCircle("circle_purple2");
        anim = AnimationUtils.loadAnimation(this, R.anim.myscale);
        bigCircle.startAnimation(anim);
    }

    private void getValueOfCircle(String message){
        data.putExtra(MainActivity.ACCESS_MESSAGE, message);
    }
    public void OnClickChoiceCircleButton(View view) {
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("typeOfCircle", typeOfCircle);
        setResult(RESULT_OK, data);
        finish();
    }
}
