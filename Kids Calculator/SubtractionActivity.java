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
public class SubtractionActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        final Intent subtractionIntent = getIntent();

        final EditText subtraction1EditText = (EditText)findViewById(R.id.subtraction1EditText);
        final EditText subtraction2EditText = (EditText)findViewById(R.id.subtraction2EditText);

        final TextView answerTextView = (TextView)findViewById(R.id.answerTextView);
        final TextView explainTextView = (TextView)findViewById(R.id.explanationTextView);
        final TextView borrowTextView = (TextView)findViewById(R.id.carryForwardTextView);
        final TextView num1TextView = (TextView)findViewById(R.id.num1TextView);
        final TextView num2TextView = (TextView)findViewById(R.id.num2TextView);

        Button subtractionButton = (Button) findViewById(R.id.subtractionButton);

        subtractionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int subtractionNum1 = Integer.valueOf(subtraction1EditText.getText().toString());
                int subtractionNum2 = Integer.valueOf(subtraction2EditText.getText().toString());
                final int subtractionNum1Unchanged = subtractionNum1;
                final int subtractionNum2Unchanged = subtractionNum2;
                int borrowExistence = 0;
                int difference = subtractionNum1Unchanged - subtractionNum2Unchanged;
                boolean borrowExistenceBoolean = true;
                String explain = null;
                int firstDigitNum1 = 0;
                int lastDigitNum1 = 0;
                int firstDigitNum2 = 0;
                int lastDigitNum2 = 0;

                while (subtractionNum1 > 0) {
                    lastDigitNum1 = (subtractionNum1 % 10);
                    firstDigitNum1 = subtractionNum1 / 10;
                    subtractionNum1 = 0;
                }
                while (subtractionNum2 > 0) {
                    lastDigitNum2 = (subtractionNum2 % 10);
                    firstDigitNum2 = subtractionNum2 / 10;
                    subtractionNum2 = 0;

                }

                boolean negativeNumber = false;
                if (subtractionNum2 > subtractionNum1) {
                    negativeNumber = true;
                }
                if (lastDigitNum2 > lastDigitNum1) {
                    borrowExistenceBoolean = true;
                    int lastDigitNum1Calculation = lastDigitNum1 + 10;
                    borrowExistence = lastDigitNum1Calculation - lastDigitNum2;
                } else {
                    borrowExistenceBoolean = false;
                }

                int borrowLastDigitNum1 = lastDigitNum1 + 10;
                int borrowFirstDigitNum1 = firstDigitNum1 - 1;
                int borrowLastDigitDiffrence = borrowLastDigitNum1 - lastDigitNum2;
                int borrowFirstDigitDiffrence = borrowFirstDigitNum1 - firstDigitNum2;
                if (subtractionNum1Unchanged >= 100 || subtractionNum2Unchanged >= 100) {
                    Toast.makeText(getApplicationContext(), "This app can currently show Borrow upto 99 right now. Stay tuned for future updates!", Toast.LENGTH_LONG).show();

                } else if (negativeNumber) {
                    Toast.makeText(getApplicationContext(), "This app can currently show Borrow in positive numbers right now. Stay tuned for future updates!", Toast.LENGTH_LONG).show();
                } else if (borrowExistenceBoolean && (subtractionNum1Unchanged > 14 || subtractionNum2Unchanged > 14)) {

                    explain = "First take the last digits on both numbers you are subtracting, "  + lastDigitNum1 + " and " + lastDigitNum2 + ". Since the bottom number is larger than the top number, add 10 to the top number to make it larger " +
                            "than the bottom number. You can add 10 by borrowing ten from the left side. " + "Reduce the top left number by 1 because you borrowed, you get "
                            + borrowFirstDigitNum1 + " as the new top left number. The number on the top left becomes " + borrowLastDigitNum1 + " so when you subtract the new numbers on the" +
                            "top and bottom, you get" + borrowLastDigitDiffrence + " That is the last digit of the answer and " + borrowFirstDigitDiffrence + " becomes the first " +
                            "digit of the answer.";

                    borrowTextView.setText(String.valueOf(borrowExistence));
                } else if (!borrowExistenceBoolean) {

                    explain = "In this problem, there is no need for a borrow so the answer automatically becomes " + difference
                            + ".";

                    borrowTextView.setText(null);
                } else if (subtractionNum1Unchanged < 0 || subtractionNum2Unchanged < 0) {
                    Toast.makeText(getApplicationContext(), "You need to put in numbers.", Toast.LENGTH_LONG).show();
                }
                explainTextView.setText(explain);
                num1TextView.setText(String.valueOf(subtractionNum1Unchanged));
                num2TextView.setText(String.valueOf(subtractionNum2Unchanged));
                answerTextView.setText(String.valueOf(difference));

            }

        });
    }
   /* public static int[] calculateDigitsSubtraction(int subtractionNum1, int subtractionNum2){
        int temporary;
        int firstDigitNum1 = 0;
        int lastDigitNum1 = 0;
        int firstDigitNum2 = 0;
        int lastDigitNum2 = 0;

        while (subtractionNum1 > 0) {
            temporary = (subtractionNum1 % 10);
            firstDigitNum1 = temporary;
            subtractionNum1 /= 10;
            temporary = (subtractionNum1 % 10);
            lastDigitNum1 = temporary;
        }
        while (subtractionNum2 > 0) {
            temporary = (subtractionNum2 % 10);
            firstDigitNum1 = temporary;
            subtractionNum2 /= 10;
            temporary = (subtractionNum2 % 10);
            lastDigitNum2 = temporary;
        }

        int[] calculateDigitsSubtraction = new int[]{firstDigitNum1  , firstDigitNum2};
        return calculateDigitsSubtraction;




    }
    */
}
