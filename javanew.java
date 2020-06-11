package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Integer totalTime;
    ConstraintLayout welcomePage;
    ConstraintLayout clockPage;
    EditText minutes;
    Button playButton;
    Button startButton;
    TextView whiteCounterTextView;
    TextView blackCounterTextView;

    CountDownTimer whiteCountDownTimer;
    CountDownTimer blackCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomePage=(ConstraintLayout)findViewById(R.id.welcomePage);
        clockPage=(ConstraintLayout)findViewById(R.id.clockPage);
        minutes=(EditText)findViewById(R.id.minutes);
        playButton=(Button)findViewById(R.id.playButton);
        startButton=(Button)findViewById(R.id.startButton);
        whiteCounterTextView=(TextView)findViewById(R.id.whiteCounterTextView);
        blackCounterTextView=(TextView)findViewById(R.id.blackCounterTextView);


        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomePage.setVisibility(View.INVISIBLE);
                clockPage.setVisibility(View.VISIBLE);
                totalTime=Integer.parseInt(minutes.getText().toString());
                whiteCounterTextView.setText(totalTime.toString()+":"+"00");
                blackCounterTextView.setText(totalTime.toString()+":"+"00");
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteCountDownTimer=new CountDownTimer(totalTime*60000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        int minutes=(int) (millisUntilFinished/1000)/60;
                        int seconds=(int) (millisUntilFinished/1000)%60;
                        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
                        whiteCounterTextView.setText(timeLeftFormatted);
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }
        });

    }
}
