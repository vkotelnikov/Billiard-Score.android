package com.example.vyacheslav.billiardscore;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView score;
    TextView score2;
    TextView partscore;
    TextView partscore2;
    Button plusbutton;
    Button plusbutton2;
    Button minusbutton;
    Button minusbutton2;
    Button reset;

    SharedPreferences sPref;

    public boolean plusminus(TextView a, boolean b, TextView c) {
        String text = a.getText().toString();
        int A = Integer.parseInt(text);

        String score = c.getText().toString();
        int S = Integer.parseInt(score);

        if (b) {
            A += 1;
            if (A == 8) {
                S += 1;
                A = 0;
                c.setText(String.valueOf(S));
                Toast toast = Toast.makeText(MainActivity.this, "Партия окончена", Toast.LENGTH_SHORT);
                toast.show();
                a.setText(String.valueOf(A));
                writeFile();
                return true;
            }
            a.setText(String.valueOf(A));
        } else {
            if (A == 0) {
                if (S == 0) {
                    return false;
                } else {
                    S -= 1;
                    c.setText(String.valueOf(S));
                    writeFile();
                    return true;
                }
            } else {
                A -= 1;
                a.setText(String.valueOf(A));
            }
        }
        return false;
    }

    final int SIZE_22 = 1;
    final int SIZE_26 = 2;
    final int SIZE_30 = 3;
    final int SIZE_22_2 = 4;
    final int SIZE_26_2 = 5;
    final int SIZE_30_2 = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        score = (TextView) findViewById(R.id.score);
        score2 = (TextView) findViewById(R.id.score2);
        partscore = (TextView) findViewById(R.id.partscore);
        partscore2 = (TextView) findViewById(R.id.partscore2);
        plusbutton = (Button) findViewById(R.id.plusbutton);
        plusbutton2 = (Button) findViewById(R.id.plusbutton2);
        minusbutton = (Button) findViewById(R.id.minusbutton);
        minusbutton2 = (Button) findViewById(R.id.minusbutton2);

        reset = (Button) findViewById(R.id.reset);

        registerForContextMenu(partscore);
        registerForContextMenu(partscore2);



        readFile();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.plusbutton:
                        if(plusminus(score, true, partscore)) {
                            score2.setText("0");
                        }
                        break;
                    case R.id.minusbutton:
                        if(plusminus(score, false, partscore)){
                            score2.setText("0");
                        }
                        break;
                    case R.id.plusbutton2:
                        if(plusminus(score2, true, partscore2))
                            score.setText("0");
                        break;
                    case R.id.minusbutton2:
                        if(plusminus(score2, false, partscore2)){
                            score.setText("0");
                        }
                        break;

                    case R.id.reset:
                        score.setText("0");
                        score2.setText("0");
                        break;
                }
            }
        };

        plusbutton.setOnClickListener(onClickListener);
        plusbutton2.setOnClickListener(onClickListener);
        minusbutton.setOnClickListener(onClickListener);
        minusbutton2.setOnClickListener(onClickListener);
        reset.setOnClickListener(onClickListener);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuinfo){
        switch (v.getId()){
            case R.id.partscore:
                menu.add(0,SIZE_22, 0, "22");
                menu.add(0,SIZE_26, 0, "26");
                menu.add(0,SIZE_30, 0, "30");
                break;
            case R.id.partscore2:
                menu.add(0,SIZE_22_2, 0, "22");
                menu.add(0,SIZE_26_2, 0, "26");
                menu.add(0,SIZE_30_2, 0, "30");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case SIZE_22:
                partscore.setTextSize(22);
                break;
            case SIZE_26:
                partscore.setTextSize(26);
                break;
            case SIZE_30:
                partscore.setTextSize(30);
                break;
            case SIZE_22_2:
                partscore2.setTextSize(22);
                break;
            case SIZE_26_2:
                partscore2.setTextSize(26);
                break;
            case SIZE_30_2:
                partscore2.setTextSize(30);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menuItem:
                Toast.makeText(MainActivity.this, "0", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    public void writeFile() {
        sPref = getPreferences(MODE_PRIVATE);
        Editor ed = sPref.edit();
        ed.putString("1", partscore.getText().toString());
        ed.putString("2", partscore2.getText().toString());
        ed.apply();
    }

    void readFile() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString("1", "0");
        partscore.setText(savedText);
        savedText = sPref.getString("2", "0");
        partscore2.setText(savedText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        writeFile();
    }
}

