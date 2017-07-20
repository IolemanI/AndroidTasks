package com.oleman.androidtasks.tasks;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oleman.androidtasks.R;


public class Task5Activity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = "myLogs";

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btn00;

    private Button actSum;
    private Button actSub;
    private Button actMult;
    private Button actDiv;
    private Button actRes;

    private ImageButton actDel;

    private TextView mainText;
    private TextView seccondaryText;
    private TextView actType;

    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task5);

        btn1 = (Button) findViewById(R.id.btn1_t5);
        btn2 = (Button) findViewById(R.id.btn2_t5);
        btn3 = (Button) findViewById(R.id.btn3_t5);
        btn4 = (Button) findViewById(R.id.btn4_t5);
        btn5 = (Button) findViewById(R.id.btn5_t5);
        btn6 = (Button) findViewById(R.id.btn6_t5);
        btn7 = (Button) findViewById(R.id.btn7_t5);
        btn8 = (Button) findViewById(R.id.btn8_t5);
        btn9 = (Button) findViewById(R.id.btn9_t5);
        btn0 = (Button) findViewById(R.id.btn0_t5);
        btn00 = (Button) findViewById(R.id.btn00_t5);
        actSum = (Button) findViewById(R.id.sum_t5);
        actDiv = (Button) findViewById(R.id.div_t5);
        actSub = (Button) findViewById(R.id.sub_t5);
        actMult = (Button) findViewById(R.id.mult_t5);
        actRes = (Button) findViewById(R.id.res_t5);
        actDel = (ImageButton) findViewById(R.id.del_t5);
        mainText = (TextView) findViewById(R.id.mainText_t5);
        seccondaryText = (TextView) findViewById(R.id.secondaryText_t5);
        actType = (TextView) findViewById(R.id.actionType_t5);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn00.setOnClickListener(this);
        actSum.setOnClickListener(this);
        actSub.setOnClickListener(this);
        actMult.setOnClickListener(this);
        actDiv.setOnClickListener(this);
        actDel.setOnClickListener(this);
        actRes.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn1_t5:
                mainText.setText(mainText.getText() + "1");
                break;
            case R.id.btn2_t5:
                mainText.setText(mainText.getText() + "2");
                break;
            case R.id.btn3_t5:
                mainText.setText(mainText.getText() + "3");
                break;
            case R.id.btn4_t5:
                mainText.setText(mainText.getText() + "4");
                break;
            case R.id.btn5_t5:
                mainText.setText(mainText.getText() + "5");
                break;
            case R.id.btn6_t5:
                mainText.setText(mainText.getText() + "6");
                break;
            case R.id.btn7_t5:
                mainText.setText(mainText.getText() + "7");
                break;
            case R.id.btn8_t5:
                mainText.setText(mainText.getText() + "8");
                break;
            case R.id.btn9_t5:
                mainText.setText(mainText.getText() + "9");
                break;
            case R.id.btn0_t5:
                mainText.setText(mainText.getText() + "0");
                break;
            case R.id.btn00_t5:
                mainText.setText(mainText.getText() + "00");
                break;


            case R.id.sum_t5:
                Log.d(TAG, "Button sum is pressed.");
                seccondaryText.setText(mainText.getText());
                mainText.setHint("");
                mainText.setText("");
                actType.setText("+");
                break;
            case R.id.sub_t5:
                Log.d(TAG, "Button sub is pressed.");
                seccondaryText.setText(mainText.getText());
                mainText.setHint("");
                mainText.setText("");
                actType.setText("-");
                break;
            case R.id.mult_t5:
                Log.d(TAG, "Button mult is pressed.");
                seccondaryText.setText(mainText.getText());
                mainText.setText("");
                mainText.setHint("");
                actType.setText("x");
                break;
            case R.id.div_t5:
                Log.d(TAG, "Button div is pressed.");
                seccondaryText.setText(mainText.getText());
                mainText.setText("");
                mainText.setHint("");
                actType.setText("/");
                break;
            case R.id.del_t5:
                Log.d(TAG, "Button del is pressed.");

                if (mainText.getText().equals("")){
                    actType.setText("");
                    seccondaryText.setText("");
                }
                mainText.setText(removeLastChar((String) mainText.getText()));
                break;
            case R.id.res_t5:
                Log.d(TAG, "Button res is pressed.");
                String act = (String) actType.getText();

                String num1 = (String) seccondaryText.getText();
                String num2 = (String) mainText.getText();

                if (num1.equals("") || num2.equals("")) break;

                actType.setText("");
                seccondaryText.setText("");
                mainText.setText(getResult(num1, num2, act));
                break;
        }

    }

    private String getResult(String num1, String num2, String act){
        double d1 = Double.parseDouble(num1);
        double d2 = Double.parseDouble(num2);
        double result = 0;
        int resultInt = 0;

        if (act.equals("+")) result = d1 + d2;
        else if (act.equals("-")) result = d1 - d2;
        else if (act.equals("x")) result = d1 * d2;
        else if (act.equals("/")){
            if (d2 == 0) return res = "Error!";
            else result = d1 / d2;
        }

        resultInt = (int)result;
        if (result%resultInt == 0) res = String.valueOf(resultInt);
        else res = String.valueOf(result);

        return res;
    }

    private String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }
}
