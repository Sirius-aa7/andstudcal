package com.example.mycalc4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {

    private EditText display;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }
    private void updateText(String strToAdd){
        String OldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = OldStr.substring(0, cursorPos);
        String rightStr = OldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);

        }
    }

    public void btnzero(View view) {
        updateText("0");
    }

    public void btnone(View view) {
        updateText("1");

    }

    public void btntwo(View view) {

        updateText("2");
    }

    public void btnthree(View view) {
        updateText("3");
    }

    public void btnfour(View view) {
        updateText("4");

    }

    public void btnfive(View view) {
        updateText("5");
    }

    public void btnsix(View view) {
        updateText("6");
    }

    public void btnseven(View view) {
        updateText("7");
    }

    public void btneight(View view) {
        updateText("8");
    }

    public void btnnine(View view) {
        updateText("9");
    }

    public void btnclear(View view) {
        display.setText("");
    }

    public void btnparenteses(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closedPar = 0;
        int textLen = display.getText().length();
        for (int i = 0; i < cursorPos; i++){
            if ( display.getText().toString().substring(i, i+1) .equals("(")){
                openPar += 1;
            }
            if ( display.getText().toString().substring(i, i+1) .equals(")")){
                closedPar += 1;
            }
        }
        if (openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText("(");

        }
        else if (closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals(")")){
            updateText(")");

        }
        display.setSelection(cursorPos + 1);
    }

    public void btndivide(View view) {
        updateText("÷");
    }

    public void btnmultiply(View view) {
        updateText("×");
    }

    public void btnsubtract(View view) {
        updateText("-");
    }

    public void btnadd(View view) {
        updateText("+");
    }

    public void btnplusminus(View view) {
        updateText("-");
    }

    public void btnpoint(View view) {
        updateText(".");
    }

    public void btnexponent(View view) {
        updateText("^");
    }


    public void btnequal(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");
        Expression exp = new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());

    }
    public void btnbackspace(View view) {

        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorPos != 0 && textLen !=0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos -1, cursorPos, "" );
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}

