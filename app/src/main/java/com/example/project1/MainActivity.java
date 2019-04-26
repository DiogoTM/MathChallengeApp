package com.example.project1;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.params.BlackLevelPattern;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.nanoTime;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Layout objects
    private TextView textViewTitle;
    private EditText editTextResult;
    private TextView textViewOperation;
    private Button[] buttons= new Button[18];
    ConstraintLayout myLayout;


    //Project objects
    private static String result = "";
    Operation myOp;
    Random myRand = new Random();
    UserLog myUser = new UserLog();
    boolean hardMode = false;//Mode removed in new design (part 2)
    boolean isRunning = false;

    public Operation createOperation()
    {
        myOp= new Operation(myRand,hardMode);
        return myOp;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Are you good in math?");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTitle = findViewById( R.id.textViewTitle );
        editTextResult = findViewById( R.id.editTextResult );
        textViewOperation = findViewById( R.id.textViewOperation );
        myLayout = findViewById(R.id.myLayout);
        int listButtons[] = {R.id.btn0, R.id.btn1, R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,
                R.id.btn7,R.id.btn8,R.id.btn9,R.id.btnStart,R.id.btnDash,R.id.btnClear,
                R.id.btnStop,R.id.btnQuit,R.id.btnEqual,R.id.btnSave,R.id.btnResults};
        for (int i = 0; i < listButtons.length; i++)
        {
            buttons[i] = findViewById(listButtons[i]);
            buttons[i].setOnClickListener(this);
        }
    }


    public void onClick(View v) {
        TextView clickedView = (TextView)v;
        switch (v.getId()) {
            case R.id.btnDash:
                if (!TextUtils.isDigitsOnly(editTextResult.getText()) || TextUtils.isEmpty(editTextResult.getText())){
                    break;
                } else {
                    float temp = Float.valueOf(result);
                    temp *= -1;
                    result = Float.toString(temp);
                    break;
                }
            case R.id.btnClear:
                result = "";
                break;
            case R.id.btnStart:
                isRunning = true;
                do {
                    myOp = createOperation();
                    textViewOperation.setText(myOp.getOperation());
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            myOp = createOperation();
                            textViewOperation.setText(myOp.getOperation());
                        }
                    }, 10000);
                }while (isRunning);
                break;
            case R.id.btnStop:
                isRunning = false;
                break;
            case R.id.btnQuit:
                finish();
                break;
            case R.id.btnEqual:
                if (myOp.getOperation() == null) {
                    textViewOperation.setText("No operation defined, please generate an operation first");
                    break;
                }
                myOp.setAnswer(Float.valueOf(result));
                if (myOp.CheckResult()) {
                    textViewOperation.setText("Correct!");
                }
                else {
                    textViewOperation.setText("Incorrect, Sorry!");
                }
                result = "";
                myUser.addOperation(myOp);
                break;

            case R.id.btnShow:
                Intent newIntent = new Intent(this,SecondActivity.class);
                newIntent.putExtra("results", myUser.toString());
                newIntent.putExtra("accuracy", myUser.getAccuracy());
                startActivity(newIntent);
                break;
            case R.id.btnHMode:
                if (hardMode)
                {
                    setTitle("Are you good in math?");
                    myLayout.setBackgroundColor(Color.BLACK);
                    ((TextView) v).setText("Hard Mode");
                    ((TextView) v).setTextColor(Color.RED);
                    hardMode=false;
                    break;
                }
                setTitle("HARD MODE!");
                myLayout.setBackgroundColor(Color.RED);
                ((TextView) v).setText("Normal Mode");
                ((TextView) v).setTextColor(Color.BLACK);
                hardMode=true;
                break;
            default:
                result += clickedView.getText();
        }
        editTextResult.setText(result);

    }
}

