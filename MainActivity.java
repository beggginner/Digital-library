package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 600000;

    private TextView mTextViewCountdown;
    private TextView mTextViewCountdown2;
    private Button mButtonStartPause;
    private Button mButtonStartPause2;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;
    private CountDownTimer mCountDownTimer2;

    private boolean mTimer1Running;
    private boolean mTimer2Running;

    private long mTimeLeft1InMillis = START_TIME_IN_MILLIS;
    private long mTimeLeft2InMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewCountdown = findViewById( R.id.text_view_countdown);
        mTextViewCountdown2 = findViewById( R.id.text_view_countdown_2);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonStartPause2 = findViewById(R.id.button_start_pause2);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimer1Running){

                    pauseTimer();

                }
                else {
                    startTimer();

                }

            }
        });

        updateCountDownText();
        mButtonStartPause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTimer2Running){
                    pauseTimer2();
                    startTimer();
                }
                else {
                    startTimer2();
                    pauseTimer();
                }

            }
        });
        updateCountDownText2();
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTimer();

            }
        });
    }

    private void startTimer(){

        mCountDownTimer = new CountDownTimer(mTimeLeft1InMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft1InMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimer1Running=false;
                mButtonStartPause.setText("Start 1");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimer1Running= true;
        mButtonStartPause.setText("START 1");
        mButtonReset.setVisibility(View.INVISIBLE);

    }
    private void startTimer2(){

        mCountDownTimer2 = new CountDownTimer(mTimeLeft1InMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft2InMillis = millisUntilFinished;
                updateCountDownText2();
            }

            @Override
            public void onFinish() {
                mTimer1Running=false;
                mButtonStartPause2.setText("Start 2");
                mButtonStartPause2.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();

        mTimer2Running= true;
        mButtonStartPause.setText("START 1");
        mButtonReset.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimer1Running=false;
        mButtonStartPause.setText("Start 1");
        mButtonReset.setVisibility(View.VISIBLE);

    }
    private void pauseTimer2(){
        mCountDownTimer2.cancel();
        mTimer2Running=false;
        mButtonStartPause2.setText("Start 2");
        mButtonReset.setVisibility(View.VISIBLE);

    }
    private void resetTimer(){
        mTimeLeft1InMillis= START_TIME_IN_MILLIS;
        mTimeLeft2InMillis= START_TIME_IN_MILLIS;

        updateCountDownText();
        updateCountDownText2();

        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        mButtonStartPause2.setVisibility(View.VISIBLE);
    }


    private void updateCountDownText() {
        int minutes=(int) (mTimeLeft1InMillis/1000)/60;
        int seconds=(int) (mTimeLeft1InMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        mTextViewCountdown.setText(timeLeftFormatted);

    }
    private void updateCountDownText2() {
        int minutes=(int) (mTimeLeft2InMillis/1000)/60;
        int seconds=(int) (mTimeLeft2InMillis/1000)%60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);

        mTextViewCountdown2.setText(timeLeftFormatted);

    }

}
