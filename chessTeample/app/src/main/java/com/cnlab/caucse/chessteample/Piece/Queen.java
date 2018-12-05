package com.cnlab.caucse.chessteample.Piece;

import com.cnlab.caucse.chessteample.Position;
import com.cnlab.caucse.chessteample.Tile;

import java.util.ArrayList;

public class Queen extends Piece {
    private String color;
    private boolean alive;
    private Position position;

    public Queen(String color, Position position){
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


        for(int plus = 1; plus <= 13; plus++){ //우상향
            pos.setX(this.position.getX()+plus);
            pos.setY(this.position.getY()-plus);
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
        for(int plus = 1; plus <= 13; plus++){ // 우하향
            pos.setX(this.position.getX()+plus);
            pos.setY(this.position.getY()+plus);
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

        for(int minus = 13; minus >= 1; minus--){ // 좌상향
            pos.setX(this.position.getX()-minus);
            pos.setY(this.position.getY()-minus);
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

        for(int minus = 13; minus >= 1; minus--){ // 좌하향
            pos.setX(this.position.getX()-minus);
            pos.setY(this.position.getY()+minus);
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

        pos.setY(this.position.getY());
        for(int x = this.position.getX()+1; x <= 13; x++){  // 우향
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

        for(int x = this.position.getX()-1; x >= 0; x--){  // 좌향
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
        for(int y = this.position.getY()+1; y <= 13; y++){ // 하향
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

        for(int y = this.position.getY()-1; y >= 0; y--){ // 상향
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
