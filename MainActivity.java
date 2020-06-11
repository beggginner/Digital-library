package com.example.fkingstop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long a=600000;
    private static final long START_TIME_IN_MILLIS = 600000;
    private static final long START_TIME_IN_MILLIS1 = 600000;

    private TextView mTextViewCountdown;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private Button mButtonStart;
    private Button mButtonReset2;

    private TextView mTextViewCountdown1;
    private Button mButtonStartPause1;
    private Button mButtonReset1;

    private CountDownTimer mCountDownTimer;

    private CountDownTimer mCountDownTimer1;

    private boolean mTimerRunning;

    private boolean mTimerRunning1;


    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private long mTimeLeftInMillis1 = START_TIME_IN_MILLIS1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountdown = findViewById( R.id.text_view_countdown);
        mTextViewCountdown1 = findViewById( R.id.text_view_countdown1);

        mButtonStart=findViewById(R.id.button_start);
        mButtonReset2 = findViewById(R.id.button_reset2);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonStartPause1 = findViewById(R.id.button_start_pause1);
        mButtonReset = findViewById(R.id.button_reset);
        mButtonReset1 = findViewById(R.id.button_reset1);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerRunning){
                    pauseTimer();
                }
                else {
                    startTimer();
                }

            }
        });

        updateCountDownText();

        mButtonStartPause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimerRunning1){
                    pauseTimer1();
                }
                else {
                    startTimer1();
                }

            }
        });

        updateCountDownText1();

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startTimer2();

            }
        });
        mButtonReset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer2();
                mButtonStart.setVisibility(View.VISIBLE);
                mButtonReset2.setVisibility(View.INVISIBLE);

            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer();

            }
        });

        mButtonReset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer1();

            }
        });
    }

    private void startTimer(){

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning=false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.INVISIBLE);

            }
        }.start();

        mTimerRunning= true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);

    }

    private void startTimer2(){
        startTimer();
        mButtonStart.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility((View.VISIBLE));
        mButtonReset2.setVisibility(View.VISIBLE);

    }

    private void resetTimer2(){

        mTimeLeftInMillis=a;
        mTimeLeftInMillis1=a;
        updateCountDownText();
        updateCountDownText1();
       // pauseTimer();
       // pauseTimer1();
        mButtonStart.setVisibility(View.VISIBLE);
        mButtonReset2.setVisibility(View.INVISIBLE);
        //mButtonStartPause.setVisibility(View.INVISIBLE);
        //mButtonStartPause1.setVisibility(View.INVISIBLE);

    }

    private void startTimer1(){

        mCountDownTimer1 = new CountDownTimer(mTimeLeftInMillis1, 1000) {
            @Override
            public void onTick(long millisUntilFinished1) {
                mTimeLeftInMillis1 = millisUntilFinished1;
                updateCountDownText1();
            }

            @Override
            public void onFinish() {
                mTimerRunning1=false;
                mButtonStartPause1.setText("Start");
                mButtonStartPause1.setVisibility(View.INVISIBLE);
                mButtonReset1.setVisibility(View.INVISIBLE);

            }
        }.start();

        mTimerRunning1= true;
        mButtonStartPause1.setText("pause");
        mButtonReset1.setVisibility(View.INVISIBLE);

    }
    private void pauseTimer(){
        mCountDownTimer.cancel();
        //mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause1.setVisibility(View.VISIBLE);
        mButtonStartPause.setVisibility(View.INVISIBLE);
        if(mTimerRunning)
        {
            startTimer1();
        }
        mTimerRunning=false;
    }
    private void pauseTimer1(){
        mCountDownTimer1.cancel();
       // mButtonStartPause1.setText("Start");
        mButtonReset1.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        mButtonStartPause1.setVisibility(View.INVISIBLE);
        mButtonReset2.setVisibility(View.INVISIBLE);
        if(mTimerRunning1)
        {
            startTimer();
        }
        mTimerRunning1=false;
        mButtonReset2.setVisibility(View.INVISIBLE);

    }
    private void resetTimer(){
        mTimeLeftInMillis= START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);


    }
    private void resetTimer1(){
        mTimeLeftInMillis1= START_TIME_IN_MILLIS1;
        updateCountDownText1();
        mButtonReset1.setVisibility(View.INVISIBLE);
        mButtonStartPause1.setVisibility(View.VISIBLE);
    }

    private void updateCountDownText() {
        int minutes=(int) (mTimeLeftInMillis/1000)/60;
        int seconds=(int) (mTimeLeftInMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        mTextViewCountdown.setText(timeLeftFormatted);
    }

    private void updateCountDownText1() {
        int minutes1=(int) (mTimeLeftInMillis1/1000)/60;
        int seconds1=(int) (mTimeLeftInMillis1/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes1,seconds1);

        mTextViewCountdown1.setText(timeLeftFormatted);
    }

}
