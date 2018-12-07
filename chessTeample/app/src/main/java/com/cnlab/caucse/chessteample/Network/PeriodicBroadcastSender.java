package com.cnlab.caucse.chessteample.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class PeriodicBroadcastSender {

    private Thread mBroadcastSender = null;
    private boolean mBroadcastSendState = false;
    private String mBroadcastSendMessage = null;
    private int mBroadcastSendPeriod = 2;

    public PeriodicBroadcastSender(String BroadcastSendMessage) {
        this(BroadcastSendMessage, 2);
    }

    public PeriodicBroadcastSender(String BroadcastSendMessage, int BroadcastSendPeriod) {
        mBroadcastSendMessage = BroadcastSendMessage;
        mBroadcastSendPeriod = BroadcastSendPeriod;

        mBroadcastSender = new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket();
                    InetAddress ia = InetAddress.getByName("255.255.255.255");
                    byte[] buf = mBroadcastSendMessage.getBytes();
                    while (mBroadcastSendState == true) {
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, ia, 50000);
                        socket.send(packet);
                        Thread.sleep(mBroadcastSendPeriod * 1000);
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    mBroadcastSendState = false;
                    if (socket != null) {
                        socket.close();
                    }
                }
                socket = null;
            }
        });
        mBroadcastSendState = false;
    }

    public void start() {
        if (mBroadcastSendState == false) {
            mBroadcastSendState = true;
            mBroadcastSender.start();
        }
    }

    public void stop() {
        mBroadcastSendState = false;
    }
}
