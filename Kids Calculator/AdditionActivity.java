package com.aerovtp.vidur.learningcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vidur on 7/31/13.
 */
public class AdditionActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_addition);
    final Intent additionIntent = getIntent();


    final EditText addition1EditText = (EditText)findViewById(R.id.addition1EditText);
    final EditText addition2EditText = (EditText)findViewById(R.id.addition2EditText);

    final TextView answerTextView = (TextView)findViewById(R.id.answerTextView);
    final TextView explainTextView = (TextView)findViewById(R.id.explanationTextView);
    final TextView carryForwardTextView = (TextView)findViewById(R.id.carryForwardTextView);
    final TextView num1TextView = (TextView)findViewById(R.id.num1TextView);
    final TextView num2TextView = (TextView)findViewById(R.id.num2TextView);

    Button addButton = (Button) findViewById(R.id.addButton);

    addButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            int additionNum1 = Integer.valueOf(addition1EditText.getText().toString());
            int additionNum2 = Integer.valueOf(addition2EditText.getText().toString());
            final int additionNum1Unchanged = additionNum1;
            final int additionNum2Unchanged = additionNum2;
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

                explain = "First take the last digits on both the numbers that you are adding, " + lastDigitNum1 + " and " + lastDigitNum2 +". When you add them, you get " + carryForwardExistence +
                        ". Then write the Carry Forward, " + carryForwardExistence + " on top. Then take the" +
                        " numbers on the left side, " + firstDigitNum1 + " and " + firstDigitNum2 + ". Then copy down the number on the top right,"
                        + " the carry forward, " + carryForwardExistence +" and put that as the second digit on the final answer. " +
                        "Your final answer is " + sum + ".";

                carryForwardTextView.setText(String.valueOf(carryForwardExistence));

            }  else if (sum < 30 || !carryForwardExistenceBoolean) {

                explain = "In this problem, there is no need for a carry forward so the answer becomes " + sum
                        +".";

                carryForwardTextView.setText(null);

            }  else if (additionNum1Unchanged < 0 || additionNum2Unchanged < 0) {
                Toast.makeText(getApplicationContext(), "You need to put in positive numbers right now. Stay tuned for future updates!", Toast.LENGTH_LONG).show();

            }
            explainTextView.setText(explain);
            num1TextView.setText(String.valueOf(additionNum1Unchanged));
            num2TextView.setText(String.valueOf(additionNum2Unchanged));
            answerTextView.setText(String.valueOf(sum));

        }

    });
}
  //Will work on this later
  /*  public int[] calculateDigitsAddition(int additionNum1, int additionNum2){
        int temporary;
        int firstDigitNum1 = 0;
        int lastDigitNum1 = 0;
        int firstDigitNum2 = 0;
        int lastDigitNum2 = 0;

        while (additionNum1 > 0) {
            temporary = (additionNum1 % 10);
            lastDigitNum1 = temporary;
            // additionNum1 /= 10;
            //temporary = (additionNum1 % 10);
            //firstDigitNum1 = temporary;
        }
        while (additionNum2 > 0) {
            temporary = (additionNum2 % 10);
            lastDigitNum2 = temporary;
            //additionNum2 /= 10;
            //temporary = (additionNum2);
            // firstDigitNum2 = temporary;
        }

        int calculateDigitsAddition[] = new int[]{lastDigitNum1, lastDigitNum2};
        return calculateDigitsAddition;

    } */
}
