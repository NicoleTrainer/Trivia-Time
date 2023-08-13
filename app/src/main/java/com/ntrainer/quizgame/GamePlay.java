package com.ntrainer.quizgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GamePlay extends AppCompatActivity implements View.OnClickListener {
    Button ans1, ans2, ans3, ans4, select;
    String selectedAns = " ";
    TextView totalQuestions, question;
    private int totalScore = 0;
    int questionIndex = 0;
    int totQuestions = questions.question.length;
    String score;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        totalQuestions = findViewById(R.id.qTotal);
        question = findViewById(R.id.question);
        ans1 = findViewById(R.id.ans_1);
        ans2 = findViewById(R.id.ans_2);
        ans3 = findViewById(R.id.ans_3);
        ans4 = findViewById(R.id.ans_4);
        select = findViewById(R.id.submit);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
        select.setOnClickListener(this);


        totalQuestions.setText("Question: " + (questionIndex+1) + "/" + totQuestions) ;

        loadNewQuestion();

    }


    public void onClick(View view) {
        int scores = 0;
        Button clicked = (Button) view;
        ans1.setBackgroundColor(Color.WHITE);
        ans2.setBackgroundColor(Color.WHITE);
        ans3.setBackgroundColor(Color.WHITE);
        ans4.setBackgroundColor(Color.WHITE);

        String ansCorr = questions.answerCorrect[questionIndex];

        if (clicked.getId() == R.id.submit) {

            if (selectedAns.equals(ansCorr)) {
                totalScore++;
            }
            questionIndex++;
            if(questionIndex!=totQuestions) {
                totalQuestions.setText("Question: " + (questionIndex + 1) + "/" + totQuestions);
            }

            loadNewQuestion();

        } else {
             selectedAns = clicked.getText().toString();
            clicked.setBackgroundColor(Color.LTGRAY);

        }

    }


    void loadNewQuestion() {

        if (questionIndex == totQuestions) {
            endQuiz();
            return;
        }
        question.setText(questions.question[questionIndex]);
        ans1.setText(questions.answerChoices[questionIndex][0]);
        ans2.setText(questions.answerChoices[questionIndex][1]);
        ans3.setText(questions.answerChoices[questionIndex][2]);
        ans4.setText(questions.answerChoices[questionIndex][3]);

    }

     void endQuiz() {
        switchToEnd();

    }


    private void switchToEnd(){
        Intent intent = new Intent(this, endGame.class);
        intent.putExtra("score", totalScore);
        intent.putExtra("totalQuestions", totQuestions);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }


}
