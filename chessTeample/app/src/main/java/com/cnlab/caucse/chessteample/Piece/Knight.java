package com.cnlab.caucse.chessteample.Piece;

import com.cnlab.caucse.chessteample.Position;
import com.cnlab.caucse.chessteample.Tile;

import java.util.ArrayList;

public class Knight extends Piece {
    private String color;
    private boolean alive;
    private Position position;

    public Knight(String color, Position position){
        this.color = color;
        this.alive = true;
        this.position = position;
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

    @Override
    public ArrayList<Position> getCanMoves(Tile[][] btnex) {
        ArrayList<Position> canmove = new ArrayList<Position>();


        Position pos = this.position;

        pos.setX(this.position.getX()-2);
        pos.setY(this.position.getY()-1);
        if(pos.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos);
            }
        }

        pos.setX(this.position.getX()-2);
        pos.setY(this.position.getY()+1);
        if(pos.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos);
            }
        }

        pos.setX(this.position.getX()+2);
        pos.setY(this.position.getY()-1);
        if(pos.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos);
            }
        }

        pos.setX(this.position.getX()+2);
        pos.setY(this.position.getY()+1);
        if(pos.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos);
            }
        }
        pos.setX(this.position.getX()-1);
        pos.setY(this.position.getY()+2);
        if(pos.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos);
            }
        }
        pos.setX(this.position.getX()+1);
        pos.setY(this.position.getY()+2);
        if(pos.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos);
            }
        }
        pos.setX(this.position.getX()-1);
        pos.setY(this.position.getY()-2);
        if(pos.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos);
            }
        }
        pos.setX(this.position.getX()+1);
        pos.setY(this.position.getY()-2);
        if(pos.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos);
            }
        }
        return canmove;
    }
}
