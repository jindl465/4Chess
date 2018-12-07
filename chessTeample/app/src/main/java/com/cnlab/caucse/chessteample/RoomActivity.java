package com.cnlab.caucse.chessteample;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomActivity extends AppCompatActivity {

    Button enterRoom;
    Button makeRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        enterRoom = findViewById(R.id.enterRoom);
        makeRoom = findViewById(R.id.makeRoom);

        Typeface face = Typeface.createFromAsset(getAssets(),"DXM.ttf");
        enterRoom.setTypeface(face);
        makeRoom.setTypeface(face);

        enterRoom.setOnClickListener(new View.OnClickListener() {   // 각각의 버튼이 눌리면 하나의 버튼은 없어지게 만들기!
            @Override
            public void onClick(View v) {
                enterRoom.setTextColor(Color.YELLOW);
                Intent intent = new Intent(RoomActivity.this, GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        makeRoom.setOnClickListener(new View.OnClickListener() {   // 각각의 버튼이 눌리면 하나의 버튼은 없어지게 만들기!
            @Override
            public void onClick(View v) {
                makeRoom.setTextColor(Color.YELLOW);
                Intent intent = new Intent(RoomActivity.this, ServerActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
