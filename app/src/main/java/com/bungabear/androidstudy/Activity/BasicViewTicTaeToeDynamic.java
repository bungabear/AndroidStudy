package com.bungabear.androidstudy.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bungabear.androidstudy.R;

// Tic-Tac-Toe
public class BasicViewTicTaeToeDynamic extends BasicViewTicTaeToe {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_view_test_tic_tac_toe_dynamic);
        LinearLayout board = findViewById(R.id.ll_tttd_board);
        for (int i = 0; i < 3; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER);
            for (int j = 0; j < 3; j++) {
                Button newButton = new Button(this);
                buttons[i * 3 + j] = newButton;
                newButton.setOnClickListener(this);
                row.addView(newButton);
            }
            board.addView(row);
        }
        Button btnRest = findViewById(R.id.btn_tttd_reset);
        btnRest.setOnClickListener(this);
        tvTurn = findViewById(R.id.tv_tttd_turn);

        reset();
    }
}