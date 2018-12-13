package com.cnlab.caucse.chessteample;

public class ChattingListView {
    private String chattingID;
    private String message;

    public void setid(String chattingID){
        this.chattingID=chattingID;
    }
    public void setChattingMessage(String message){
        this.message = message;
    }

    public String getChattingID(){
        return this.chattingID;
    }

    public String getChattingMessage(){
        return this.message;
    }
}
