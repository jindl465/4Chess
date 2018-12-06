package com.cnlab.caucse.chessteample.Piece;

import android.util.Log;

import com.cnlab.caucse.chessteample.Position;
import com.cnlab.caucse.chessteample.Tile;

import java.util.ArrayList;

public class Pawn extends Piece {
    private String color;
    private boolean alive;
    private Position position;
    private boolean firstmove;

    public Pawn(String color, Position position){
        this.color = color;
        this.alive = true;
        this.position = position;
        this.firstmove = true;
    }
    @Override
    public void setPosition(Position pos) {
        position = pos;
    }
    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public boolean isFirstmove(){
        return firstmove;
    }

    public void setFirstmove(boolean use){
        this.firstmove = use;
    }

    @Override
    public ArrayList<Position> getCanMoves(Tile[][] btnex) {
        ArrayList<Position> canmove = new ArrayList<Position>();
        Position pos = new Position(this.position.getX(), this.position.getY());
        Position pos2 = new Position(this.position.getX(), this.position.getY());
        Position pos3 = new Position(this.position.getX(), this.position.getY());
        Position pos4 = new Position(this.position.getX(), this.position.getY());

        if(firstmove==true) { // 첫이동일 경우 두칸도 가능, 폰 move 시 firstmove 설정할 수 잇어야함.
            pos.setX(this.position.getX());
            pos.setY(this.position.getY() - 2);
            if (pos.isValid()) {
                if (btnex[pos.getX()][pos.getY()].getColor().equals("NONE")) {
                    canmove.add(pos);
                }
            }
        }

        /////////한칸 이동하는 경우
        pos2.setX(this.position.getX());
        pos2.setY(this.position.getY()-1);
        if(pos2.isValid()){
            if(btnex[pos2.getX()][pos2.getY()].getColor().equals("NONE")){
                canmove.add(pos2);
                Log.d("whysecond",Integer.toString(pos2.getX()));
                Log.d("whysecond",Integer.toString(pos2.getY()));
            }
        }

        for(int i=0;i<canmove.size();i++){
            Log.d("whycanmove",Integer.toString(canmove.get(i).getX()));
            Log.d("whycanmove",Integer.toString(canmove.get(i).getY()));
        }

        ////////// 다른 말을 먹는경우    좌상단

        pos3.setX(this.position.getX()-1);
        pos3.setY(this.position.getY()-1);
        if(pos3.isValid()){
            if(!btnex[pos3.getX()][pos3.getY()].getColor().equals("NONE") && !btnex[pos3.getX()][pos3.getY()].getColor().equals(this.color)){
                canmove.add(pos3);
            }
        }

        ////////// 다른 말을 먹는 경우 우상단

        pos4.setX(this.position.getX()+1);
        pos4.setY(this.position.getY()-1);
        if(pos.isValid()){
            if(!btnex[pos4.getX()][pos4.getY()].getColor().equals("NONE") && !btnex[pos4.getX()][pos4.getY()].getColor().equals(this.color)){
                canmove.add(pos4);
            }
        }

        return canmove;
    }
}
