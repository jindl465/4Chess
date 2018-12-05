package com.cnlab.caucse.chessteample;

import android.graphics.Color;

public class King extends Piece {
    @Override
    public String getColor() {
        return null;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public Position[] getCanMoves() {
        return new Position[0];
    }
}
