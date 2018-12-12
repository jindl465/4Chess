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
import com.cnlab.caucse.chessteample.Network.GroupManagerServer;
import com.cnlab.caucse.chessteample.Network.GroupUser;


public class ServerActivity extends AppCompatActivity {

    GroupManagerServer groupManager = null;
    BroadcastReceiver GroupMembersChanged = null;
    BroadcastReceiver GroupMessageReceived = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        groupManager = new GroupManagerServer(this);

        GroupMembersChanged = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TextView groupList = (TextView) findViewById(R.id.groupList);
                StringBuffer innerText = new StringBuffer("List of Group Members");
                for(String ip : groupManager.getUserList()) {
                    innerText.append(System.lineSeparator() + ip);
                }
                groupList.setText(innerText.toString());
            }
        };
        GroupMessageReceived = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TextView clientMessageView = (TextView) findViewById(R.id.clientMessageView);
                clientMessageView.append(System.lineSeparator());
                clientMessageView.append(intent.getStringExtra("from"));
                clientMessageView.append(" > ");
                clientMessageView.append(intent.getStringExtra("message"));
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(GroupMembersChanged, new IntentFilter("GroupMembersChanged"));
        LocalBroadcastManager.getInstance(this).registerReceiver(GroupMessageReceived, new IntentFilter("GroupMessageReceived"));

        groupManager.joinGroup(new GroupUser());

        groupManager.startGroupTCPServer();
        groupManager.startBroadcastingGroupInvitation();

        TextView groupName = (TextView) findViewById(R.id.groupName);
        groupName.setText("Group Name: " + groupManager.getGroupName());

        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editText);
                groupManager.broadcastToGroup(editText.getText().toString());
            }
        });

        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServerActivity.this, GameActivity.class);
                intent.putExtra("playerColor", GroupManager.playerColors[groupManager.getLocalIndex()]);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (groupManager != null) {
            groupManager.startBroadcastingGroupInvitation();
            LocalBroadcastManager.getInstance(this).registerReceiver(GroupMembersChanged, new IntentFilter("GroupMembersChanged"));
            LocalBroadcastManager.getInstance(this).registerReceiver(GroupMessageReceived, new IntentFilter("GroupMessageReceived"));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (groupManager != null) {
            groupManager.stopBroadcastingGroupInvitation();
            LocalBroadcastManager.getInstance(this).unregisterReceiver(GroupMembersChanged);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(GroupMessageReceived);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (groupManager != null) {
            groupManager.stopBroadcastingGroupInvitation();
            groupManager.stopGroupTCPServer();
            LocalBroadcastManager.getInstance(this).unregisterReceiver(GroupMembersChanged);
            LocalBroadcastManager.getInstance(this).unregisterReceiver(GroupMessageReceived);
            groupManager = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(GroupMembersChanged);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(GroupMessageReceived);
        finish();
    }
}
