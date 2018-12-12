package com.cnlab.caucse.chessteample.Network;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GroupManagerServer implements GroupManager{

    private Context mContext = null;
    private String mGroupName = null;
    private List<GroupUser> mUserList = null;
    private String mLocalAddress = null;

    private PeriodicBroadcastSender mPeriodicBroadcastSender = null;
    private GroupTCPServer mGroupTCPServer = null;

    public GroupManagerServer(Context context) {
        mContext = context;
        mGroupName = randomNameGenerator();
        mUserList = new ArrayList<>();
        mLocalAddress = GroupUser.getLocalHost().getHostAddress();
    }

    private String randomNameGenerator() {
        StringBuffer tmp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            switch (rnd.nextInt(2)) {
                case 0: // A-Z
                    tmp.append((char)(rnd.nextInt(26) + 65));
                    break;
                case 1: // 0-9
                    tmp.append(rnd.nextInt(10));
                    break;
            }
        }
        return tmp.toString();
    }

    @Override
    public String getGroupName() {
        return mGroupName;
    }

    public String[] getUserList() {
        String[] userList = null;
        synchronized (mUserList) {
            userList = new String[mUserList.size()];
            for (int i = 0; i < userList.length; i++) {
                userList[i] = mUserList.get(i).getIPAddress();
            }
        }
        return userList;
    }

    public synchronized void joinGroup(final GroupUser user) {
        mUserList.add(user);
        user.startReceive(new GroupUser.receiverTask() {
            @Override
            public void process(String message) {
                Intent intent = new Intent("GroupMessageReceived");
                intent.putExtra("from", user.getIPAddress());
                intent.putExtra("message", message);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                if (message.startsWith("GROUP BROADCAST: ")) {
                    broadcastToGroup(message.substring("GROUP BROADCAST: ".length()), user);
                }
            }
        });
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent("GroupMembersChanged"));
        Log.d("Group Manager", "JOIN USER: " + user.getUserName());
        broadcastToGroup("GROUP " + this.getGroupName() + " MEMBERS:" + Arrays.toString(this.getUserList()));
    }

    public synchronized void leaveGroup(GroupUser user) {
        mUserList.remove(user);
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent("GroupMembersChanged"));
        Log.d("Group Manager", "LEAVE USER: " + user.getUserName());
    }

    public void startGroupTCPServer() {
        if (mGroupTCPServer == null) {
            mGroupTCPServer = new GroupTCPServer(this);
            mGroupTCPServer.start();
            Log.d("Group Manager", "Start Group TCP Server");
        }
    }

    public void startBroadcastingGroupInvitation() {
        if (mPeriodicBroadcastSender == null) {
            mPeriodicBroadcastSender = new PeriodicBroadcastSender("GROUP:" + mGroupName);
            mPeriodicBroadcastSender.start();
            Log.d("Group Manager", "Start Periodic Broadcast Sender");
        }
    }

    public void stopGroupTCPServer() {
        if (mGroupTCPServer != null) {
            mGroupTCPServer.stop();
            mGroupTCPServer = null;
            Log.d("Group Manager", "Stop Group TCP Server");
        }
    }

    public void stopBroadcastingGroupInvitation() {
        if (mPeriodicBroadcastSender != null) {
            mPeriodicBroadcastSender.stop();
            mPeriodicBroadcastSender = null;
            Log.d("Group Manager", "Stop Periodic Broadcast Sender");
        }
    }

    @Override
    public void broadcastToGroup(String message) {
        broadcastToGroup(message, null);
    }

    public synchronized void broadcastToGroup(String message, GroupUser from) {
        Log.d("Group Manager", "Relay to Another Group Members: " + message);
        for(GroupUser user : mUserList) {
            if (user != from) {
                Log.d("Group Manager", "Send To: " + user.getIPAddress());
                user.send(message);
            }
        }
    }

    public int getLocalIndex() {
        for(int i = 0; i < mUserList.size(); i++) {
            if (mUserList.get(i).getIPAddress().equals(mLocalAddress)) {
                return i;
            }
        }
        return -1;
    }

}