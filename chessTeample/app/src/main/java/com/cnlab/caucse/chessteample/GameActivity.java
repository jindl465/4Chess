package com.cnlab.caucse.chessteample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private ImageButton btn[][] = new ImageButton[14][14];
    private Tile btnEx[][] = new Tile[14][14];
    public static Context context;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);

        // Example of a call to a native method
 //       TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
    btn[0][0] = (ImageButton)findViewById(R.id.board00);
    btn[0][1] = (ImageButton)findViewById(R.id.board01);
    btn[0][2] = (ImageButton)findViewById(R.id.board02);
    btn[0][3] = (ImageButton)findViewById(R.id.board03);
    btn[0][4] = (ImageButton)findViewById(R.id.board04);
    btn[0][5] = (ImageButton)findViewById(R.id.board05);
    btn[0][6] = (ImageButton)findViewById(R.id.board06);
    btn[0][7] = (ImageButton)findViewById(R.id.board07);
    btn[0][8] = (ImageButton)findViewById(R.id.board08);
    btn[0][9] = (ImageButton)findViewById(R.id.board09);
    btn[0][10] = (ImageButton)findViewById(R.id.board010);
    btn[0][11] = (ImageButton)findViewById(R.id.board011);
    btn[0][12] = (ImageButton)findViewById(R.id.board012);
    btn[0][13] = (ImageButton)findViewById(R.id.board013);

        btn[1][0] = (ImageButton)findViewById(R.id.board10);
        btn[1][1] = (ImageButton)findViewById(R.id.board11);
        btn[1][2] = (ImageButton)findViewById(R.id.board12);
        btn[1][3] = (ImageButton)findViewById(R.id.board13);
        btn[1][4] = (ImageButton)findViewById(R.id.board14);
        btn[1][5] = (ImageButton)findViewById(R.id.board15);
        btn[1][6] = (ImageButton)findViewById(R.id.board16);
        btn[1][7] = (ImageButton)findViewById(R.id.board17);
        btn[1][8] = (ImageButton)findViewById(R.id.board18);
        btn[1][9] = (ImageButton)findViewById(R.id.board19);
        btn[1][10] = (ImageButton)findViewById(R.id.board110);
        btn[1][11] = (ImageButton)findViewById(R.id.board111);
        btn[1][12] = (ImageButton)findViewById(R.id.board112);
        btn[1][13] = (ImageButton)findViewById(R.id.board113);

        btn[2][0] = (ImageButton)findViewById(R.id.board20);
        btn[2][1] = (ImageButton)findViewById(R.id.board21);
        btn[2][2] = (ImageButton)findViewById(R.id.board22);
        btn[2][3] = (ImageButton)findViewById(R.id.board23);
        btn[2][4] = (ImageButton)findViewById(R.id.board24);
        btn[2][5] = (ImageButton)findViewById(R.id.board25);
        btn[2][6] = (ImageButton)findViewById(R.id.board26);
        btn[2][7] = (ImageButton)findViewById(R.id.board27);
        btn[2][8] = (ImageButton)findViewById(R.id.board28);
        btn[2][9] = (ImageButton)findViewById(R.id.board29);
        btn[2][10] = (ImageButton)findViewById(R.id.board210);
        btn[2][11] = (ImageButton)findViewById(R.id.board211);
        btn[2][12] = (ImageButton)findViewById(R.id.board212);
        btn[2][13] = (ImageButton)findViewById(R.id.board213);

        btn[3][0] = (ImageButton)findViewById(R.id.board30);
        btn[3][1] = (ImageButton)findViewById(R.id.board31);
        btn[3][2] = (ImageButton)findViewById(R.id.board32);
        btn[3][3] = (ImageButton)findViewById(R.id.board33);
        btn[3][4] = (ImageButton)findViewById(R.id.board34);
        btn[3][5] = (ImageButton)findViewById(R.id.board35);
        btn[3][6] = (ImageButton)findViewById(R.id.board36);
        btn[3][7] = (ImageButton)findViewById(R.id.board37);
        btn[3][8] = (ImageButton)findViewById(R.id.board38);
        btn[3][9] = (ImageButton)findViewById(R.id.board39);
        btn[3][10] = (ImageButton)findViewById(R.id.board310);
        btn[3][11] = (ImageButton)findViewById(R.id.board311);
        btn[3][12] = (ImageButton)findViewById(R.id.board312);
        btn[3][13] = (ImageButton)findViewById(R.id.board313);

        btn[4][0] = (ImageButton)findViewById(R.id.board40);
        btn[4][1] = (ImageButton)findViewById(R.id.board41);
        btn[4][2] = (ImageButton)findViewById(R.id.board42);
        btn[4][3] = (ImageButton)findViewById(R.id.board43);
        btn[4][4] = (ImageButton)findViewById(R.id.board44);
        btn[4][5] = (ImageButton)findViewById(R.id.board45);
        btn[4][6] = (ImageButton)findViewById(R.id.board46);
        btn[4][7] = (ImageButton)findViewById(R.id.board47);
        btn[4][8] = (ImageButton)findViewById(R.id.board48);
        btn[4][9] = (ImageButton)findViewById(R.id.board49);
        btn[4][10] = (ImageButton)findViewById(R.id.board410);
        btn[4][11] = (ImageButton)findViewById(R.id.board411);
        btn[4][12] = (ImageButton)findViewById(R.id.board412);
        btn[4][13] = (ImageButton)findViewById(R.id.board413);

        btn[5][0] = (ImageButton)findViewById(R.id.board50);
        btn[5][1] = (ImageButton)findViewById(R.id.board51);
        btn[5][2] = (ImageButton)findViewById(R.id.board52);
        btn[5][3] = (ImageButton)findViewById(R.id.board53);
        btn[5][4] = (ImageButton)findViewById(R.id.board54);
        btn[5][5] = (ImageButton)findViewById(R.id.board55);
        btn[5][6] = (ImageButton)findViewById(R.id.board56);
        btn[5][7] = (ImageButton)findViewById(R.id.board57);
        btn[5][8] = (ImageButton)findViewById(R.id.board58);
        btn[5][9] = (ImageButton)findViewById(R.id.board59);
        btn[5][10] = (ImageButton)findViewById(R.id.board510);
        btn[5][11] = (ImageButton)findViewById(R.id.board511);
        btn[5][12] = (ImageButton)findViewById(R.id.board512);
        btn[5][13] = (ImageButton)findViewById(R.id.board513);

        btn[6][0] = (ImageButton)findViewById(R.id.board60);
        btn[6][1] = (ImageButton)findViewById(R.id.board61);
        btn[6][2] = (ImageButton)findViewById(R.id.board62);
        btn[6][3] = (ImageButton)findViewById(R.id.board63);
        btn[6][4] = (ImageButton)findViewById(R.id.board64);
        btn[6][5] = (ImageButton)findViewById(R.id.board65);
        btn[6][6] = (ImageButton)findViewById(R.id.board66);
        btn[6][7] = (ImageButton)findViewById(R.id.board67);
        btn[6][8] = (ImageButton)findViewById(R.id.board68);
        btn[6][9] = (ImageButton)findViewById(R.id.board69);
        btn[6][10] = (ImageButton)findViewById(R.id.board610);
        btn[6][11] = (ImageButton)findViewById(R.id.board611);
        btn[6][12] = (ImageButton)findViewById(R.id.board612);
        btn[6][13] = (ImageButton)findViewById(R.id.board613);

        btn[7][0] = (ImageButton)findViewById(R.id.board70);
        btn[7][1] = (ImageButton)findViewById(R.id.board71);
        btn[7][2] = (ImageButton)findViewById(R.id.board72);
        btn[7][3] = (ImageButton)findViewById(R.id.board73);
        btn[7][4] = (ImageButton)findViewById(R.id.board74);
        btn[7][5] = (ImageButton)findViewById(R.id.board75);
        btn[7][6] = (ImageButton)findViewById(R.id.board76);
        btn[7][7] = (ImageButton)findViewById(R.id.board77);
        btn[7][8] = (ImageButton)findViewById(R.id.board78);
        btn[7][9] = (ImageButton)findViewById(R.id.board79);
        btn[7][10] = (ImageButton)findViewById(R.id.board710);
        btn[7][11] = (ImageButton)findViewById(R.id.board711);
        btn[7][12] = (ImageButton)findViewById(R.id.board712);
        btn[7][13] = (ImageButton)findViewById(R.id.board713);

        btn[8][0] = (ImageButton)findViewById(R.id.board80);
        btn[8][1] = (ImageButton)findViewById(R.id.board81);
        btn[8][2] = (ImageButton)findViewById(R.id.board82);
        btn[8][3] = (ImageButton)findViewById(R.id.board83);
        btn[8][4] = (ImageButton)findViewById(R.id.board84);
        btn[8][5] = (ImageButton)findViewById(R.id.board85);
        btn[8][6] = (ImageButton)findViewById(R.id.board86);
        btn[8][7] = (ImageButton)findViewById(R.id.board87);
        btn[8][8] = (ImageButton)findViewById(R.id.board88);
        btn[8][9] = (ImageButton)findViewById(R.id.board89);
        btn[8][10] = (ImageButton)findViewById(R.id.board810);
        btn[8][11] = (ImageButton)findViewById(R.id.board811);
        btn[8][12] = (ImageButton)findViewById(R.id.board812);
        btn[8][13] = (ImageButton)findViewById(R.id.board813);

        btn[9][0] = (ImageButton)findViewById(R.id.board90);
        btn[9][1] = (ImageButton)findViewById(R.id.board91);
        btn[9][2] = (ImageButton)findViewById(R.id.board92);
        btn[9][3] = (ImageButton)findViewById(R.id.board93);
        btn[9][4] = (ImageButton)findViewById(R.id.board94);
        btn[9][5] = (ImageButton)findViewById(R.id.board95);
        btn[9][6] = (ImageButton)findViewById(R.id.board96);
        btn[9][7] = (ImageButton)findViewById(R.id.board97);
        btn[9][8] = (ImageButton)findViewById(R.id.board98);
        btn[9][9] = (ImageButton)findViewById(R.id.board99);
        btn[9][10] = (ImageButton)findViewById(R.id.board910);
        btn[9][11] = (ImageButton)findViewById(R.id.board911);
        btn[9][12] = (ImageButton)findViewById(R.id.board912);
        btn[9][13] = (ImageButton)findViewById(R.id.board913);

        btn[10][0] = (ImageButton)findViewById(R.id.board100);
        btn[10][1] = (ImageButton)findViewById(R.id.board101);
        btn[10][2] = (ImageButton)findViewById(R.id.board102);
        btn[10][3] = (ImageButton)findViewById(R.id.board103);
        btn[10][4] = (ImageButton)findViewById(R.id.board104);
        btn[10][5] = (ImageButton)findViewById(R.id.board105);
        btn[10][6] = (ImageButton)findViewById(R.id.board106);
        btn[10][7] = (ImageButton)findViewById(R.id.board107);
        btn[10][8] = (ImageButton)findViewById(R.id.board108);
        btn[10][9] = (ImageButton)findViewById(R.id.board109);
        btn[10][10] = (ImageButton)findViewById(R.id.board1010);
        btn[10][11] = (ImageButton)findViewById(R.id.board1011);
        btn[10][12] = (ImageButton)findViewById(R.id.board1012);
        btn[10][13] = (ImageButton)findViewById(R.id.board1013);

        btn[11][0] = (ImageButton)findViewById(R.id.board11_0);
        btn[11][1] = (ImageButton)findViewById(R.id.board11_1);
        btn[11][2] = (ImageButton)findViewById(R.id.board11_2);
        btn[11][3] = (ImageButton)findViewById(R.id.board11_3);
        btn[11][4] = (ImageButton)findViewById(R.id.board114);
        btn[11][5] = (ImageButton)findViewById(R.id.board115);
        btn[11][6] = (ImageButton)findViewById(R.id.board116);
        btn[11][7] = (ImageButton)findViewById(R.id.board117);
        btn[11][8] = (ImageButton)findViewById(R.id.board118);
        btn[11][9] = (ImageButton)findViewById(R.id.board119);
        btn[11][10] = (ImageButton)findViewById(R.id.board1110);
        btn[11][11] = (ImageButton)findViewById(R.id.board1111);
        btn[11][12] = (ImageButton)findViewById(R.id.board1112);
        btn[11][13] = (ImageButton)findViewById(R.id.board1113);

        btn[12][0] = (ImageButton)findViewById(R.id.board120);
        btn[12][1] = (ImageButton)findViewById(R.id.board121);
        btn[12][2] = (ImageButton)findViewById(R.id.board122);
        btn[12][3] = (ImageButton)findViewById(R.id.board123);
        btn[12][4] = (ImageButton)findViewById(R.id.board124);
        btn[12][5] = (ImageButton)findViewById(R.id.board125);
        btn[12][6] = (ImageButton)findViewById(R.id.board126);
        btn[12][7] = (ImageButton)findViewById(R.id.board127);
        btn[12][8] = (ImageButton)findViewById(R.id.board128);
        btn[12][9] = (ImageButton)findViewById(R.id.board129);
        btn[12][10] = (ImageButton)findViewById(R.id.board1210);
        btn[12][11] = (ImageButton)findViewById(R.id.board1211);
        btn[12][12] = (ImageButton)findViewById(R.id.board1212);
        btn[12][13] = (ImageButton)findViewById(R.id.board1213);

        btn[13][0] = (ImageButton)findViewById(R.id.board130);
        btn[13][1] = (ImageButton)findViewById(R.id.board131);
        btn[13][2] = (ImageButton)findViewById(R.id.board132);
        btn[13][3] = (ImageButton)findViewById(R.id.board133);
        btn[13][4] = (ImageButton)findViewById(R.id.board134);
        btn[13][5] = (ImageButton)findViewById(R.id.board135);
        btn[13][6] = (ImageButton)findViewById(R.id.board136);
        btn[13][7] = (ImageButton)findViewById(R.id.board137);
        btn[13][8] = (ImageButton)findViewById(R.id.board138);
        btn[13][9] = (ImageButton)findViewById(R.id.board139);
        btn[13][10] = (ImageButton)findViewById(R.id.board1310);
        btn[13][11] = (ImageButton)findViewById(R.id.board1311);
        btn[13][12] = (ImageButton)findViewById(R.id.board1312);
        btn[13][13] = (ImageButton)findViewById(R.id.board1313);

        btn[0][3].setImageResource(R.mipmap.rook_black);
        btnEx[0][3].setActive(true);
        btnEx[0][3].setOccupyPiece("ROOK");
        btnEx[0][3].setOnPiece(true);
        btnEx[0][3].setColor(Color.BLACK);
        btn[0][4].setImageResource(R.mipmap.knight_black);
        btnEx[0][4].setActive(true);
        btnEx[0][4].setOccupyPiece("KNIGHT");
        btnEx[0][4].setOnPiece(true);
        btnEx[0][4].setColor(Color.BLACK);
        btn[0][5].setImageResource(R.mipmap.bishop_black);
        btnEx[0][5].setActive(true);
        btnEx[0][5].setOccupyPiece("BISHOP");
        btnEx[0][5].setOnPiece(true);
        btnEx[0][5].setColor(Color.BLACK);
        btn[0][6].setImageResource(R.mipmap.king_black);
        btnEx[0][6].setActive(true);
        btnEx[0][6].setOccupyPiece("KING");
        btnEx[0][6].setOnPiece(true);
        btnEx[0][6].setColor(Color.BLACK);
        btn[0][7].setImageResource(R.mipmap.queen_black);
        btnEx[0][7].setActive(true);
        btnEx[0][7].setOccupyPiece("QUEEN");
        btnEx[0][7].setOnPiece(true);
        btnEx[0][7].setColor(Color.BLACK);
        btn[0][8].setImageResource(R.mipmap.bishop_black);
        btnEx[0][8].setActive(true);
        btnEx[0][8].setOccupyPiece("BISHOP");
        btnEx[0][8].setOnPiece(true);
        btnEx[0][8].setColor(Color.BLACK);
        btn[0][9].setImageResource(R.mipmap.knight_black);
        btnEx[0][9].setActive(true);
        btnEx[0][9].setOccupyPiece("KNIGHT");
        btnEx[0][9].setOnPiece(true);
        btnEx[0][9].setColor(Color.BLACK);
        btn[0][10].setImageResource(R.mipmap.rook_black);
        btnEx[0][10].setActive(true);
        btnEx[0][10].setOccupyPiece("ROOK");
        btnEx[0][10].setOnPiece(true);
        btnEx[0][10].setColor(Color.BLACK);

        for(int i = 3; i < 11; i++){
            btn[1][i].setImageResource(R.mipmap.pawn_black);
            btnEx[1][i].setActive(true);
            btnEx[1][i].setOccupyPiece("PAWN");
            btnEx[1][i].setOnPiece(true);
            btnEx[1][i].setColor(Color.BLACK);
        }


        btn[13][3].setImageResource(R.mipmap.rook_white);
        btnEx[13][3].setActive(true);
        btnEx[13][3].setOccupyPiece("ROOK");
        btnEx[13][3].setOnPiece(true);
        btnEx[13][3].setColor(Color.WHITE);
        btn[13][4].setImageResource(R.mipmap.knight_white);
        btnEx[13][4].setActive(true);
        btnEx[13][4].setOccupyPiece("KNIGHT");
        btnEx[13][4].setOnPiece(true);
        btnEx[13][4].setColor(Color.WHITE);
        btn[13][5].setImageResource(R.mipmap.bishop_white);
        btnEx[13][5].setActive(true);
        btnEx[13][5].setOccupyPiece("BISHOP");
        btnEx[13][5].setOnPiece(true);
        btnEx[13][5].setColor(Color.WHITE);
        btn[13][6].setImageResource(R.mipmap.queen_white);
        btnEx[13][6].setActive(true);
        btnEx[13][6].setOccupyPiece("QUEEN");
        btnEx[13][6].setOnPiece(true);
        btnEx[13][6].setColor(Color.WHITE);
        btn[13][7].setImageResource(R.mipmap.king_white);
        btnEx[13][7].setActive(true);
        btnEx[13][7].setOccupyPiece("KING");
        btnEx[13][7].setOnPiece(true);
        btnEx[13][7].setColor(Color.WHITE);
        btn[13][8].setImageResource(R.mipmap.bishop_white);
        btnEx[13][8].setActive(true);
        btnEx[13][8].setOccupyPiece("BISHOP");
        btnEx[13][8].setOnPiece(true);
        btnEx[13][8].setColor(Color.WHITE);
        btn[13][9].setImageResource(R.mipmap.knight_white);
        btnEx[13][9].setActive(true);
        btnEx[13][9].setOccupyPiece("KNIGHT");
        btnEx[13][9].setOnPiece(true);
        btnEx[13][9].setColor(Color.WHITE);
        btn[13][10].setImageResource(R.mipmap.rook_white);
        for(int i = 3; i < 11; i++){
            btn[12][i].setImageResource(R.mipmap.pawn_white);
            btnEx[12][i].setActive(true);
            btnEx[12][i].setOccupyPiece("PAWN");
            btnEx[12][i].setOnPiece(true);
            btnEx[12][i].setColor(Color.WHITE);
        }

        btn[3][0].setImageResource(R.mipmap.rook_red);
        btnEx[3][0].setActive(true);
        btnEx[3][0].setOccupyPiece("ROOK");
        btnEx[3][0].setOnPiece(true);
        btnEx[3][0].setColor(Color.RED);
        btn[4][0].setImageResource(R.mipmap.knight_red);
        btnEx[4][0].setActive(true);
        btnEx[4][0].setOccupyPiece("KNIGHT");
        btnEx[4][0].setOnPiece(true);
        btnEx[4][0].setColor(Color.RED);
        btn[5][0].setImageResource(R.mipmap.bishop_red);
        btnEx[5][0].setActive(true);
        btnEx[5][0].setOccupyPiece("BISHOP");
        btnEx[5][0].setOnPiece(true);
        btnEx[5][0].setColor(Color.RED);
        btn[6][0].setImageResource(R.mipmap.king_red);
        btnEx[6][0].setActive(true);
        btnEx[6][0].setOccupyPiece("KING");
        btnEx[6][0].setOnPiece(true);
        btnEx[6][0].setColor(Color.RED);
        btn[7][0].setImageResource(R.mipmap.queen_red);
        btnEx[7][0].setActive(true);
        btnEx[7][0].setOccupyPiece("QUEEN");
        btnEx[7][0].setOnPiece(true);
        btnEx[7][0].setColor(Color.RED);
        btn[8][0].setImageResource(R.mipmap.bishop_red);
        btnEx[8][0].setActive(true);
        btnEx[8][0].setOccupyPiece("BISHOP");
        btnEx[8][0].setOnPiece(true);
        btnEx[8][0].setColor(Color.RED);
        btn[9][0].setImageResource(R.mipmap.knight_red);
        btnEx[9][0].setActive(true);
        btnEx[9][0].setOccupyPiece("KNIGHT");
        btnEx[9][0].setOnPiece(true);
        btnEx[9][0].setColor(Color.RED);
        btn[10][0].setImageResource(R.mipmap.rook_red);
        btnEx[10][0].setActive(true);
        btnEx[10][0].setOccupyPiece("ROOK");
        btnEx[10][0].setOnPiece(true);
        btnEx[10][0].setColor(Color.RED);
        for(int i = 3; i < 11; i++){
            btn[i][1].setImageResource(R.mipmap.pawn_red);
            btnEx[i][1].setActive(true);
            btnEx[i][1].setOccupyPiece("PAWN");
            btnEx[i][1].setOnPiece(true);
            btnEx[i][1].setColor(Color.RED);
        }

        btn[3][13].setImageResource(R.mipmap.rook_green);
        btnEx[3][13].setActive(true);
        btnEx[3][13].setOccupyPiece("ROOK");
        btnEx[3][13].setOnPiece(true);
        btnEx[3][13].setColor(Color.GREEN);
        btn[4][13].setImageResource(R.mipmap.knight_green);
        btnEx[4][13].setActive(true);
        btnEx[4][13].setOccupyPiece("KNIGHT");
        btnEx[4][13].setOnPiece(true);
        btnEx[4][13].setColor(Color.GREEN);
        btn[5][13].setImageResource(R.mipmap.bishop_green);
        btnEx[5][13].setActive(true);
        btnEx[5][13].setOccupyPiece("BISHOP");
        btnEx[5][13].setOnPiece(true);
        btnEx[5][13].setColor(Color.GREEN);
        btn[6][13].setImageResource(R.mipmap.queen_green);
        btnEx[6][13].setActive(true);
        btnEx[6][13].setOccupyPiece("QUEEN");
        btnEx[6][13].setOnPiece(true);
        btnEx[6][13].setColor(Color.GREEN);
        btn[7][13].setImageResource(R.mipmap.king_green);
        btnEx[7][13].setActive(true);
        btnEx[7][13].setOccupyPiece("KING");
        btnEx[7][13].setOnPiece(true);
        btnEx[7][13].setColor(Color.GREEN);
        btn[8][13].setImageResource(R.mipmap.bishop_green);
        btnEx[8][13].setActive(true);
        btnEx[8][13].setOccupyPiece("BISHOP");
        btnEx[8][13].setOnPiece(true);
        btnEx[8][13].setColor(Color.GREEN);
        btn[9][13].setImageResource(R.mipmap.knight_green);
        btnEx[9][13].setActive(true);
        btnEx[9][13].setOccupyPiece("KNIGHT");
        btnEx[9][13].setOnPiece(true);
        btnEx[9][13].setColor(Color.GREEN);
        btn[10][13].setImageResource(R.mipmap.rook_green);
        btnEx[10][13].setActive(true);
        btnEx[10][13].setOccupyPiece("ROOK");
        btnEx[10][13].setOnPiece(true);
        btnEx[10][13].setColor(Color.GREEN);
        for(int i = 3; i < 11; i++){
            btn[i][12].setImageResource(R.mipmap.pawn_green);
            btnEx[i][12].setActive(true);
            btnEx[i][12].setOccupyPiece("PAWN");
            btnEx[i][12].setOnPiece(true);
            btnEx[i][12].setColor(Color.GREEN);
        }

        context = this;
    }

    public Tile[][] getBtnEx() {
        return btnEx;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();
}
