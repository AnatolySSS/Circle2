package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {

    ImageView circle = (ImageView) findViewById(R.id.circle);
    int score = 0;
    int stopmark;
    String name;
    String typeOfCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Bundle arguments = getIntent().getExtras();
        name = arguments != null ? arguments.getString("name") : "";
        typeOfCircle = arguments != null ? arguments.getString("typeOfCircle") : "";

        switch (typeOfCircle) {
            case "circle_black":
                circle.setImageResource(R.drawable.circle_black);
                break;
            case "circle_blue":
                circle.setImageResource(R.drawable.circle_blue);
                break;
            case "circle_red":
                circle.setImageResource(R.drawable.circle_red);
                break;
            case "circle_purple":
                circle.setImageResource(R.drawable.circle_purple);
                break;
            case "circle_purple2":
                circle.setImageResource(R.drawable.circle_purple2);
                break;
        }
    }

    public int Screenwidth() {
        int width;
        Point size = new Point();
        WindowManager w = getWindowManager();
        w.getDefaultDisplay().getSize(size);
        width = size.x;
        return width;
    }

    public int Screenheight() {
        int height;
        Point size = new Point();
        WindowManager w = getWindowManager();
        w.getDefaultDisplay().getSize(size);
        height = size.y;
        return height;
    }

    public void onClick (View view){

        TextView textView = (TextView) findViewById(R.id.textView);
        TextView scoreText = (TextView) findViewById(R.id.scoreText);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.ll);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        String scoretxt = "";
        String scoretotal;
        int circleWidth = circle.getWidth();
        int circleHeight = circle.getHeight();

        if(score == 0) {
            new CountDownTimer(10000, 1000) {

                TextView timerText = (TextView) findViewById(R.id.timerText);

                //Здесь обновляем текст счетчика обратного отсчета с каждой секундой
                public void onTick(long millisUntilFinished) {

                    String timertxt = "Осталось: " + millisUntilFinished / 1000;
                    timerText.setText(timertxt);
                }

                //Задаем действия после завершения отсчета (высвечиваем надпись "Бабах!"):
                public void onFinish() {
                    timerText.setText("Time's out!");
                    stopmark = 1;
                }
            }
            .start();
        }

        if(stopmark != 1) {

            score++;
            scoretxt = String.valueOf(score);

            int maxWidth = (int) (Screenwidth() - circleWidth);
            int maxHeight = (int) (Screenheight() - circleHeight);

            Random random = new Random();
            int xRand = random.nextInt(500);
            int yRand = random.nextInt(700);

            String string = "X: " + xRand + "; Y: " + yRand;

            textView.setText(string);
            scoreText.setText(scoretxt);

            layoutParams.leftMargin = xRand;
            layoutParams.topMargin = yRand;

            circle.setLayoutParams(layoutParams);
        } else {
            scoretotal = name + ", you score is " + score;
            textView.setText(scoretotal);

            layoutParams.gravity = Gravity.CENTER;
            layoutParams.weight = 1;
            circle.setLayoutParams(layoutParams);

            Button againbutton = new Button(this);
            againbutton.setText("START");
            relativeLayout.addView(againbutton);
        }
    }
}
