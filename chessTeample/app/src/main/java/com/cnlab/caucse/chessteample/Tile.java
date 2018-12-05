package com.cnlab.caucse.chessteample;

import android.graphics.Color;

import com.cnlab.caucse.chessteample.Piece.Bishop;
import com.cnlab.caucse.chessteample.Piece.King;
import com.cnlab.caucse.chessteample.Piece.Knight;
import com.cnlab.caucse.chessteample.Piece.Pawn;
import com.cnlab.caucse.chessteample.Piece.Piece;
import com.cnlab.caucse.chessteample.Piece.Queen;
import com.cnlab.caucse.chessteample.Piece.Rook;


public class Tile {
    private boolean active;
    private boolean onPiece;
    private String PieceType;
    private String color;
    private Piece piece;

    public Tile(){
        this.active = false;
        this.onPiece = false;
        this.PieceType = null;
        this.color = "NONE";
    }
    public Tile(String PieceType, String color, Position pos){
        this.active = false;
        this.onPiece = true;
        this.PieceType = PieceType;
        this.color = color;

        if(PieceType.equals("KING")){
            piece = new King(color, pos);
        }
        if(PieceType.equals("QUEEN")){
            piece = new Queen(color, pos);
        }
        if(PieceType.equals("ROOK")){
            piece = new Rook(color, pos);
        }
        if(PieceType.equals("PAWN")){
            piece = new Pawn(color, pos);
        }
        if(PieceType.equals("BISHOP")){
            piece = new Bishop(color, pos);
        }
        if(PieceType.equals("KNIGHT")){
            piece = new Knight(color, pos);
        }
        if(PieceType.equals("NONE")){
            piece = null;
        }
    }
    public Piece getPiece(){
        return this.piece;
    }
    public void setPiece(Piece pc){
        piece = pc;
    }
    public String getPiecetype(){
        return this.PieceType;
    }
    public void setPiecetype(String pc){
        PieceType = pc;
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

    public void setColor(String color){
        this.color = color;
    }

    public String getColor(){return this.color;}
}

