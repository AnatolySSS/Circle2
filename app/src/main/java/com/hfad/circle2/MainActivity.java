package com.hfad.circle2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick (View view){
        EditText namefield = (EditText) findViewById(R.id.name);
        String namestr = namefield.getText().toString();

        Intent intent = new Intent(this, PlayActivity.class);
        intent.putExtra("name", namestr);
        startActivity(intent);
    }
}
