package com.aerovtp.vidur.learningcalculator;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.aerovtp.vidur.learningcalculator.R.*;


/**
 * Created by Vidur on 8/16/13.
 */
public class additionPractice extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layout.activity_additionpractice);
        final Intent additionPractice = getIntent();

        final EditText answerEditText = (EditText)findViewById(id.answerEditText);

        final TextView num1TextView = (TextView)findViewById(id.num1TextView);
        final TextView num2TextView = (TextView)findViewById(id.num2TextView);
        final TextView carryForwardText = (TextView)findViewById(id.carryForwardPracticeTextView);
        final TextView explainTextView = (TextView)findViewById(id.explanationTextView);
        final TextView firstNumberTextView = (TextView)findViewById(id.firstNumberTextView);
        final TextView secondNumberTextView = (TextView)findViewById(id.secondNumberTextView);
        final TextView answerTextView = (TextView)findViewById(id.answerTextView);
        final TextView dashTextView = (TextView)findViewById(id.textView6);

        double num1Double = Math.random();
        double num2Double = Math.random();
        num1Double = num1Double * 100;
        num2Double = num2Double * 100;
        final int num1 = (int) num1Double;
        final int num2 = (int) num2Double;
        final int[] mistakes = {0};

        num1TextView.setText(String.valueOf(num1));
        num2TextView.setText(String.valueOf(num2));

        final int sum = num1 + num2;

        final Button thatsMyAnswerButton = (Button) findViewById(id.thatsMyAnswerButton);

        thatsMyAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int answer = Integer.valueOf(answerEditText.getText().toString());

                 if (sum == answer) {
                    thatsMyAnswerButton.setText(String.valueOf("Good Job!")); /*
                    MediaPlayer mediaPlayer = MediaPlayer.create();
                    mediaPlayer.start();
                    Intent playVideo = new Intent(com.aerovtp.vidur.learningcalculator.additionPractice.this, VideoActivity.class);
                    startActivity(playVideo);
*/
                } else {
                    thatsMyAnswerButton.setText(String.valueOf("Good Try!"));
                    mistakes[0]++;
                    int additionNum1 = num1;
                    int additionNum2 = num2;
                    final int additionNum1Unchanged = num1;
                    final int additionNum2Unchanged = num2;
                    final int sum = additionNum1Unchanged + additionNum2Unchanged;
                    boolean carryForwardExistenceBoolean = true;
                    String explain = null;
                    int firstDigitNum1 = 0;
                    int lastDigitNum1 = 0;
                    int firstDigitNum2 = 0;
                    int lastDigitNum2 = 0;

                    while (additionNum1 > 0) {
                        lastDigitNum1 = (additionNum1 % 10);
                        firstDigitNum1 = additionNum1 / 10;
                        additionNum1 = 0;
                    }
                    while (additionNum2 > 0) {
                        lastDigitNum2 = (additionNum2 % 10);
                        firstDigitNum2 = additionNum2 / 10;
                        additionNum2 = 0;

                    }

                    int carryForwardExistence = lastDigitNum1 + lastDigitNum2;

                    if (carryForwardExistence >= 10) {
                        carryForwardExistenceBoolean = true;
                    } else {
                        carryForwardExistenceBoolean = false;
                    }

                    // Algorithim Test Line
                    // 111 gets caught in first Else If
                    // 99 gets caught in second Else If

                    if(additionNum1Unchanged >= 100 || additionNum2Unchanged >=100) {
                        Toast.makeText(getApplicationContext(), "The app can only show carry forward upto 99 right now. Stay tuned for future updates.", Toast.LENGTH_LONG).show();

                    } else if (carryForwardExistenceBoolean && (additionNum1Unchanged > 14 ||  additionNum2Unchanged > 14)) {

                        explain = "The way to solve this problem is to first take the last digits on both the numbers that you are adding, " +
                                "right now those are " + lastDigitNum1 + " and " + lastDigitNum2 +". When you add them, you get " + carryForwardExistence +
                                ". You then write the Carry Forward, " + carryForwardExistence + " on top of the numbers already there. You then take all the" +
                                " numbers on the left side, which are " + firstDigitNum1 + " and " + firstDigitNum2 + ". Then copy down the number on the top right,"
                                + " the carry forward, in this case that is " + carryForwardExistence +" and put that as the second digit on the final answer. " +
                                "After all of these steps you get your final answer as " + sum + ".";

                        carryForwardText.setText(String.valueOf(carryForwardExistence));

                    }  else if (sum < 30 || !carryForwardExistenceBoolean) {

                        explain = "In this problem, there is no need for a carry forward so the answer automatically becomes " + sum
                                +".";

                        carryForwardText.setText(null);

                    }  else if (additionNum1Unchanged < 0 || additionNum2Unchanged < 0) {
                        Toast.makeText(getApplicationContext(), "You need to put in positive numbers right now. Stay tuned for future updates!", Toast.LENGTH_LONG).show();

                    }
                    explainTextView.setText(explain);
                    firstNumberTextView.setText(String.valueOf(additionNum1Unchanged));
                    secondNumberTextView.setText(String.valueOf(additionNum2Unchanged));
                    answerTextView.setText(String.valueOf(sum));
                    dashTextView.setText("__________");

                }


        }
        });
        final Button helpButton = (Button) findViewById(id.helpButton);

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent additionIntent =new Intent(com.aerovtp.vidur.learningcalculator.additionPractice.this,AdditionActivity.class);
                startActivity(additionIntent);
            }
        });
   }}







