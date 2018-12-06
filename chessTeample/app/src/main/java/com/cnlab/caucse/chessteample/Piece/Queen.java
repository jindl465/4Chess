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

        Position[] pos = new Position[52];
        int count = 0;

        for(int i=0;i<52;i++){
            pos[i] = new Position(this.position.getX(),this.position.getY());
        }


        for(int plus = 1; plus <= 13; plus++){ //우상향
            pos[count].setX(this.position.getX()+plus);
            pos[count].setY(this.position.getY()-plus);
            if(pos[count].isValid()) {      //이동위치가 말판에 있ㄴ는가?
                if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")) { //빈칸인가?
                    canmove.add(pos[count]);
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 앞에 같은색이 걸리니?
                    break;
                } else if (!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 다른색이니?
                    canmove.add(pos[count]);
                    break;
                }
            }
        }
        for(int plus = 1; plus <= 13; plus++){ // 우하향
            pos[count].setX(this.position.getX()+plus);
            pos[count].setY(this.position.getY()+plus);
            if(pos[count].isValid()) {      //이동위치가 말판에 있ㄴ는가?
                if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")) { //빈칸인가?
                    canmove.add(pos[count]);
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 앞에 같은색이 걸리니?
                    break;
                } else if (!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 다른색이니?
                    canmove.add(pos[count]);
                    break;
                }
            }
        }

        for(int minus = 1; minus <=13; minus++){ // 좌상향
            pos[count].setX(this.position.getX()-minus);
            pos[count].setY(this.position.getY()-minus);
            if(pos[count].isValid()) {      //이동위치가 말판에 있ㄴ는가?
                if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")) { //빈칸인가?
                    canmove.add(pos[count]);
                    count++;
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 앞에 같은색이 걸리니?
                    break;
                } else if (!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 다른색이니?
                    canmove.add(pos[count]);
                    count++;
                    break;
                }
            }
        }

        for(int minus = 1; minus <= 13; minus++){ // 좌하향
            pos[count].setX(this.position.getX()-minus);
            pos[count].setY(this.position.getY()+minus);
            if(pos[count].isValid()) {      //이동위치가 말판에 있ㄴ는가?
                if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")) { //빈칸인가?
                    canmove.add(pos[count]);
                    count++;
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 앞에 같은색이 걸리니?
                    break;
                } else if (!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 다른색이니?
                    canmove.add(pos[count]);
                    count++;
                    break;
                }
            }
        }

        pos[count].setY(this.position.getY());
        for(int x = this.position.getX()+1; x <= 13; x++){  // 우향
            pos[count].setX(x);
            if(pos[count].isValid()) {      //이동위치가 말판에 있ㄴ는가?
                if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")) { //빈칸인가?
                    canmove.add(pos[count]);
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 앞에 같은색이 걸리니?
                    break;
                } else if (!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 다른색이니?
                    canmove.add(pos[count]);
                    break;
                }
            }
        }

        for(int x = this.position.getX()-1; x >= 0; x--){  // 좌향
            pos[count].setX(x);
            if(pos[count].isValid()){      //이동위치가 말판에 있ㄴ는가?
                if(btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")){ //빈칸인가?
                    canmove.add(pos[count]);
                }else if(btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)){ // 앞에 같은색이 걸리니?
                    break;
                }
                else if(!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)){ // 다른색이니?
                    canmove.add(pos[count]);
                    break;
                }
            }
        }

        pos[count].setX(this.position.getX());
        for(int y = this.position.getY()+1; y <= 13; y++){ // 하향
            pos[count].setY(y);
            if(pos[count].isValid()){      //이동위치가 말판에 있ㄴ는가?
                if(btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")){ //빈칸인가?
                    canmove.add(pos[count]);
                }else if(btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)){ // 앞에 같은색이 걸리니?
                    break;
                }
                else if(!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)){ // 다른색이니?
                    canmove.add(pos[count]);
                    break;
                }
            }
        }

        for(int y = this.position.getY()-1; y >= 0; y--){ // 상향
            pos[count].setY(y);
            if(pos[count].isValid()){      //이동위치가 말판에 있ㄴ는가?
                if(btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")){ //빈칸인가?
                    canmove.add(pos[count]);
                }else if(btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)){ // 앞에 같은색이 걸리니?
                    break;
                }
                else if(!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)){ // 다른색이니?
                    canmove.add(pos[count]);
                    break;
                }
            }
        }
        return canmove;
    }
}
