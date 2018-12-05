package com.cnlab.caucse.chessteample;

import android.graphics.Color;

public abstract class Piece {
    public abstract String getColor();
    public abstract boolean isAlive();
    public abstract Position getPosition();
    public abstract Position[] getCanMoves();
    //public abstract Position[] getCanMovesForKing();
}
