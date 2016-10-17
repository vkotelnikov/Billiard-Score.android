package com.example.vyacheslav.billiardscore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    TextView score;
    Button plusbutton;
    Button minusbutton;
    Button reset;

    public void plusminus(TextView a, boolean b){
        if (b) {
            String text = a.getText().toString();
            int A = Integer.parseInt(text);
                A += 1;
                a.setText(String.valueOf(A));
        } else {
            String text = a.getText().toString();
            int A = Integer.parseInt(text);
            if (A==0) return; else {
                A -= 1;
                a.setText(String.valueOf(A));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        score = (TextView) findViewById(R.id.score);
        plusbutton = (Button) findViewById(R.id.plusbutton);
        minusbutton = (Button) findViewById(R.id.minusbutton);
        reset = (Button) findViewById(R.id.reset);

        View.OnClickListener onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.plusbutton:
                        plusminus(score, true);
                        break;
                    case R.id.minusbutton:
                        plusminus(score, false);
                        break;
                    case R.id.reset:
                        score.setText("0");
                        break;
                }
            }
        };

        plusbutton.setOnClickListener(onClickListener);
        minusbutton.setOnClickListener(onClickListener);
        reset.setOnClickListener(onClickListener);
    }



}
