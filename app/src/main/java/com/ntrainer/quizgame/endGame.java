package com.ntrainer.quizgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class endGame extends AppCompatActivity {

    String passOrFail = " ";
    TextView passOrFailMsg, totalScore;
    Button redo, returnHome;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        totalScore = findViewById(R.id.totalscore);
        passOrFailMsg = findViewById(R.id.victoryorfail);

        totalScore.setText(score + "/" + totalQuestions);
        passOrFail =  getPassOrFailMsg(score, totalQuestions);
        passOrFailMsg.setText(passOrFail);

        redo = findViewById(R.id.redo);
        returnHome = findViewById(R.id.returnHome);


        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redoQuiz();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                returnHomeQuiz();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            }
        });

    }

    private void returnHomeQuiz() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void redoQuiz() {
        Intent intent = new Intent(this, GamePlay.class);
        startActivity(intent);
    }


    private String getPassOrFailMsg(int score, int totalQuestions) {

        int diff = totalQuestions-score;

        if(diff==0){ //all questions correct
            passOrFail = "GREAT JOB!";
        }
        else if(diff<2){ //80% questions correct
            passOrFail = "GOOD WORK!";
        }
        else if(diff<4){ //60% questions correct
            passOrFail = "PASS";
        }
        else{ //less then 60% questions correct
            passOrFail = "TRY AGAIN!";
        }
        return passOrFail;
    }


}
