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
        Position pos1 = new Position(this.position.getX(), this.position.getY());
        Position pos2 = new Position(this.position.getX(), this.position.getY());
        Position pos3 = new Position(this.position.getX(), this.position.getY());
        Position pos4 = new Position(this.position.getX(), this.position.getY());
        Position pos5 = new Position(this.position.getX(), this.position.getY());
        Position pos6 = new Position(this.position.getX(), this.position.getY());
        Position pos7 = new Position(this.position.getX(), this.position.getY());
        Position pos8 = new Position(this.position.getX(), this.position.getY());


        pos1.setX(this.position.getX()-2);
        pos1.setY(this.position.getY()-1);
        if(pos1.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos1.getX()][pos1.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos1);
            }
        }

        pos2.setX(this.position.getX()-2);
        pos2.setY(this.position.getY()+1);
        if(pos2.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos2.getX()][pos2.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos2);
            }
        }

        pos3.setX(this.position.getX()+2);
        pos3.setY(this.position.getY()-1);
        if(pos3.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos3.getX()][pos3.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos3);
            }
        }

        pos4.setX(this.position.getX()+2);
        pos4.setY(this.position.getY()+1);
        if(pos4.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos4.getX()][pos4.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos4);
            }
        }
        pos5.setX(this.position.getX()-1);
        pos5.setY(this.position.getY()+2);
        if(pos5.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos5.getX()][pos5.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos5);
            }
        }
        pos6.setX(this.position.getX()+1);
        pos6.setY(this.position.getY()+2);
        if(pos6.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos6.getX()][pos6.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos6);
            }
        }
        pos7.setX(this.position.getX()-1);
        pos7.setY(this.position.getY()-2);
        if(pos7.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos7.getX()][pos7.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos7);
            }
        }
        pos8.setX(this.position.getX()+1);
        pos8.setY(this.position.getY()-2);
        if(pos8.isValid()){ // 이동위치가 말판 안에 있는가?
            if(!btnex[pos8.getX()][pos8.getY()].getColor().equals(this.color)){ // 같은색이면 이동못해, 다른색이면 이동가능!
                canmove.add(pos8);
            }
        }
        return canmove;
    }
}
