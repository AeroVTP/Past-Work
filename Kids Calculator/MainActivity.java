package com.aerovtp.vidur.learningcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Button additionButton = (Button) findViewById(R.id.additionButton);

        additionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent additionIntent = new Intent(MainActivity.this, AdditionActivity.class);
                startActivity(additionIntent);
            }});
        Button subtractionButton = (Button) findViewById(R.id.subtractionButton);

            subtractionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent subtractionIntent =new Intent(MainActivity.this,SubtractionActivity.class);
                    startActivity(subtractionIntent);
                }
        });
        Button additionPracticeButton = (Button) findViewById(R.id.additionPracticeButton);

        additionPracticeButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent additionPractice = new Intent(MainActivity.this, com.aerovtp.vidur.learningcalculator.additionPractice.class);
            startActivity(additionPractice);
        }
    });
        Button subtractionPractice = (Button) findViewById(R.id.additionPracticeButton);

        additionPracticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent subtractionPractice = new Intent(MainActivity.this, com.aerovtp.vidur.learningcalculator.subtractionPractice.class);
                startActivity(subtractionPractice);
            }});}}





 