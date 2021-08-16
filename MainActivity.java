package com.zybooks.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timerCountDown;
    TextView score;
    TextView problem;
    TextView responseView;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button startButton;
    Button resetButton;
    CountDownTimer countDownTimer;
    Random r = new Random();
    int sum;
    int correct = 0;
    int wrong = 0;
    Boolean gameActive = true;

    public void buttonClicked(View view){
        if(gameActive == true) {
            Button value = (Button) view;
            if (value.getTag().toString().equals(Integer.toString(sum))) {
                Log.i("Info ", "You Win");
                responseView.setVisibility(View.VISIBLE);
                responseView.setText("Correct!");
                scoreKeeper(1);
                countDownTimer.cancel();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameStart(view);
                    }
                }, 1000);
            } else {
                responseView.setVisibility(View.VISIBLE);
                responseView.setText("Try Again");
                scoreKeeper(0);
                countDownTimer.cancel();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameStart(view);
                    }
                }, 1000);
            }
        }else {
            //Restart Game
        }
    }

    public void gameStart(View view) {
        startButton.setEnabled(false);
        resetButton.setEnabled(false);
        responseView.setVisibility(View.INVISIBLE);
        equationGenerator();
        int range = (99 - 1)+1;
        int b1 = r.nextInt(range);
        int b2 = r.nextInt(range);
        int b3 = r.nextInt(range);
        int b4 = r.nextInt(range);
        btn1.setText(Integer.toString(b1));
        btn1.setTag(Integer.toString(b1));
        btn2.setText(Integer.toString(b2));
        btn2.setTag(Integer.toString(b2));
        btn3.setText(Integer.toString(b3));
        btn3.setTag(Integer.toString(b3));
        btn4.setText(Integer.toString(b4));
        btn4.setTag(Integer.toString(b4));
        int sumButton = r.nextInt(4)+1;

        switch(sumButton){
            case 1:
                btn1.setText(Integer.toString(sum));
                btn1.setTag(Integer.toString(sum));
                break;
            case 2:
                btn2.setText(Integer.toString(sum));
                btn2.setTag(Integer.toString(sum));
                break;
            case 3:
                btn3.setText(Integer.toString(sum));
                btn3.setTag(Integer.toString(sum));
                break;
            case 4:
                btn4.setText(Integer.toString(sum));
                btn4.setTag(Integer.toString(sum));
                break;
            default:
                break;
        }
        countDownTimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secondsLeft = (int) millisUntilFinished / 1000;
                timerCountDown.setText(Integer.toString(secondsLeft));
            }
            @Override
            public void onFinish() {
                gameActive = false;
                responseView.setVisibility(View.VISIBLE);
                responseView.setText("Game Over!");
                Log.i("I",Boolean.toString(gameActive));
                startButton.setEnabled(false);
                resetButton.setEnabled(true);


            }
        }.start();
    }

    public void equationGenerator(){
        int numRange = (50-1)+1;
        int num1 = r.nextInt(numRange);
        int num2 = r.nextInt(numRange);
        sum = num1+num2;
        problem.setText(Integer.toString(num1) + " + " + Integer.toString(num2));// + " = " + sum);
    }

    public void scoreKeeper(int num){
        if(num == 1){
            correct = correct + 1;
        }else{
            wrong = wrong + 1;
        }
        score.setText(Integer.toString(correct) + " / " + Integer.toString(wrong));

    }
    public void gameReset(View view){
        gameActive = true;
        responseView.setText("Press Start");
        score.setText("0 / 0");
        correct = 0;
        wrong = 0;
        responseView.setText("Press Start");
        startButton.setEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerCountDown = findViewById(R.id.timerTextView);
        score = findViewById(R.id.scoreTextView);
        problem = findViewById(R.id.problemTextView);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        startButton = findViewById(R.id.startButton);
        responseView = findViewById(R.id.responseView);
        timerCountDown.setText("10");
        resetButton = findViewById(R.id.resetButton);
        resetButton.setEnabled(false);
    }
}