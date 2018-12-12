package com.cnlab.caucse.chessteample.Network;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class GroupUser {

    private String mUserName = null;
    private Socket mSocket = null;
    private InetAddress mAddress = null;
    private BufferedReader mBufferedReader = null;
    private PrintWriter mPrintWriter = null;
    private Thread mReceiverThread = null;

    public GroupUser() {
        mAddress = getLocalHost();
        mUserName = mAddress.getHostAddress();
        Log.d("GroupUser", "New localhost user created");
    }

    public GroupUser(InetAddress inetAddress) {
        mAddress = inetAddress;
        mUserName = mAddress.getHostAddress();
        Log.d("GroupUser", "New virtual user created");
    }

    public GroupUser(Socket socket) {
        this(socket, socket.getInetAddress().getHostAddress());
    }

    public GroupUser(Socket socket, String userName) {
        mUserName = userName;
        mSocket = socket;
        mAddress = socket.getInetAddress();
        try {
            mBufferedReader = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
            mPrintWriter = new PrintWriter(new OutputStreamWriter(mSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("GroupUser", "New " + userName + " user created");
    }

    public static InetAddress getLocalHost() {
        InetAddress result = null;
        try {
            for(Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        result = inetAddress;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void send(String message) {
        if (mPrintWriter != null) {
            mPrintWriter.println(message);
            mPrintWriter.flush();
            Log.d("GroupUser", getUserName() + " Send: " + message);
        }
        else {
            Log.d("GroupUser", getUserName() + " Error : Output stream not available");
        }
    }

    public void startReceive(final receiverTask task, final Runnable leaveTask) {
        mReceiverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (mBufferedReader != null) {
                        while (true) {
                            String message = mBufferedReader.readLine();
                            if (message == null) {
                                Log.d("GroupUser", getUserName() + "Error : Input stream returned null value");
                                leaveTask.run();
                                break;
                            }
                            else {
                                Log.d("GroupUser", getUserName() + " Receive: " + message);
                                task.process(message);
                            }
                        }
                    }
                    else {
                        Log.d("GroupUser", getUserName() + "Error : Input stream not available");
                    }
                } catch (IOException e) {
                    Log.d("GroupUser", getUserName() + "Error : Input stream throws exception");
                    e.printStackTrace();
                }
            }
        });
        mReceiverThread.start();
    }

    public void stopReceive() {
        if (mReceiverThread != null) {
            mReceiverThread.interrupt();
            mReceiverThread = null;
        }
    }

    public String getUserName() {
        return mUserName;
    }

    public String getIPAddress() {
        return mAddress.getHostAddress();
    }

    interface receiverTask {
        void process(String message);
    }
}
