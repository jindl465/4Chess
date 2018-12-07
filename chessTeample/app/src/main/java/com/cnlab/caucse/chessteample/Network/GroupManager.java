package com.cnlab.caucse.chessteample.Network;

public interface GroupManager {
    String getGroupName();
    void broadcastToGroup(String message);
}
