package com.cnlab.caucse.chessteample.Network;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GroupInvitationReceiver {

    private Thread mBroadcastReceiver = null;
    private boolean mBroadcastReceiveState = false;

    public GroupInvitationReceiver(final Context context, final GroupManagerClient groupManagerClient) {
        mBroadcastReceiver = new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(50000);

                    while (mBroadcastReceiveState) {
                        byte[] buf = new byte[1024];
                        DatagramPacket packet = new DatagramPacket(buf, buf.length);
                        socket.receive(packet);
                        String message = new String(packet.getData()).trim();
                        Log.d("GroupInvitationReceiver", "Broadcast Message: " + message);
                        if (message.startsWith("GROUP:")) {
                            groupManagerClient.setGroupName(message.substring(6));
                            GroupUser server = new GroupUser(new Socket(packet.getAddress(), 50001));
                            server.send("GROUP JOIN:" + groupManagerClient.getGroupName());
                            groupManagerClient.getUserList().add(server);
                            server.startReceive(new GroupUser.receiverTask() {
                                @Override
                                public void process(String message) {
                                    if (message.startsWith("GROUP ") && message.startsWith(" MEMBERS:", 11)) {
                                        String[] membersIP = message.substring(21, message.length() - 1).split(", ");
                                        for (int i = 1; i < groupManagerClient.getUserList().size(); i++) {
                                            groupManagerClient.getUserList().remove(i);
                                        }
                                        try {
                                            for (int i = 1; i < membersIP.length ; i++) {
                                                groupManagerClient.getUserList().add(new GroupUser(InetAddress.getByName(membersIP[i])));
                                            }
                                        } catch (UnknownHostException e) {
                                            e.printStackTrace();
                                        }
                                        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("GroupMembersChanged"));
                                    } else {
                                        Intent intent = new Intent("GroupMessageReceived");
                                        intent.putExtra("message", message);
                                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                                        Log.d("GroupUser", "Message from Server: " + message);
                                    }
                                }
                            });
                            mBroadcastReceiveState = false;
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (socket != null) {
                        socket.close();
                        socket = null;
                    }
                }
            }
        });
    }

    public void start() {
        if (mBroadcastReceiveState == false) {
            mBroadcastReceiveState = true;
            mBroadcastReceiver.start();
        }
    }

    public void stop() {
        mBroadcastReceiveState = false;
    }
}
