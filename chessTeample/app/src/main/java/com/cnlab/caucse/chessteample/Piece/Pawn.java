package com.cnlab.caucse.chessteample.Piece;

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
        Position pos = this.position;
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

        pos.setX(this.position.getX());
        pos.setY(this.position.getY()-1);
            if(pos.isValid()){
                if(btnex[pos.getX()][pos.getY()].getColor().equals("NONE")){
                    canmove.add(pos);
                }
            }

        ////////// 다른 말을 먹는경우    좌상단

        pos.setX(this.position.getX()-1);
        pos.setY(this.position.getY()-1);
        if(pos.isValid()){
            if(!btnex[pos.getX()][pos.getY()].getColor().equals("NONE") && !btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){
                canmove.add(pos);
            }
        }

        ////////// 다른 말을 먹는 경우 우상단

        pos.setX(this.position.getX()+1);
        pos.setY(this.position.getY()-1);
        if(pos.isValid()){
            if(!btnex[pos.getX()][pos.getY()].getColor().equals("NONE") && !btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){
                canmove.add(pos);
            }
        }

        return canmove;
    }
}
