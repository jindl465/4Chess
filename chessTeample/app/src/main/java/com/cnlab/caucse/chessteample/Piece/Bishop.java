package com.cnlab.caucse.chessteample.Piece;

import com.cnlab.caucse.chessteample.Position;
import com.cnlab.caucse.chessteample.Tile;

import java.util.ArrayList;

public class Bishop extends Piece {
    private String color;
    private boolean alive;
    private Position position;
    private String teamcolor;

    public Bishop(String color, Position position){
        this.color = color;
        this.alive = true;
        this.position = position;
        if(color.equals("WHITE")){
            teamcolor = "BLACK";
        }
        if(color.equals("BLACK")){
            teamcolor = "WHITE";
        }
        if(color.equals("RED")){
            teamcolor = "GREEN";
        }
        if(color.equals("GREEN")){
            teamcolor = "RED";
        }
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
        Position[] pos = new Position[26];
        int count = 0;

        for(int i=0;i<26;i++){
            pos[i] = new Position(this.position.getX(), this.position.getY());
        }

        for(int plus = 1; plus <= 13; plus++){ //우상향
            pos[count].setX(this.position.getX()+plus);
            pos[count].setY(this.position.getY()-plus);
            if(pos[count].isValid()) {      //이동위치가 말판에 있는가?
                if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals("NONE")) { //빈칸인가?
                    canmove.add(pos[count]);
                    count++;
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color) || btnex[pos[count].getX()][pos[count].getY()].getColor().equals(teamcolor)) { // 앞에 같은색이 걸리니?
                    break;
                } else if (!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 다른색이니?
                    canmove.add(pos[count]);
                    count++;
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
                    count++;
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color) || btnex[pos[count].getX()][pos[count].getY()].getColor().equals(teamcolor)) { // 앞에 같은색이 걸리니?
                    break;
                } else if (!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 다른색이니?
                    canmove.add(pos[count]);
                    count++;
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
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color) || btnex[pos[count].getX()][pos[count].getY()].getColor().equals(teamcolor)) { // 앞에 같은색이 걸리니?
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
                } else if (btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color) || btnex[pos[count].getX()][pos[count].getY()].getColor().equals(teamcolor)) { // 앞에 같은색이 걸리니?
                    break;
                } else if (!btnex[pos[count].getX()][pos[count].getY()].getColor().equals(this.color)) { // 다른색이니?
                    canmove.add(pos[count]);
                    count++;
                    break;
                }
            }
        }

        return canmove;
    }
}
