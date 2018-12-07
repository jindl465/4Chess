package com.cnlab.caucse.chessteample.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class GroupTCPServer {

    private static final int port = 50001;

    private Thread mReceiverThread = null;
    private boolean mRecieveState = false;

    public GroupTCPServer(final GroupManagerServer groupManager) {
        mReceiverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerSocket serverSocket = null;
                Socket socket = null;
                BufferedReader br = null;
                try {
                    serverSocket = new ServerSocket(port);
                    while (mRecieveState == true) {
                        socket = serverSocket.accept();
                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        if (br.readLine().equals("GROUP JOIN:" + groupManager.getGroupName())) {
                            groupManager.joinGroup(new GroupUser(socket));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (serverSocket != null) {
                            serverSocket.close();
                            serverSocket = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void start() {
        if (mReceiverThread != null) {
            mRecieveState = true;
            mReceiverThread.start();
        }
    }

    public void stop() {
        mRecieveState = false;
    }
}
