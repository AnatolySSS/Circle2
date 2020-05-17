package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    ImageView startCircle;
    String typeOfCircle;
    static final String ACCESS_MESSAGE = "ACCESS_MESSAGE";
    private static  final int REQUEST_ACCESS_TYPE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startCircle = (ImageView) findViewById(R.id.startCircle);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
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
        startActivityForResult(intent, REQUEST_ACCESS_TYPE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String accessMessage = data.getStringExtra(ACCESS_MESSAGE);
        typeOfCircle = accessMessage;
    }
}
