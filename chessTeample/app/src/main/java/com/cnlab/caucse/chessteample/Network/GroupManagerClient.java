package com.cnlab.caucse.chessteample.Network;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class GroupManagerClient{

    private Context mContext = null;
    private String mGroupName = null;
    private static List<GroupUser> mUserList = null;
    private String mLocalAddress = null;

    private GroupInvitationReceiver mGroupInvitationReceiver = null;

    public GroupManagerClient(Context context) {
        mContext = context;
        mUserList = new ArrayList<>();
        mLocalAddress = GroupUser.getLocalHost().getHostAddress();
    }

    public List<GroupUser> getUserList() {
        return mUserList;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public static void broadcastToGroup(String message) {
        mUserList.get(0).send("GROUP BROADCAST: " + message);
    }

    public void startReceivingGroupInvitation() {
        if (mGroupInvitationReceiver == null) {
            mGroupInvitationReceiver = new GroupInvitationReceiver(mContext, this);
            mGroupInvitationReceiver.start();
            Log.d("Group Manager Client", "Start Group Invitation Receiver");
        }
    }

    public void stopReceivingGroupInvitation() {
        if (mGroupInvitationReceiver != null) {
            mGroupInvitationReceiver.stop();
            mGroupInvitationReceiver = null;
            Log.d("Group Manager Client", "Stop Group Invitation Receiver");
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
