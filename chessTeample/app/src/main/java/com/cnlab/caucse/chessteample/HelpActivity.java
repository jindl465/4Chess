package com.cnlab.caucse.chessteample;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    private ImageButton backHomeButton;
    private Button mainButton;
    private TextView howto;
    private TextView h1;
    private TextView h2;
    private TextView h3;
    private TextView h4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        backHomeButton = findViewById(R.id.backHomeButton);
        howto = findViewById(R.id.howTo);
        h1 = findViewById(R.id.h1);
        h2 = findViewById(R.id.h2);
        h3 = findViewById(R.id.h3);
        h4 = findViewById(R.id.h4);

        Typeface face = Typeface.createFromAsset(getAssets(),"DXM.ttf");
        howto.setTypeface(face);
        h1.setTypeface(face);
        h2.setTypeface(face);
        h3.setTypeface(face);
        h4.setTypeface(face);

        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainButton = ((MainActivity)MainActivity.context).helpButton;
                mainButton.setTextColor(Color.WHITE);
                finish();
            }
        });
    }
}
