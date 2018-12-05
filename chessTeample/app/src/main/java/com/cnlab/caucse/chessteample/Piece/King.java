package com.cnlab.caucse.chessteample.Piece;

import com.cnlab.caucse.chessteample.Position;
import com.cnlab.caucse.chessteample.Tile;

import java.util.ArrayList;

public class King extends Piece {
    private String color;
    private boolean alive;
    private Position position;

    public King(String color, Position position){
        this.color = color;
        this.alive = true;
        this.position = position;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setPosition(Position pos) {
        position = pos;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public ArrayList<Position> getCanMoves(Tile[][] btnex) {
        ArrayList<Position> canmove = new ArrayList<Position>();
        Position pos = this.position;
        for(int x= this.position.getX() -1; x <= this.position.getX() +1; x++){
            for(int y = this.position.getY() -1; y <= this.position.getY() +1; y++){
                if(this.position.getX() != x && this.position.getY() !=y) { // 현재위치 체크
                    pos.setX(x);
                    pos.setY(y);
                    if (pos.isValid()) {  // 이동위치가 말판 안에 있는가?
                        if(!btnex[x][y].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                            canmove.add(pos);
                        }

                    }
                }
            }
        }
        return canmove;
    }
}
