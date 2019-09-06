package com.example.menschargerdichnicht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickPlay(View view){
        Intent intent= new Intent();
        startActivity(new Intent(MainActivity.this, PlayActivity.class));
    }

    public void onClickInstructions(View view){
        startActivity(new Intent(MainActivity.this, InstructionsActivity.class));
    }
}
