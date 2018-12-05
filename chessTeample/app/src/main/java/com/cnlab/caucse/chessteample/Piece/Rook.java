package com.cnlab.caucse.chessteample.Piece;

import com.cnlab.caucse.chessteample.Position;
import com.cnlab.caucse.chessteample.Tile;

import java.util.ArrayList;

public class Rook extends Piece {
    private String color;
    private boolean alive;
    private Position position;

    public Rook(String color, Position position){
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

        pos.setY(this.position.getY());
        for(int x = this.position.getX()+1; x <= 13; x++){
            pos.setX(x);
                if(pos.isValid()) {      //이동위치가 말판에 있ㄴ는가?
                    if (btnex[pos.getX()][pos.getY()].getColor().equals("NONE")) { //빈칸인가?
                        canmove.add(pos);
                    } else if (btnex[pos.getX()][pos.getY()].getColor().equals(this.color)) { // 앞에 같은색이 걸리니?
                        break;
                    } else if (!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)) { // 다른색이니?
                        canmove.add(pos);
                        break;
                    }
                }
        }

        for(int x = this.position.getX()-1; x >= 0; x--){
            pos.setX(x);
                if(pos.isValid()){      //이동위치가 말판에 있ㄴ는가?
                    if(btnex[pos.getX()][pos.getY()].getColor().equals("NONE")){ //빈칸인가?
                        canmove.add(pos);
                    }else if(btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 앞에 같은색이 걸리니?
                        break;
                    }
                    else if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 다른색이니?
                        canmove.add(pos);
                        break;
                    }
                }
        }

        pos.setX(this.position.getX());
        for(int y = this.position.getY()+1; y <= 13; y++){
            pos.setY(y);
                if(pos.isValid()){      //이동위치가 말판에 있ㄴ는가?
                    if(btnex[pos.getX()][pos.getY()].getColor().equals("NONE")){ //빈칸인가?
                        canmove.add(pos);
                    }else if(btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 앞에 같은색이 걸리니?
                        break;
                    }
                    else if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 다른색이니?
                        canmove.add(pos);
                        break;
                    }
                }
        }

        for(int y = this.position.getY()-1; y >= 0; y--){
            pos.setY(y);
                if(pos.isValid()){      //이동위치가 말판에 있ㄴ는가?
                    if(btnex[pos.getX()][pos.getY()].getColor().equals("NONE")){ //빈칸인가?
                        canmove.add(pos);
                    }else if(btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 앞에 같은색이 걸리니?
                        break;
                    }
                    else if(!btnex[pos.getX()][pos.getY()].getColor().equals(this.color)){ // 다른색이니?
                        canmove.add(pos);
                        break;
                    }
                }
        }


        return canmove;
    }
}
