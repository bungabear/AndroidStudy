package com.bungabear.androidstudy.BasicViewTest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bungabear.androidstudy.R;

// Tic-Tac-Toe
public class BasicViewTicTaeToe extends AppCompatActivity implements View.OnClickListener {

    protected Button[] buttons = new Button[9];
    protected Turn[] game = new Turn[9];
    protected Turn current;
    protected TextView tvTurn;
    protected int gameCount;

    protected enum Turn {
        Me(1), You(2), None(0);
        private int num;
        Turn(int num) {
            this.num = num;
        }
        Turn toggle(){
            if(this == Turn.Me){
                return Turn.You;
            }
            return Turn.Me;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view_test_tic_tac_toe);
        for (int i = 0 ; i < buttons.length; i++) {
            buttons[i] = findViewById(getResources().getIdentifier("btn_ttt_"+(i+1), "id", getPackageName()));
            buttons[i].setOnClickListener(this);
        }

        Button btnRest = findViewById(R.id.btn_ttt_reset);
        btnRest.setOnClickListener(this);
        tvTurn = findViewById(R.id.tv_ttt_turn);

        reset();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_ttt_reset){
            reset();
            return;
        }
        for(int i = 0 ; i < buttons.length; i++) {
            if (buttons[i] == v) {
                if(game[i] == Turn.None){
                    inputSet(i);
                    current = current.toggle();
                }
            }
        }
    }

    protected void reset(){
        gameCount = 0;
        for (int i = 0 ; i < buttons.length; i++) {
            game[i] = Turn.None;
            buttons[i].setText("");
            buttons[i].setClickable(true);
        }
        current = Turn.Me;
        String turn = "Turn : " + current.name();
        tvTurn.setText(turn);
    }

    protected void inputSet(int i){
        gameCount++;
        String marker = current.name();
        game[i] = current;
        buttons[i].setText(marker);
        buttons[i].setClickable(false);
        String turn = "Turn : " + current.toggle().name();
        tvTurn.setText(turn);
        checkGame();
    }

    protected void setGame(Turn winner){
        String text;
        if(winner != Turn.None){
            text = winner.name() + " Win!";
            tvTurn.setText(text);
            for(Button btn : buttons)
                btn.setClickable(false);
        } else if(gameCount == 9){
            text = "Draw!";
            tvTurn.setText(text);
            for(Button btn : buttons)
                btn.setClickable(false);
        }
    }

    protected void checkGame(){
        setGame(checkDiag());
        for(int i = 0 ; i < 3; i ++){
            setGame(checkRow(i));
            setGame(checkCol(i));
        }
    }
    // 0 1 2
    // 3 4 5
    // 6 7 8
    protected Turn checkRow(int row){
        int r = row*3;
        if(game[r] == game[r+1])
            if(game[r+1] == game[r+2])
                return game[r];
        return Turn.None;
    }

    protected Turn checkCol(int c){
        if(game[c] == game[c+3])
            if(game[c+3] == game[c+6])
                return game[c];
        return Turn.None;
    }

    protected Turn checkDiag(){
        if(game[0] == game[4])
            if(game[4] == game[8])
                return game[0];

        if(game[2] == game[4])
            if(game[4] == game[6])
                return game[2];

        return Turn.None;
    }
}
