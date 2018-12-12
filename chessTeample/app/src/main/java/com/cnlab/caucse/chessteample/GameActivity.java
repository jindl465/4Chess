package com.cnlab.caucse.chessteample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cnlab.caucse.chessteample.Piece.Bishop;
import com.cnlab.caucse.chessteample.Piece.King;
import com.cnlab.caucse.chessteample.Piece.Knight;
import com.cnlab.caucse.chessteample.Piece.Pawn;
import com.cnlab.caucse.chessteample.Piece.Queen;
import com.cnlab.caucse.chessteample.Piece.Rook;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity{
    private ImageButton btn[][] = new ImageButton[14][14];
    private Tile btnEx[][] = new Tile[14][14];
    public static Context context;
    private String mycolor;
    private String teamcolor;
    private String p1_color;
    private String p2_color;
    private String p3_color;
    private String p4_color;
    private TextView checkid;
    private boolean flag;
    private ArrayList<Position> cantouch;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        mycolor = getIntent().getStringExtra("playerColor");
        if(mycolor.equals("BLACK")){
            teamcolor = "WHITE";
        }
        if(mycolor.equals("WHITE")){
            teamcolor = "BLACK";
        }
        if(mycolor.equals("RED")){
            teamcolor = "GREEN";
        }
        if(mycolor.equals("GREEN")){
            teamcolor = "RED";
        }
        flag = false;
        p1_color = "BLACK";
        p2_color = "WHITE";
        p3_color = "RED";
        p4_color = "GREEN";
        // Example of a call to a native method
 //       TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        setButtonID();
        setStartTile(p1_color);
        setbcl();


        context = this;
    }

    public Tile[][] getBtnEx() {
        return btnEx;
    }




    public boolean checkcheck(){
        for(int i = 0; i <=13; i ++){
            for(int j = 0; j <=13; j++){
                if(btnEx[i][j].isOnPiece()==true){
                    for(int z = 0; z<btnEx[i][j].getPiece().getCanMoves(btnEx).size(); z++){
                        if(btnEx[btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getX()][btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getY()].getPiecetype().equals("KING")){
                            String color = btnEx[btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getX()][btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getY()].getColor();
                            color = color + " Check";
                            checkid.setText(color);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkcheckmate(String mycolor){
        for(int i = 0; i <=13; i ++){
            for(int j = 0; j <=13; j++){
                if(btnEx[i][j].isOnPiece()==true){
                    for(int z = 0; z<btnEx[i][j].getPiece().getCanMoves(btnEx).size(); z++){
                        if(btnEx[btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getX()][btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getY()].getPiecetype().equals("KING")){
                          //  if(btnEx[btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getX()][btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getY()].getPiece().getCanMoves(btnEx).size()==0){
                                for(int a = 0 ; a < 14; a++) {
                                    for (int b = 0; b < 14; b++) {
                                        if(btnEx[a][b].getColor().equals(mycolor)){
                                            for(int c =0 ; c<btnEx[a][b].getPiece().getCanMoves(btnEx).size();c++){
                                                String temp = btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].getColor();
                                                btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].setColor(mycolor);
                                                if(btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].getPiecetype().equals("KING")){
                                                    btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].setColor(temp);
                                                    return true;
                                                }
                                                else if(checkcheck() ==false){
                                                    btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].setColor(temp);
                                                    return false;
                                                }
                                                btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].setColor(temp);
                                            }
                                        }
                                    }
                                }
                         //   }
                            //String color = btnEx[btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getX()][btnEx[i][j].getPiece().getCanMoves(btnEx).get(z).getY()].getColor();
                            //color = color + " Check";
                            //checkid.setText(color);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean checkstalemate(String mycolor){
        if(checkcheck()!=true){
            for(int a = 0 ; a < 14; a++) {
                for (int b = 0; b < 14; b++) {
                    if(btnEx[a][b].getColor().equals(mycolor)){
                        for(int c =0 ; c<btnEx[a][b].getPiece().getCanMoves(btnEx).size();c++){
                            String temp = btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].getColor();
                            btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].setColor(mycolor);
                            if(checkcheck() ==false){
                                btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].setColor(temp);
                                return false;
                            }
                            btnEx[btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getX()][btnEx[a][b].getPiece().getCanMoves(btnEx).get(c).getY()].setColor(temp);
                        }
                    }
                }
            }
            return true;
        }

        return false;
    }

    public void setbcl(){
        int i=0; int j=0;
        for(i = 0 ; i <=13; i++){
            for (j = 0 ; j <=13; j++){
                final int finalI = i;
                final int finalJ = j;
                btn[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(flag == false){
                            if(btnEx[finalI][finalJ].isOnPiece() == true && btnEx[finalI][finalJ].getColor().equals(mycolor)){
                                flag = true;
                                btnEx[finalI][finalJ].setActive(true);
                              //  Log.d("why1",Integer.toString(finalI));
                              //  Log.d("why1",Integer.toString(finalJ));
                                cantouch = new ArrayList<Position>(btnEx[finalI][finalJ].getPiece().getCanMoves(btnEx));
                              //  Log.d("why2",Integer.toString(btnEx[finalI][finalJ].getPiece().getPosition().getX()));
                              //  Log.d("why2",Integer.toString(btnEx[finalI][finalJ].getPiece().getPosition().getY()));
                                for(int q=0; q<cantouch.size(); q++){
                                    btn[cantouch.get(q).getX()][cantouch.get(q).getY()].setBackgroundResource(R.drawable.buttonshapered);
                                }
                            }
                        }
                        else if(flag == true){
                            Log.d("why", Integer.toString(finalI));
                            Log.d("why", Integer.toString(finalJ));
                            int findx = 0, findy = 0;
                            int mflag = 0;
                            for(int k = 0 ; k < cantouch.size(); k++) {
                                Log.d("why@@", Integer.toString(cantouch.get(k).getX()));
                                Log.d("why@@",Integer.toString(cantouch.get(k).getY()));
                                if (cantouch.get(k).getX() == finalI && cantouch.get(k).getY() == finalJ){
                                    Log.d("why", "ok");
                                    for(findx = 0 ; findx<=13; findx++){
                                        for(findy = 0; findy <=13; findy++){
                                            if(btnEx[findx][findy].getActive() ==true){
                                                btnEx[findx][findy].setActive(false);
                                                if(btnEx[findx][findy].getPiecetype()=="PAWN"){
                                                    if((findy-finalJ)==2){
                                                        Log.d("whyeee","ok");
                                                        btnEx[findx][findy].getPiece().setFirstmove(false);
                                                    }
                                                }
                                                mflag = 1;
                                                break;
                                            }
                                        }
                                        if(mflag==1) break;
                                    }
                                    btnEx[finalI][finalJ].setColor(btnEx[findx][findy].getColor());
                                    btnEx[finalI][finalJ].setOnPiece(true);
                                    Log.d("why1",btnEx[finalI][finalJ].getColor());

                                    btnEx[findx][findy].getPiece().setPosition(new Position(finalI,finalJ));

                                    btnEx[finalI][finalJ].setPiecetype(btnEx[findx][findy].getPiecetype());
                                    Log.d("why1",btnEx[finalI][finalJ].getPiecetype());
                                    btnEx[finalI][finalJ].setPiece(btnEx[findx][findy].getPiece());
                                    btnEx[findx][findy].setPiece(null);
                                    btnEx[findx][findy].setPiecetype("NONE");
                                    btnEx[findx][findy].setColor("NONE");
                                    btnEx[findx][findy].setOnPiece(false);
                                    btn[findx][findy].setImageResource(0);

                                    if(btnEx[finalI][finalJ].getColor().equals("BLACK")) {
                                        if(btnEx[finalI][finalJ].getPiecetype()=="ROOK")
                                        btn[finalI][finalJ].setImageResource(R.mipmap.rook_black);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="KNIGHT")
                                        btn[finalI][finalJ].setImageResource(R.mipmap.knight_black);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="BISHOP")
                                        btn[finalI][finalJ].setImageResource(R.mipmap.bishop_black);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="KING")
                                        btn[finalI][finalJ].setImageResource(R.mipmap.king_black);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="QUEEN")
                                        btn[finalI][finalJ].setImageResource(R.mipmap.queen_black);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="PAWN")
                                        btn[finalI][finalJ].setImageResource(R.mipmap.pawn_black);
                                    }

                                    if(btnEx[finalI][finalJ].getColor().equals("WHITE")) {
                                        if(btnEx[finalI][finalJ].getPiecetype()=="ROOK")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.rook_white);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="KNIGHT")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.knight_white);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="BISHOP")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.bishop_white);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="KING")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.king_white);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="QUEEN")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.queen_white);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="PAWN")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.pawn_white);
                                    }

                                    if(btnEx[finalI][finalJ].getColor().equals("RED")) {
                                        if(btnEx[finalI][finalJ].getPiecetype()=="ROOK")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.rook_red);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="KNIGHT")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.knight_red);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="BISHOP")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.bishop_red);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="KING")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.king_red);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="QUEEN")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.queen_red);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="PAWN")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.pawn_red);
                                    }

                                    if(btnEx[finalI][finalJ].getColor().equals("GREEN")) {
                                        if(btnEx[finalI][finalJ].getPiecetype()=="ROOK")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.rook_green);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="KNIGHT")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.knight_green);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="BISHOP")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.bishop_green);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="KING")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.king_green);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="QUEEN")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.queen_green);

                                        if(btnEx[finalI][finalJ].getPiecetype()=="PAWN")
                                            btn[finalI][finalJ].setImageResource(R.mipmap.pawn_green);

                                    }
                                    checkcheck();
                                }

                            }
                            for(findx = 0 ; findx<=13; findx++){
                                for(findy = 0; findy <=13; findy++){
                                    if(btnEx[findx][findy].getActive() ==true){
                                        btnEx[findx][findy].setActive(false);
                                        break;
                                    }
                                }
                            }
                            for(int q=0; q<cantouch.size(); q++){
                                if((cantouch.get(q).getX()+cantouch.get(q).getY())%2 == 1){
                                    btn[cantouch.get(q).getX()][cantouch.get(q).getY()].setBackgroundResource(R.drawable.buttonshapegray);
                                }
                                else{
                                    btn[cantouch.get(q).getX()][cantouch.get(q).getY()].setBackgroundResource(R.drawable.buttonshapewhite);
                                }
                            }
                            flag = false;
                        }
                    }
                });

            }
        }

    }

    public void Movelistener(){
        String listencolor = "NONE";
        int onex= 9;
        int oney = 9;
        int twox=9;
        int twoy=9;

        if(mycolor.equals("WHITE")){
            if(listencolor.equals("GREEN")){
                int temp = onex;
                onex = oney;
                oney = 13-temp;
                int temp2 = twox;
                twox = twoy;
                twoy = 13-temp;
            }
            if(listencolor.equals("RED")){
                int temp = onex;
                onex = 13- oney;
                oney = temp;
                int temp2 = twox;
                twox = 13 - twoy;
                twoy = temp;
            }
            if(listencolor.equals("BLACK")){
                onex= 13-onex;
                oney= 13-oney;
                twox=13-twox;
                twoy=13-twoy;
            }
        }
        if(mycolor.equals("BLACK")){
            if(listencolor.equals("RED")){
                int temp = onex;
                onex = oney;
                oney = 13-temp;
                int temp2 = twox;
                twox = twoy;
                twoy = 13-temp;
            }
            if(listencolor.equals("GREEN")){
                int temp = onex;
                onex = 13- oney;
                oney = temp;
                int temp2 = twox;
                twox = 13 - twoy;
                twoy = temp;
            }
            if(listencolor.equals("WHITE")){
                onex= 13-onex;
                oney= 13-oney;
                twox=13-twox;
                twoy=13-twoy;
            }
        }
        if(mycolor.equals("RED")){
            if(listencolor.equals("WHITE")){
                int temp = onex;
                onex = oney;
                oney = 13-temp;
                int temp2 = twox;
                twox = twoy;
                twoy = 13-temp;
            }
            if(listencolor.equals("BLACK")){
                int temp = onex;
                onex = 13- oney;
                oney = temp;
                int temp2 = twox;
                twox = 13 - twoy;
                twoy = temp;
            }
            if(listencolor.equals("GREEN")){
                onex= 13-onex;
                oney= 13-oney;
                twox=13-twox;
                twoy=13-twoy;
            }
        }
        if(mycolor.equals("GREEN")){
            if(listencolor.equals("BLACK")){
                int temp = onex;
                onex = oney;
                oney = 13-temp;
                int temp2 = twox;
                twox = twoy;
                twoy = 13-temp;
            }
            if(listencolor.equals("WHITE")){
                int temp = onex;
                onex = 13- oney;
                oney = temp;
                int temp2 = twox;
                twox = 13 - twoy;
                twoy = temp;
            }
            if(listencolor.equals("RED")){
                onex= 13-onex;
                oney= 13-oney;
                twox=13-twox;
                twoy=13-twoy;
            }
        }

        btnEx[twox][twoy].setColor(btnEx[onex][oney].getColor());
        btnEx[twox][twoy].setOnPiece(true);
        btnEx[twox][twoy].setPiecetype(btnEx[onex][oney].getPiecetype());
        Position pos = new Position(twox,twoy);
        btnEx[onex][oney].getPiece().setPosition(pos);
        btnEx[twox][twoy].setPiece(btnEx[onex][oney].getPiece());
        btnEx[onex][oney].setPiecetype("NONE");
        btnEx[onex][oney].setOnPiece(false);
        btnEx[onex][oney].setPiece(null);
        btnEx[onex][oney].setColor("NONE");
    }


    public void setButtonID(){
        checkid = (TextView)findViewById(R.id.checkId);

        btn[0][0] = (ImageButton)findViewById(R.id.board00);
        btn[1][0] = (ImageButton)findViewById(R.id.board01);
        btn[2][0] = (ImageButton)findViewById(R.id.board02);
        btn[3][0] = (ImageButton)findViewById(R.id.board03);
        btn[4][0] = (ImageButton)findViewById(R.id.board04);
        btn[5][0] = (ImageButton)findViewById(R.id.board05);
        btn[6][0] = (ImageButton)findViewById(R.id.board06);
        btn[7][0] = (ImageButton)findViewById(R.id.board07);
        btn[8][0] = (ImageButton)findViewById(R.id.board08);
        btn[9][0] = (ImageButton)findViewById(R.id.board09);
        btn[10][0] = (ImageButton)findViewById(R.id.board010);
        btn[11][0] = (ImageButton)findViewById(R.id.board011);
        btn[12][0] = (ImageButton)findViewById(R.id.board012);
        btn[13][0] = (ImageButton)findViewById(R.id.board013);

        btn[0][1] = (ImageButton)findViewById(R.id.board10);
        btn[1][1] = (ImageButton)findViewById(R.id.board11);
        btn[2][1] = (ImageButton)findViewById(R.id.board12);
        btn[3][1] = (ImageButton)findViewById(R.id.board13);
        btn[4][1] = (ImageButton)findViewById(R.id.board14);
        btn[5][1] = (ImageButton)findViewById(R.id.board15);
        btn[6][1] = (ImageButton)findViewById(R.id.board16);
        btn[7][1] = (ImageButton)findViewById(R.id.board17);
        btn[8][1] = (ImageButton)findViewById(R.id.board18);
        btn[9][1] = (ImageButton)findViewById(R.id.board19);
        btn[10][1] = (ImageButton)findViewById(R.id.board110);
        btn[11][1] = (ImageButton)findViewById(R.id.board111);
        btn[12][1] = (ImageButton)findViewById(R.id.board112);
        btn[13][1] = (ImageButton)findViewById(R.id.board113);

        btn[0][2] = (ImageButton)findViewById(R.id.board20);
        btn[1][2] = (ImageButton)findViewById(R.id.board21);
        btn[2][2] = (ImageButton)findViewById(R.id.board22);
        btn[3][2] = (ImageButton)findViewById(R.id.board23);
        btn[4][2] = (ImageButton)findViewById(R.id.board24);
        btn[5][2] = (ImageButton)findViewById(R.id.board25);
        btn[6][2] = (ImageButton)findViewById(R.id.board26);
        btn[7][2] = (ImageButton)findViewById(R.id.board27);
        btn[8][2] = (ImageButton)findViewById(R.id.board28);
        btn[9][2] = (ImageButton)findViewById(R.id.board29);
        btn[10][2] = (ImageButton)findViewById(R.id.board210);
        btn[11][2] = (ImageButton)findViewById(R.id.board211);
        btn[12][2] = (ImageButton)findViewById(R.id.board212);
        btn[13][2] = (ImageButton)findViewById(R.id.board213);

        btn[0][3] = (ImageButton)findViewById(R.id.board30);
        btn[1][3] = (ImageButton)findViewById(R.id.board31);
        btn[2][3] = (ImageButton)findViewById(R.id.board32);
        btn[3][3] = (ImageButton)findViewById(R.id.board33);
        btn[4][3] = (ImageButton)findViewById(R.id.board34);
        btn[5][3] = (ImageButton)findViewById(R.id.board35);
        btn[6][3] = (ImageButton)findViewById(R.id.board36);
        btn[7][3] = (ImageButton)findViewById(R.id.board37);
        btn[8][3] = (ImageButton)findViewById(R.id.board38);
        btn[9][3] = (ImageButton)findViewById(R.id.board39);
        btn[10][3] = (ImageButton)findViewById(R.id.board310);
        btn[11][3] = (ImageButton)findViewById(R.id.board311);
        btn[12][3] = (ImageButton)findViewById(R.id.board312);
        btn[13][3] = (ImageButton)findViewById(R.id.board313);

        btn[0][4] = (ImageButton)findViewById(R.id.board40);
        btn[1][4] = (ImageButton)findViewById(R.id.board41);
        btn[2][4] = (ImageButton)findViewById(R.id.board42);
        btn[3][4] = (ImageButton)findViewById(R.id.board43);
        btn[4][4] = (ImageButton)findViewById(R.id.board44);
        btn[5][4] = (ImageButton)findViewById(R.id.board45);
        btn[6][4] = (ImageButton)findViewById(R.id.board46);
        btn[7][4] = (ImageButton)findViewById(R.id.board47);
        btn[8][4] = (ImageButton)findViewById(R.id.board48);
        btn[9][4] = (ImageButton)findViewById(R.id.board49);
        btn[10][4] = (ImageButton)findViewById(R.id.board410);
        btn[11][4] = (ImageButton)findViewById(R.id.board411);
        btn[12][4] = (ImageButton)findViewById(R.id.board412);
        btn[13][4] = (ImageButton)findViewById(R.id.board413);

        btn[0][5] = (ImageButton)findViewById(R.id.board50);
        btn[1][5] = (ImageButton)findViewById(R.id.board51);
        btn[2][5] = (ImageButton)findViewById(R.id.board52);
        btn[3][5] = (ImageButton)findViewById(R.id.board53);
        btn[4][5] = (ImageButton)findViewById(R.id.board54);
        btn[5][5] = (ImageButton)findViewById(R.id.board55);
        btn[6][5] = (ImageButton)findViewById(R.id.board56);
        btn[7][5] = (ImageButton)findViewById(R.id.board57);
        btn[8][5] = (ImageButton)findViewById(R.id.board58);
        btn[9][5] = (ImageButton)findViewById(R.id.board59);
        btn[10][5] = (ImageButton)findViewById(R.id.board510);
        btn[11][5] = (ImageButton)findViewById(R.id.board511);
        btn[12][5] = (ImageButton)findViewById(R.id.board512);
        btn[13][5] = (ImageButton)findViewById(R.id.board513);

        btn[0][6] = (ImageButton)findViewById(R.id.board60);
        btn[1][6] = (ImageButton)findViewById(R.id.board61);
        btn[2][6] = (ImageButton)findViewById(R.id.board62);
        btn[3][6] = (ImageButton)findViewById(R.id.board63);
        btn[4][6] = (ImageButton)findViewById(R.id.board64);
        btn[5][6] = (ImageButton)findViewById(R.id.board65);
        btn[6][6] = (ImageButton)findViewById(R.id.board66);
        btn[7][6] = (ImageButton)findViewById(R.id.board67);
        btn[8][6] = (ImageButton)findViewById(R.id.board68);
        btn[9][6] = (ImageButton)findViewById(R.id.board69);
        btn[10][6] = (ImageButton)findViewById(R.id.board610);
        btn[11][6] = (ImageButton)findViewById(R.id.board611);
        btn[12][6] = (ImageButton)findViewById(R.id.board612);
        btn[13][6] = (ImageButton)findViewById(R.id.board613);

        btn[0][7] = (ImageButton)findViewById(R.id.board70);
        btn[1][7] = (ImageButton)findViewById(R.id.board71);
        btn[2][7] = (ImageButton)findViewById(R.id.board72);
        btn[3][7] = (ImageButton)findViewById(R.id.board73);
        btn[4][7] = (ImageButton)findViewById(R.id.board74);
        btn[5][7] = (ImageButton)findViewById(R.id.board75);
        btn[6][7] = (ImageButton)findViewById(R.id.board76);
        btn[7][7] = (ImageButton)findViewById(R.id.board77);
        btn[8][7] = (ImageButton)findViewById(R.id.board78);
        btn[9][7] = (ImageButton)findViewById(R.id.board79);
        btn[10][7] = (ImageButton)findViewById(R.id.board710);
        btn[11][7] = (ImageButton)findViewById(R.id.board711);
        btn[12][7] = (ImageButton)findViewById(R.id.board712);
        btn[13][7] = (ImageButton)findViewById(R.id.board713);

        btn[0][8] = (ImageButton)findViewById(R.id.board80);
        btn[1][8] = (ImageButton)findViewById(R.id.board81);
        btn[2][8] = (ImageButton)findViewById(R.id.board82);
        btn[3][8] = (ImageButton)findViewById(R.id.board83);
        btn[4][8] = (ImageButton)findViewById(R.id.board84);
        btn[5][8] = (ImageButton)findViewById(R.id.board85);
        btn[6][8] = (ImageButton)findViewById(R.id.board86);
        btn[7][8] = (ImageButton)findViewById(R.id.board87);
        btn[8][8] = (ImageButton)findViewById(R.id.board88);
        btn[9][8] = (ImageButton)findViewById(R.id.board89);
        btn[10][8] = (ImageButton)findViewById(R.id.board810);
        btn[11][8] = (ImageButton)findViewById(R.id.board811);
        btn[12][8] = (ImageButton)findViewById(R.id.board812);
        btn[13][8] = (ImageButton)findViewById(R.id.board813);

        btn[0][9] = (ImageButton)findViewById(R.id.board90);
        btn[1][9] = (ImageButton)findViewById(R.id.board91);
        btn[2][9] = (ImageButton)findViewById(R.id.board92);
        btn[3][9] = (ImageButton)findViewById(R.id.board93);
        btn[4][9] = (ImageButton)findViewById(R.id.board94);
        btn[5][9] = (ImageButton)findViewById(R.id.board95);
        btn[6][9] = (ImageButton)findViewById(R.id.board96);
        btn[7][9] = (ImageButton)findViewById(R.id.board97);
        btn[8][9] = (ImageButton)findViewById(R.id.board98);
        btn[9][9] = (ImageButton)findViewById(R.id.board99);
        btn[10][9] = (ImageButton)findViewById(R.id.board910);
        btn[11][9] = (ImageButton)findViewById(R.id.board911);
        btn[12][9] = (ImageButton)findViewById(R.id.board912);
        btn[13][9] = (ImageButton)findViewById(R.id.board913);

        btn[0][10] = (ImageButton)findViewById(R.id.board100);
        btn[1][10] = (ImageButton)findViewById(R.id.board101);
        btn[2][10] = (ImageButton)findViewById(R.id.board102);
        btn[3][10] = (ImageButton)findViewById(R.id.board103);
        btn[4][10] = (ImageButton)findViewById(R.id.board104);
        btn[5][10] = (ImageButton)findViewById(R.id.board105);
        btn[6][10] = (ImageButton)findViewById(R.id.board106);
        btn[7][10] = (ImageButton)findViewById(R.id.board107);
        btn[8][10] = (ImageButton)findViewById(R.id.board108);
        btn[9][10] = (ImageButton)findViewById(R.id.board109);
        btn[10][10] = (ImageButton)findViewById(R.id.board1010);
        btn[11][10] = (ImageButton)findViewById(R.id.board1011);
        btn[12][10] = (ImageButton)findViewById(R.id.board1012);
        btn[13][10] = (ImageButton)findViewById(R.id.board1013);

        btn[0][11] = (ImageButton)findViewById(R.id.board11_0);
        btn[1][11] = (ImageButton)findViewById(R.id.board11_1);
        btn[2][11] = (ImageButton)findViewById(R.id.board11_2);
        btn[3][11] = (ImageButton)findViewById(R.id.board11_3);
        btn[4][11] = (ImageButton)findViewById(R.id.board114);
        btn[5][11] = (ImageButton)findViewById(R.id.board115);
        btn[6][11] = (ImageButton)findViewById(R.id.board116);
        btn[7][11] = (ImageButton)findViewById(R.id.board117);
        btn[8][11] = (ImageButton)findViewById(R.id.board118);
        btn[9][11] = (ImageButton)findViewById(R.id.board119);
        btn[10][11] = (ImageButton)findViewById(R.id.board1110);
        btn[11][11] = (ImageButton)findViewById(R.id.board1111);
        btn[12][11] = (ImageButton)findViewById(R.id.board1112);
        btn[13][11] = (ImageButton)findViewById(R.id.board1113);

        btn[0][12] = (ImageButton)findViewById(R.id.board120);
        btn[1][12] = (ImageButton)findViewById(R.id.board121);
        btn[2][12] = (ImageButton)findViewById(R.id.board122);
        btn[3][12] = (ImageButton)findViewById(R.id.board123);
        btn[4][12] = (ImageButton)findViewById(R.id.board124);
        btn[5][12] = (ImageButton)findViewById(R.id.board125);
        btn[6][12] = (ImageButton)findViewById(R.id.board126);
        btn[7][12] = (ImageButton)findViewById(R.id.board127);
        btn[8][12] = (ImageButton)findViewById(R.id.board128);
        btn[9][12] = (ImageButton)findViewById(R.id.board129);
        btn[10][12] = (ImageButton)findViewById(R.id.board1210);
        btn[11][12] = (ImageButton)findViewById(R.id.board1211);
        btn[12][12] = (ImageButton)findViewById(R.id.board1212);
        btn[13][12] = (ImageButton)findViewById(R.id.board1213);

        btn[0][13] = (ImageButton)findViewById(R.id.board130);
        btn[1][13] = (ImageButton)findViewById(R.id.board131);
        btn[2][13] = (ImageButton)findViewById(R.id.board132);
        btn[3][13] = (ImageButton)findViewById(R.id.board133);
        btn[4][13] = (ImageButton)findViewById(R.id.board134);
        btn[5][13] = (ImageButton)findViewById(R.id.board135);
        btn[6][13] = (ImageButton)findViewById(R.id.board136);
        btn[7][13] = (ImageButton)findViewById(R.id.board137);
        btn[8][13] = (ImageButton)findViewById(R.id.board138);
        btn[9][13] = (ImageButton)findViewById(R.id.board139);
        btn[10][13] = (ImageButton)findViewById(R.id.board1310);
        btn[11][13] = (ImageButton)findViewById(R.id.board1311);
        btn[12][13] = (ImageButton)findViewById(R.id.board1312);
        btn[13][13] = (ImageButton)findViewById(R.id.board1313);
    }

    public void setStartTile(String Player){
        for(int i = 0; i <=13; i++) {
            for(int j = 0 ; j <= 13; j++){
                btnEx[i][j] = new Tile();
            }
        }
    if(mycolor.equals("WHITE")){
        btn[3][0].setImageResource(R.mipmap.rook_black);
        btnEx[3][0] = new Tile("ROOK", "BLACK", new Position(3,0));

        btn[4][0].setImageResource(R.mipmap.knight_black);
        btnEx[4][0] = new Tile("KNIGHT", "BLACK", new Position(4,0));
        btn[5][0].setImageResource(R.mipmap.bishop_black);
        btnEx[5][0] = new Tile("BISHOP", "BLACK", new Position(5,0));
        btn[6][0].setImageResource(R.mipmap.king_black);
        btnEx[6][0] = new Tile("KING", "BLACK", new Position(6,0));
        btn[7][0].setImageResource(R.mipmap.queen_black);
        btnEx[7][0] = new Tile("QUEEN", "BLACK", new Position(7,0));
        btn[8][0].setImageResource(R.mipmap.bishop_black);
        btnEx[8][0] = new Tile("BISHOP", "BLACK", new Position(8,0));
        btn[9][0].setImageResource(R.mipmap.knight_black);
        btnEx[9][0] = new Tile("KNIGHT", "BLACK", new Position(9,0));
        btn[10][0].setImageResource(R.mipmap.rook_black);
        btnEx[10][0] = new Tile("ROOK", "BLACK", new Position(10,0));

        for(int i = 3; i < 11; i++){
            btn[i][1].setImageResource(R.mipmap.pawn_black);
            btnEx[i][1] = new Tile("PAWN", "BLACK", new Position(i,1));
        }


        btn[3][13].setImageResource(R.mipmap.rook_white);
        btnEx[3][13] = new Tile("ROOK", "WHITE", new Position(3,13));
        btn[4][13].setImageResource(R.mipmap.knight_white);
        btnEx[4][13] = new Tile("KNIGHT", "WHITE", new Position(4,13));
        btn[5][13].setImageResource(R.mipmap.bishop_white);
        btnEx[5][13] = new Tile("BISHOP", "WHITE", new Position(5,13));
        btn[6][13].setImageResource(R.mipmap.queen_white);
        btnEx[6][13] = new Tile("QUEEN", "WHITE", new Position(6,13));
        btn[7][13].setImageResource(R.mipmap.king_white);
        btnEx[7][13] = new Tile("KING", "WHITE", new Position(7,13));
        btn[8][13].setImageResource(R.mipmap.bishop_white);
        btnEx[8][13] = new Tile("BISHOP", "WHITE", new Position(8,13));
        btn[9][13].setImageResource(R.mipmap.knight_white);
        btnEx[9][13] = new Tile("KNIGHT", "WHITE", new Position(9,13));
        btn[10][13].setImageResource(R.mipmap.rook_white);
        btnEx[10][13] = new Tile("ROOK", "WHITE", new Position(10,13));
        for(int i = 3; i < 11; i++){
            btn[i][12].setImageResource(R.mipmap.pawn_white);
            btnEx[i][12] = new Tile("PAWN", "WHITE", new Position(i,12));
        }

        btn[0][3].setImageResource(R.mipmap.rook_red);
        btnEx[0][3] = new Tile("ROOK", "RED", new Position(0,3));
        btn[0][4].setImageResource(R.mipmap.knight_red);
        btnEx[0][4] = new Tile("KNIGHT", "RED", new Position(0,4));
        btn[0][5].setImageResource(R.mipmap.bishop_red);
        btnEx[0][5] = new Tile("BISHOP", "RED", new Position(0,5));
        btn[0][6].setImageResource(R.mipmap.king_red);
        btnEx[0][6] = new Tile("KING", "RED", new Position(0,6));
        btn[0][7].setImageResource(R.mipmap.queen_red);
        btnEx[0][7] = new Tile("QUEEN", "RED", new Position(0,7));
        btn[0][8].setImageResource(R.mipmap.bishop_red);
        btnEx[0][8] = new Tile("BISHOP", "RED", new Position(0,8));
        btn[0][9].setImageResource(R.mipmap.knight_red);
        btnEx[0][9] = new Tile("KNIGHT", "RED", new Position(0,9));
        btn[0][10].setImageResource(R.mipmap.rook_red);
        btnEx[0][10] = new Tile("ROOK", "RED", new Position(0,10));
        for(int i = 3; i < 11; i++){
            btn[1][i].setImageResource(R.mipmap.pawn_red);
            btnEx[1][i] = new Tile("PAWN", "RED", new Position(1,i));
        }

        btn[13][3].setImageResource(R.mipmap.rook_green);
        btnEx[13][3] = new Tile("ROOK", "GREEN", new Position(13,3));
        btn[13][4].setImageResource(R.mipmap.knight_green);
        btnEx[13][4] = new Tile("KNIGHT", "GREEN", new Position(13,4));
        btn[13][5].setImageResource(R.mipmap.bishop_green);
        btnEx[13][5] = new Tile("BISHOP", "GREEN", new Position(13,5));
        btn[13][6].setImageResource(R.mipmap.queen_green);
        btnEx[13][6] = new Tile("QUEEN", "GREEN", new Position(13, 6));
        btn[13][7].setImageResource(R.mipmap.king_green);
        btnEx[13][7] = new Tile("KING", "GREEN", new Position(13,7));
        btn[13][8].setImageResource(R.mipmap.bishop_green);
        btnEx[13][8] = new Tile("BISHOP", "GREEN", new Position(13,8));
        btn[13][9].setImageResource(R.mipmap.knight_green);
        btnEx[13][9] = new Tile("KNIGHT", "GREEN", new Position(13,9));
        btn[13][10].setImageResource(R.mipmap.rook_green);
        btnEx[13][10] = new Tile("ROOK", "GREEN", new Position(13,10));
        for(int i = 3; i < 11; i++){
            btn[12][i].setImageResource(R.mipmap.pawn_green);
            btnEx[12][i] = new Tile("PAWN", "GREEN", new Position(12,i));
        }
    }
        else if(mycolor.equals("BLACK")){
            btn[3][0].setImageResource(R.mipmap.rook_white);
            btnEx[3][0] = new Tile("ROOK", "WHITE", new Position(3,0));

            btn[4][0].setImageResource(R.mipmap.knight_white);
            btnEx[4][0] = new Tile("KNIGHT", "WHITE", new Position(4,0));
            btn[5][0].setImageResource(R.mipmap.bishop_white);
            btnEx[5][0] = new Tile("BISHOP", "WHITE", new Position(5,0));
            btn[6][0].setImageResource(R.mipmap.king_white);
            btnEx[6][0] = new Tile("KING", "WHITE", new Position(6,0));
            btn[7][0].setImageResource(R.mipmap.queen_white);
            btnEx[7][0] = new Tile("QUEEN", "WHITE", new Position(7,0));
            btn[8][0].setImageResource(R.mipmap.bishop_white);
            btnEx[8][0] = new Tile("BISHOP", "WHITE", new Position(8,0));
            btn[9][0].setImageResource(R.mipmap.knight_white);
            btnEx[9][0] = new Tile("KNIGHT", "WHITE", new Position(9,0));
            btn[10][0].setImageResource(R.mipmap.rook_white);
            btnEx[10][0] = new Tile("ROOK", "WHITE", new Position(10,0));

            for(int i = 3; i < 11; i++){
                btn[i][1].setImageResource(R.mipmap.pawn_white);
                btnEx[i][1] = new Tile("PAWN", "WHITE", new Position(i,1));
            }


            btn[3][13].setImageResource(R.mipmap.rook_black);
            btnEx[3][13] = new Tile("ROOK", "BLACK", new Position(3,13));
            btn[4][13].setImageResource(R.mipmap.knight_black);
            btnEx[4][13] = new Tile("KNIGHT", "BLACK", new Position(4,13));
            btn[5][13].setImageResource(R.mipmap.bishop_black);
            btnEx[5][13] = new Tile("BISHOP", "BLACK", new Position(5,13));
            btn[6][13].setImageResource(R.mipmap.queen_black);
            btnEx[6][13] = new Tile("QUEEN", "BLACK", new Position(6,13));
            btn[7][13].setImageResource(R.mipmap.king_black);
            btnEx[7][13] = new Tile("KING", "BLACK", new Position(7,13));
            btn[8][13].setImageResource(R.mipmap.bishop_black);
            btnEx[8][13] = new Tile("BISHOP", "BLACK", new Position(8,13));
            btn[9][13].setImageResource(R.mipmap.knight_black);
            btnEx[9][13] = new Tile("KNIGHT", "BLACK", new Position(9,13));
            btn[10][13].setImageResource(R.mipmap.rook_black);
            btnEx[10][13] = new Tile("ROOK", "BLACK", new Position(10,13));
            for(int i = 3; i < 11; i++){
                btn[i][12].setImageResource(R.mipmap.pawn_black);
                btnEx[i][12] = new Tile("PAWN", "BLACK", new Position(i,12));
            }

            btn[0][3].setImageResource(R.mipmap.rook_green);
            btnEx[0][3] = new Tile("ROOK", "GREEN", new Position(0,3));
            btn[0][4].setImageResource(R.mipmap.knight_green);
            btnEx[0][4] = new Tile("KNIGHT", "GREEN", new Position(0,4));
            btn[0][5].setImageResource(R.mipmap.bishop_green);
            btnEx[0][5] = new Tile("BISHOP", "GREEN", new Position(0,5));
            btn[0][6].setImageResource(R.mipmap.king_green);
            btnEx[0][6] = new Tile("KING", "GREEN", new Position(0,6));
            btn[0][7].setImageResource(R.mipmap.queen_green);
            btnEx[0][7] = new Tile("QUEEN", "GREEN", new Position(0,7));
            btn[0][8].setImageResource(R.mipmap.bishop_green);
            btnEx[0][8] = new Tile("BISHOP", "GREEN", new Position(0,8));
            btn[0][9].setImageResource(R.mipmap.knight_green);
            btnEx[0][9] = new Tile("KNIGHT", "GREEN", new Position(0,9));
            btn[0][10].setImageResource(R.mipmap.rook_green);
            btnEx[0][10] = new Tile("ROOK", "GREEN", new Position(0,10));
            for(int i = 3; i < 11; i++){
                btn[1][i].setImageResource(R.mipmap.pawn_green);
                btnEx[1][i] = new Tile("PAWN", "GREEN", new Position(1,i));
            }

            btn[13][3].setImageResource(R.mipmap.rook_red);
            btnEx[13][3] = new Tile("ROOK", "RED", new Position(13,3));
            btn[13][4].setImageResource(R.mipmap.knight_red);
            btnEx[13][4] = new Tile("KNIGHT", "RED", new Position(13,4));
            btn[13][5].setImageResource(R.mipmap.bishop_red);
            btnEx[13][5] = new Tile("BISHOP", "RED", new Position(13,5));
            btn[13][6].setImageResource(R.mipmap.queen_red);
            btnEx[13][6] = new Tile("QUEEN", "RED", new Position(13, 6));
            btn[13][7].setImageResource(R.mipmap.king_red);
            btnEx[13][7] = new Tile("KING", "RED", new Position(13,7));
            btn[13][8].setImageResource(R.mipmap.bishop_red);
            btnEx[13][8] = new Tile("BISHOP", "RED", new Position(13,8));
            btn[13][9].setImageResource(R.mipmap.knight_red);
            btnEx[13][9] = new Tile("KNIGHT", "RED", new Position(13,9));
            btn[13][10].setImageResource(R.mipmap.rook_red);
            btnEx[13][10] = new Tile("ROOK", "RED", new Position(13,10));
            for(int i = 3; i < 11; i++){
                btn[12][i].setImageResource(R.mipmap.pawn_red);
                btnEx[12][i] = new Tile("PAWN", "RED", new Position(12,i));
            }
        }
        if(mycolor.equals("GREEN")){
            btn[3][0].setImageResource(R.mipmap.rook_red);
            btnEx[3][0] = new Tile("ROOK", "RED", new Position(3,0));

            btn[4][0].setImageResource(R.mipmap.knight_red);
            btnEx[4][0] = new Tile("KNIGHT", "RED", new Position(4,0));
            btn[5][0].setImageResource(R.mipmap.bishop_red);
            btnEx[5][0] = new Tile("BISHOP", "RED", new Position(5,0));
            btn[6][0].setImageResource(R.mipmap.king_red);
            btnEx[6][0] = new Tile("KING", "RED", new Position(6,0));
            btn[7][0].setImageResource(R.mipmap.queen_red);
            btnEx[7][0] = new Tile("QUEEN", "RED", new Position(7,0));
            btn[8][0].setImageResource(R.mipmap.bishop_red);
            btnEx[8][0] = new Tile("BISHOP", "RED", new Position(8,0));
            btn[9][0].setImageResource(R.mipmap.knight_red);
            btnEx[9][0] = new Tile("KNIGHT", "RED", new Position(9,0));
            btn[10][0].setImageResource(R.mipmap.rook_red);
            btnEx[10][0] = new Tile("ROOK", "RED", new Position(10,0));

            for(int i = 3; i < 11; i++){
                btn[i][1].setImageResource(R.mipmap.pawn_red);
                btnEx[i][1] = new Tile("PAWN", "RED", new Position(i,1));
            }


            btn[3][13].setImageResource(R.mipmap.rook_green);
            btnEx[3][13] = new Tile("ROOK", "GREEN", new Position(3,13));
            btn[4][13].setImageResource(R.mipmap.knight_green);
            btnEx[4][13] = new Tile("KNIGHT", "GREEN", new Position(4,13));
            btn[5][13].setImageResource(R.mipmap.bishop_green);
            btnEx[5][13] = new Tile("BISHOP", "GREEN", new Position(5,13));
            btn[6][13].setImageResource(R.mipmap.queen_green);
            btnEx[6][13] = new Tile("QUEEN", "GREEN", new Position(6,13));
            btn[7][13].setImageResource(R.mipmap.king_green);
            btnEx[7][13] = new Tile("KING", "GREEN", new Position(7,13));
            btn[8][13].setImageResource(R.mipmap.bishop_green);
            btnEx[8][13] = new Tile("BISHOP", "GREEN", new Position(8,13));
            btn[9][13].setImageResource(R.mipmap.knight_green);
            btnEx[9][13] = new Tile("KNIGHT", "GREEN", new Position(9,13));
            btn[10][13].setImageResource(R.mipmap.rook_green);
            btnEx[10][13] = new Tile("ROOK", "GREEN", new Position(10,13));
            for(int i = 3; i < 11; i++){
                btn[i][12].setImageResource(R.mipmap.pawn_green);
                btnEx[i][12] = new Tile("PAWN", "GREEN", new Position(i,12));
            }

            btn[0][3].setImageResource(R.mipmap.rook_black);
            btnEx[0][3] = new Tile("ROOK", "BLACK", new Position(0,3));
            btn[0][4].setImageResource(R.mipmap.knight_black);
            btnEx[0][4] = new Tile("KNIGHT", "BLACK", new Position(0,4));
            btn[0][5].setImageResource(R.mipmap.bishop_black);
            btnEx[0][5] = new Tile("BISHOP", "BLACK", new Position(0,5));
            btn[0][6].setImageResource(R.mipmap.king_black);
            btnEx[0][6] = new Tile("KING", "BLACK", new Position(0,6));
            btn[0][7].setImageResource(R.mipmap.queen_black);
            btnEx[0][7] = new Tile("QUEEN", "BLACK", new Position(0,7));
            btn[0][8].setImageResource(R.mipmap.bishop_black);
            btnEx[0][8] = new Tile("BISHOP", "BLACK", new Position(0,8));
            btn[0][9].setImageResource(R.mipmap.knight_black);
            btnEx[0][9] = new Tile("KNIGHT", "BLACK", new Position(0,9));
            btn[0][10].setImageResource(R.mipmap.rook_black);
            btnEx[0][10] = new Tile("ROOK", "BLACK", new Position(0,10));
            for(int i = 3; i < 11; i++){
                btn[1][i].setImageResource(R.mipmap.pawn_black);
                btnEx[1][i] = new Tile("PAWN", "BLACK", new Position(1,i));
            }

            btn[13][3].setImageResource(R.mipmap.rook_white);
            btnEx[13][3] = new Tile("ROOK", "WHITE", new Position(13,3));
            btn[13][4].setImageResource(R.mipmap.knight_white);
            btnEx[13][4] = new Tile("KNIGHT", "WHITE", new Position(13,4));
            btn[13][5].setImageResource(R.mipmap.bishop_white);
            btnEx[13][5] = new Tile("BISHOP", "WHITE", new Position(13,5));
            btn[13][6].setImageResource(R.mipmap.queen_white);
            btnEx[13][6] = new Tile("QUEEN", "WHITE", new Position(13, 6));
            btn[13][7].setImageResource(R.mipmap.king_white);
            btnEx[13][7] = new Tile("KING", "WHITE", new Position(13,7));
            btn[13][8].setImageResource(R.mipmap.bishop_white);
            btnEx[13][8] = new Tile("BISHOP", "WHITE", new Position(13,8));
            btn[13][9].setImageResource(R.mipmap.knight_white);
            btnEx[13][9] = new Tile("KNIGHT", "WHITE", new Position(13,9));
            btn[13][10].setImageResource(R.mipmap.rook_white);
            btnEx[13][10] = new Tile("ROOK", "WHITE", new Position(13,10));
            for(int i = 3; i < 11; i++){
                btn[12][i].setImageResource(R.mipmap.pawn_white);
                btnEx[12][i] = new Tile("PAWN", "WHITE", new Position(12,i));
            }
        }
        if(mycolor.equals("RED")){
            btn[3][0].setImageResource(R.mipmap.rook_green);
            btnEx[3][0] = new Tile("ROOK", "GREEN", new Position(3,0));

            btn[4][0].setImageResource(R.mipmap.knight_green);
            btnEx[4][0] = new Tile("KNIGHT", "GREEN", new Position(4,0));
            btn[5][0].setImageResource(R.mipmap.bishop_green);
            btnEx[5][0] = new Tile("BISHOP", "GREEN", new Position(5,0));
            btn[6][0].setImageResource(R.mipmap.king_green);
            btnEx[6][0] = new Tile("KING", "GREEN", new Position(6,0));
            btn[7][0].setImageResource(R.mipmap.queen_green);
            btnEx[7][0] = new Tile("QUEEN", "GREEN", new Position(7,0));
            btn[8][0].setImageResource(R.mipmap.bishop_green);
            btnEx[8][0] = new Tile("BISHOP", "GREEN", new Position(8,0));
            btn[9][0].setImageResource(R.mipmap.knight_green);
            btnEx[9][0] = new Tile("KNIGHT", "GREEN", new Position(9,0));
            btn[10][0].setImageResource(R.mipmap.rook_green);
            btnEx[10][0] = new Tile("ROOK", "GREEN", new Position(10,0));

            for(int i = 3; i < 11; i++){
                btn[i][1].setImageResource(R.mipmap.pawn_green);
                btnEx[i][1] = new Tile("PAWN", "GREEN", new Position(i,1));
            }


            btn[3][13].setImageResource(R.mipmap.rook_red);
            btnEx[3][13] = new Tile("ROOK", "RED", new Position(3,13));
            btn[4][13].setImageResource(R.mipmap.knight_red);
            btnEx[4][13] = new Tile("KNIGHT", "RED", new Position(4,13));
            btn[5][13].setImageResource(R.mipmap.bishop_red);
            btnEx[5][13] = new Tile("BISHOP", "RED", new Position(5,13));
            btn[6][13].setImageResource(R.mipmap.queen_red);
            btnEx[6][13] = new Tile("QUEEN", "RED", new Position(6,13));
            btn[7][13].setImageResource(R.mipmap.king_red);
            btnEx[7][13] = new Tile("KING", "RED", new Position(7,13));
            btn[8][13].setImageResource(R.mipmap.bishop_red);
            btnEx[8][13] = new Tile("BISHOP", "RED", new Position(8,13));
            btn[9][13].setImageResource(R.mipmap.knight_red);
            btnEx[9][13] = new Tile("KNIGHT", "RED", new Position(9,13));
            btn[10][13].setImageResource(R.mipmap.rook_red);
            btnEx[10][13] = new Tile("ROOK", "RED", new Position(10,13));
            for(int i = 3; i < 11; i++){
                btn[i][12].setImageResource(R.mipmap.pawn_red);
                btnEx[i][12] = new Tile("PAWN", "RED", new Position(i,12));
            }

            btn[0][3].setImageResource(R.mipmap.rook_white);
            btnEx[0][3] = new Tile("ROOK", "WHITE", new Position(0,3));
            btn[0][4].setImageResource(R.mipmap.knight_white);
            btnEx[0][4] = new Tile("KNIGHT", "WHITE", new Position(0,4));
            btn[0][5].setImageResource(R.mipmap.bishop_white);
            btnEx[0][5] = new Tile("BISHOP", "WHITE", new Position(0,5));
            btn[0][6].setImageResource(R.mipmap.king_white);
            btnEx[0][6] = new Tile("KING", "WHITE", new Position(0,6));
            btn[0][7].setImageResource(R.mipmap.queen_white);
            btnEx[0][7] = new Tile("QUEEN", "WHITE", new Position(0,7));
            btn[0][8].setImageResource(R.mipmap.bishop_white);
            btnEx[0][8] = new Tile("BISHOP", "WHITE", new Position(0,8));
            btn[0][9].setImageResource(R.mipmap.knight_white);
            btnEx[0][9] = new Tile("KNIGHT", "WHITE", new Position(0,9));
            btn[0][10].setImageResource(R.mipmap.rook_white);
            btnEx[0][10] = new Tile("ROOK", "WHITE", new Position(0,10));
            for(int i = 3; i < 11; i++){
                btn[1][i].setImageResource(R.mipmap.pawn_white);
                btnEx[1][i] = new Tile("PAWN", "WHITE", new Position(1,i));
            }

            btn[13][3].setImageResource(R.mipmap.rook_black);
            btnEx[13][3] = new Tile("ROOK", "BLACK", new Position(13,3));
            btn[13][4].setImageResource(R.mipmap.knight_black);
            btnEx[13][4] = new Tile("KNIGHT", "BLACK", new Position(13,4));
            btn[13][5].setImageResource(R.mipmap.bishop_black);
            btnEx[13][5] = new Tile("BISHOP", "BLACK", new Position(13,5));
            btn[13][6].setImageResource(R.mipmap.queen_black);
            btnEx[13][6] = new Tile("QUEEN", "BLACK", new Position(13, 6));
            btn[13][7].setImageResource(R.mipmap.king_black);
            btnEx[13][7] = new Tile("KING", "BLACK", new Position(13,7));
            btn[13][8].setImageResource(R.mipmap.bishop_black);
            btnEx[13][8] = new Tile("BISHOP", "BLACK", new Position(13,8));
            btn[13][9].setImageResource(R.mipmap.knight_black);
            btnEx[13][9] = new Tile("KNIGHT", "BLACK", new Position(13,9));
            btn[13][10].setImageResource(R.mipmap.rook_black);
            btnEx[13][10] = new Tile("ROOK", "BLACK", new Position(13,10));
            for(int i = 3; i < 11; i++){
                btn[12][i].setImageResource(R.mipmap.pawn_black);
                btnEx[12][i] = new Tile("PAWN", "BLACK", new Position(12,i));
            }
        }
}

}
