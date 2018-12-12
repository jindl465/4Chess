package com.cnlab.caucse.chessteample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cnlab.caucse.chessteample.Network.GroupManager;
import com.cnlab.caucse.chessteample.Network.GroupManagerClient;
import com.cnlab.caucse.chessteample.Network.GroupUser;

public class ClientActivity extends AppCompatActivity {

    GroupManagerClient groupManagerClient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        groupManagerClient = new GroupManagerClient(this);

        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TextView groupStatus = (TextView) findViewById(R.id.groupStatus);
                StringBuffer innerText = new StringBuffer("Group Name: ");
                innerText.append(groupManagerClient.getGroupName());
                innerText.append(System.lineSeparator());
                innerText.append("List of Group Members");
                for(GroupUser user : groupManagerClient.getUserList()) {
                    innerText.append(System.lineSeparator());
                    innerText.append(user.getIPAddress());
                }
                groupStatus.setText(innerText.toString());
                ((TextView) findViewById(R.id.serverMessageView)).setText("Messages from Server");
            }
        }, new IntentFilter("GroupMembersChanged"));

        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TextView serverMessageView = (TextView) findViewById(R.id.serverMessageView);
                String message = intent.getStringExtra("message");
                serverMessageView.append(System.lineSeparator());
                serverMessageView.append(message);
            }
        }, new IntentFilter("GroupMessageReceived"));

        groupManagerClient.startReceivingGroupInvitation();

        Button sendButton = (Button) findViewById(R.id.sendButton2);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText2);
                groupManagerClient.broadcastToGroup(editText.getText().toString());
            }
        });

        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientActivity.this, GameActivity.class);
                intent.putExtra("playerColor", GroupManager.playerColors[groupManagerClient.getLocalIndex()]);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (groupManagerClient != null) {
            groupManagerClient.startReceivingGroupInvitation();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (groupManagerClient != null) {
            groupManagerClient.stopReceivingGroupInvitation();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (groupManagerClient != null) {
            groupManagerClient.stopReceivingGroupInvitation();
            groupManagerClient = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
