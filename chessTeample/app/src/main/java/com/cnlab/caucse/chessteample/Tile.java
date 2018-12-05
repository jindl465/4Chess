package com.cnlab.caucse.chessteample;

import android.graphics.Color;

public class Tile {
    private boolean active;
    private boolean onPiece;
    private String PieceType;
    private int color;

    public Tile(){
        this.active = false;
        this.onPiece = false;
        this.PieceType = "NONE";
        this.color = Color.GRAY;
    }

    public void setActive(boolean tf) {
        this.active = tf;
    }

    public boolean getActive() {
        return this.active;
    }

    public boolean isOnPiece() {
        return this.onPiece;
    }

    public void setOnPiece(boolean onPiece) {
        this.onPiece = onPiece;
    }

    public void setOccupyPiece(String pc) {
        this.PieceType = pc;
    }

    public String getOccupyPiece() {
        return PieceType;
    }

    public void setColor(int color){
        this.color = color;
    }
}

