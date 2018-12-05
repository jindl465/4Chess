package com.cnlab.caucse.chessteample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public Button startButton;
    public Button helpButton;
    public Button quitButton;
    public static Context context;
    private TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        helpButton = findViewById(R.id.helpButton);
        quitButton = findViewById(R.id.quitButton);
        appName = findViewById(R.id.appName);
        context = this;

        Typeface face = Typeface.createFromAsset(getAssets(),"KAMIKZOM.ttf");
        Typeface faceButton = Typeface.createFromAsset(getAssets(),"Hoon.ttf");
        appName.setTypeface(face);
        startButton.setTypeface(faceButton);
        helpButton.setTypeface(faceButton);
        quitButton.setTypeface(faceButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setTextColor(Color.YELLOW);
                
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpButton.setTextColor(Color.YELLOW);
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }
}
