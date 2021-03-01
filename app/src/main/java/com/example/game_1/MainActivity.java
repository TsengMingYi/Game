package com.example.game_1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.game_1.MyGrid.CIRCLE_TYPE;
import static com.example.game_1.MyGrid.CROSS_TYPE;

public class MainActivity extends AppCompatActivity {
    private Button again;
    private final int MAX_GRID_SIZE = 9;
    private MyGrid[] gridViews = new MyGrid[MAX_GRID_SIZE];

    private final int CIRCLE_TURN = 0;
    private final int CROSS_TURN = 1;

    private int currentTurn = CIRCLE_TURN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initial();


    }

    private void initial() {
        for (int i = 0; i < MAX_GRID_SIZE; i++) {
            gridViews[i].reset();
        }
        currentTurn = CIRCLE_TURN;
    }

    private void showWinner(int turn) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(turn == CIRCLE_TURN ? "圈圈胜利" : "叉叉胜利")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        initial();
                    }
                })
                .show();
    }




    private void checkGameOver() {

        for(int i=0;i<3;i++){
            // horizon
            int startIndex = i * 3;
            if (gridViews[startIndex].getCurrentType() == gridViews[startIndex+1].getCurrentType()
                    && gridViews[startIndex].getCurrentType() == gridViews[startIndex+2].getCurrentType()) {
                if (gridViews[startIndex].getCurrentType() == CIRCLE_TYPE) {
                    showWinner(CIRCLE_TURN);
                } else if (gridViews[startIndex].getCurrentType() == CROSS_TYPE) {
                    showWinner(CROSS_TURN);
                }
            }

            //vertical
            startIndex = i;
            if (gridViews[startIndex].getCurrentType() == gridViews[startIndex+3].getCurrentType()
                    && gridViews[startIndex].getCurrentType() == gridViews[startIndex+6].getCurrentType()) {
                if (gridViews[startIndex].getCurrentType() == CIRCLE_TYPE) {
                    showWinner(CIRCLE_TURN);
                } else if (gridViews[startIndex].getCurrentType() == CROSS_TYPE) {
                    showWinner(CROSS_TURN);
                }
            }
        }

        if (gridViews[0].getCurrentType() == gridViews[4].getCurrentType() && gridViews[4].getCurrentType() == gridViews[8].getCurrentType()) {
            if (gridViews[0].getCurrentType() == CIRCLE_TYPE) {
                showWinner(CIRCLE_TURN);
            } else if (gridViews[0].getCurrentType() == CROSS_TYPE) {
                showWinner(CROSS_TURN);
            }

        }

        if (gridViews[2].getCurrentType() == gridViews[4].getCurrentType() && gridViews[4].getCurrentType() == gridViews[6].getCurrentType()) {
            if (gridViews[2].getCurrentType() == CIRCLE_TYPE) {
                showWinner(CIRCLE_TURN);
            } else if (gridViews[2].getCurrentType() == CROSS_TYPE) {
                showWinner(CROSS_TURN);
            }
        }
    }


    private void findView() {

//        myGrid = findViewById(R.id.grid_1);
//        myGrid.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myGrid.setCurrentType(currentTurn);
//                currentTurn = 1- currentTurn;
//            }
//        });

        for (int i = 0; i < MAX_GRID_SIZE; i++) {
            String localId = "grid_" + (i + 1);
            int resID = getResources().getIdentifier(localId, "id", getPackageName());
            gridViews[i] = findViewById(resID);
        }

        for (int i = 0; i < gridViews.length; i++) {
            gridViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyGrid myGrid;
                    if(v instanceof MyGrid){
                        myGrid = (MyGrid)v;
                    }else {
                        return;
                    }
                    if (myGrid.getCurrentType() == MyGrid.NULL_TYPE) {
                        if (currentTurn == CIRCLE_TURN) {
                            myGrid.setCurrentType(CIRCLE_TYPE);
                            currentTurn = CROSS_TURN;
                        } else if (currentTurn == CROSS_TURN) {
                            myGrid.setCurrentType(MyGrid.CROSS_TYPE);
                            currentTurn = CIRCLE_TURN;
                        }
                        checkGameOver();
                    }

                }
            });
        }
    }
}




