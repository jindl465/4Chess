package com.cnlab.caucse.chessteample.Network;

public interface GroupManager {
    String[] playerColors = {"BLACK", "WHITE", "RED", "GREEN"};

    String getGroupName();
    void broadcastToGroup(String message);
}
