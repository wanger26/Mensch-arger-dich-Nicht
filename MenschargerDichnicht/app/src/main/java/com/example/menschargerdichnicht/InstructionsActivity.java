package com.example.menschargerdichnicht;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;

public class InstructionsActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity__instructions);
        }

    public void onClickPlayInst(View view){
        startActivity(new Intent(InstructionsActivity.this, PlayActivity.class));
    }
    public void onHome(View view){
        startActivity(new Intent(InstructionsActivity.this, MainActivity.class));
    }
    public void onMoreHelp(View view){
        WebView moreHelp= new WebView(this);
        setContentView(moreHelp);
        moreHelp.loadUrl("https://en.wikipedia.org/wiki/Mensch_%C3%A4rgere_Dich_nicht");
    }

}
