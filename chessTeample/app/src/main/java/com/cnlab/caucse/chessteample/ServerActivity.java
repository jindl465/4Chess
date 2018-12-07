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

import com.cnlab.caucse.chessteample.Network.GroupManagerServer;
import com.cnlab.caucse.chessteample.Network.GroupUser;


public class ServerActivity extends AppCompatActivity {

    GroupManagerServer groupManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        groupManager = new GroupManagerServer(this);

        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TextView groupList = (TextView) findViewById(R.id.groupList);
                StringBuffer innerText = new StringBuffer("List of Group Members");
                for(String ip : groupManager.getUserList()) {
                    innerText.append(System.lineSeparator() + ip);
                }
                groupList.setText(innerText.toString());
            }
        }, new IntentFilter("GroupMembersChanged"));

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        groupManager.stopBroadcastingGroupInvitation();
        groupManager.stopGroupTCPServer();
    }
}
